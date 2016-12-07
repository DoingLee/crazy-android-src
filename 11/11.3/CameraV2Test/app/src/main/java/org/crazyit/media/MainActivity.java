package org.crazyit.media;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener
{
	private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

	static
	{
		ORIENTATIONS.append(Surface.ROTATION_0, 90);
		ORIENTATIONS.append(Surface.ROTATION_90, 0);
		ORIENTATIONS.append(Surface.ROTATION_180, 270);
		ORIENTATIONS.append(Surface.ROTATION_270, 180);
	}

	private AutoFitTextureView textureView;
	// 摄像头ID（通常0代表后置摄像头，1代表前置摄像头）
	private String mCameraId = "0";
	// 定义代表摄像头的成员变量
	private CameraDevice cameraDevice;
	// 预览尺寸
	private Size previewSize;
	private CaptureRequest.Builder previewRequestBuilder;
	// 定义用于预览照片的捕获请求
	private CaptureRequest previewRequest;
	// 定义CameraCaptureSession成员变量
	private CameraCaptureSession captureSession;
	private ImageReader imageReader;
	private final TextureView.SurfaceTextureListener mSurfaceTextureListener
			= new TextureView.SurfaceTextureListener()
	{
		@Override
		public void onSurfaceTextureAvailable(SurfaceTexture texture
			, int width, int height)
		{
			// 当TextureView可用时，打开摄像头
			openCamera(width, height);
		}

		@Override
		public void onSurfaceTextureSizeChanged(SurfaceTexture texture
			, int width, int height){ }

		@Override
		public boolean onSurfaceTextureDestroyed(SurfaceTexture texture)
		{
			return true;
		}

		@Override
		public void onSurfaceTextureUpdated(SurfaceTexture texture){}
	};
	private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback()
	{
		//  摄像头被打开时激发该方法
		@Override
		public void onOpened(CameraDevice cameraDevice)
		{
			MainActivity.this.cameraDevice = cameraDevice;
			// 开始预览
			createCameraPreviewSession();  // ②
		}
		// 摄像头断开连接时激发该方法
		@Override
		public void onDisconnected(CameraDevice cameraDevice)
		{
			cameraDevice.close();
			MainActivity.this.cameraDevice = null;
		}
		// 打开摄像头出现错误时激发该方法
		@Override
		public void onError(CameraDevice cameraDevice, int error)
		{
			cameraDevice.close();
			MainActivity.this.cameraDevice = null;
			MainActivity.this.finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textureView = (AutoFitTextureView) findViewById(R.id.texture);
		// 为该组件设置监听器
		textureView.setSurfaceTextureListener(mSurfaceTextureListener);
		findViewById(R.id.capture).setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		captureStillPicture();
	}

	private void captureStillPicture()
	{
		try
		{
			if (cameraDevice == null)
			{
				return;
			}
			// 创建作为拍照的CaptureRequest.Builder
			final CaptureRequest.Builder captureRequestBuilder =
					cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
			// 将imageReader的surface作为CaptureRequest.Builder的目标
			captureRequestBuilder.addTarget(imageReader.getSurface());
			// 设置自动对焦模式
			captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
					CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
			// 设置自动曝光模式
			captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
					CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
			// 获取设备方向
			int rotation = getWindowManager().getDefaultDisplay().getRotation();
			// 根据设备方向计算设置照片的方向
			captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION
					, ORIENTATIONS.get(rotation));
			// 停止连续取景
			captureSession.stopRepeating();
			// 捕获静态图像
			captureSession.capture(captureRequestBuilder.build()
				, new CameraCaptureSession.CaptureCallback()  // ⑤
			{
				// 拍照完成时激发该方法
				@Override
				public void onCaptureCompleted(CameraCaptureSession session
						, CaptureRequest request, TotalCaptureResult result)
				{
					try
					{
						// 重设自动对焦模式
						previewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,
								CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
						// 设置自动曝光模式
						previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
								CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
						// 打开连续取景模式
						captureSession.setRepeatingRequest(previewRequest, null,
								null);
					}
					catch (CameraAccessException e)
					{
						e.printStackTrace();
					}
				}
			}, null);
		}
		catch (CameraAccessException e)
		{
			e.printStackTrace();
		}
	}

	// 打开摄像头
	private void openCamera(int width, int height)
	{
		setUpCameraOutputs(width, height);
		CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
		try
		{
			// 打开摄像头
			manager.openCamera(mCameraId, stateCallback, null); // ①
		}
		catch (CameraAccessException e)
		{
			e.printStackTrace();
		}
	}

	private void createCameraPreviewSession()
	{
		try
		{
			SurfaceTexture texture = textureView.getSurfaceTexture();
			texture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());
			Surface surface = new Surface(texture);
			// 创建作为预览的CaptureRequest.Builder
			previewRequestBuilder = cameraDevice
				.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
			// 将textureView的surface作为CaptureRequest.Builder的目标
			previewRequestBuilder.addTarget(new Surface(texture));
			// 创建CameraCaptureSession，该对象负责管理处理预览请求和拍照请求
			cameraDevice.createCaptureSession(Arrays.asList(surface
				, imageReader.getSurface()), new CameraCaptureSession.StateCallback() // ③
				{
					@Override
					public void onConfigured(CameraCaptureSession cameraCaptureSession)
					{
						// 如果摄像头为null，直接结束方法
						if (null == cameraDevice)
						{
							return;
						}

						// 当摄像头已经准备好时，开始显示预览
						captureSession = cameraCaptureSession;
						try
						{
							// 设置自动对焦模式
							previewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
									CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
							// 设置自动曝光模式
							previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
									CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
							// 开始显示相机预览
							previewRequest = previewRequestBuilder.build();
							// 设置预览时连续捕获图像数据
							captureSession.setRepeatingRequest(previewRequest,
									null, null);  // ④
						}
						catch (CameraAccessException e)
						{
							e.printStackTrace();
						}
					}

					@Override
					public void onConfigureFailed(CameraCaptureSession cameraCaptureSession)
					{
						Toast.makeText(MainActivity.this, "配置失败！"
								, Toast.LENGTH_SHORT).show();
					}
				}, null
			);
		}
		catch (CameraAccessException e)
		{
			e.printStackTrace();
		}
	}

	private void setUpCameraOutputs(int width, int height)
	{
		CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
		try
		{
			// 获取指定摄像头的特性
			CameraCharacteristics characteristics
					= manager.getCameraCharacteristics(mCameraId);
			// 获取摄像头支持的配置属性
			StreamConfigurationMap map = characteristics.get(
					CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

			// 获取摄像头支持的最大尺寸
			Size largest = Collections.max(
					Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)),
					new CompareSizesByArea());
			// 创建一个ImageReader对象，用于获取摄像头的图像数据
			imageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(),
					ImageFormat.JPEG, 2);
			imageReader.setOnImageAvailableListener(
					new ImageReader.OnImageAvailableListener()
					{
						// 当照片数据可用时激发该方法
						@Override
						public void onImageAvailable(ImageReader reader)
						{
							// 获取捕获的照片数据
							Image image = reader.acquireNextImage();
							ByteBuffer buffer = image.getPlanes()[0].getBuffer();
							byte[] bytes = new byte[buffer.remaining()];
							// 使用IO流将照片写入指定文件
							File file = new File(getExternalFilesDir(null), "pic.jpg");
							buffer.get(bytes);
							try (
								FileOutputStream output = new FileOutputStream(file))
							{
								output.write(bytes);
								Toast.makeText(MainActivity.this, "保存: "
									+ file, Toast.LENGTH_SHORT).show();
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
							finally
							{
								image.close();
							}
						}
					}, null);

			// 获取最佳的预览尺寸
			previewSize = chooseOptimalSize(map.getOutputSizes(
				SurfaceTexture.class), width, height, largest);
			// 根据选中的预览尺寸来调整预览组件（TextureView的）的长宽比
			int orientation = getResources().getConfiguration().orientation;
			if (orientation == Configuration.ORIENTATION_LANDSCAPE)
			{
				textureView.setAspectRatio(
						previewSize.getWidth(), previewSize.getHeight());
			}
			else
			{
				textureView.setAspectRatio(
						previewSize.getHeight(), previewSize.getWidth());
			}
		}
		catch (CameraAccessException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			System.out.println("出现错误。");
		}
	}

	private static Size chooseOptimalSize(Size[] choices
		, int width, int height, Size aspectRatio)
	{
		// 收集摄像头支持的打过预览Surface的分辨率
		List<Size> bigEnough = new ArrayList<>();
		int w = aspectRatio.getWidth();
		int h = aspectRatio.getHeight();
		for (Size option : choices)
		{
			if (option.getHeight() == option.getWidth() * h / w &&
					option.getWidth() >= width && option.getHeight() >= height)
			{
				bigEnough.add(option);
			}
		}
		// 如果找到多个预览尺寸，获取其中面积最小的。
		if (bigEnough.size() > 0)
		{
			return Collections.min(bigEnough, new CompareSizesByArea());
		}
		else
		{
			System.out.println("找不到合适的预览尺寸！！！");
			return choices[0];
		}
	}

	// 为Size定义一个比较器Comparator
	static class CompareSizesByArea implements Comparator<Size>
	{
		@Override
		public int compare(Size lhs, Size rhs)
		{
			// 强转为long保证不会发生溢出
			return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
					(long) rhs.getWidth() * rhs.getHeight());
		}
	}
}
