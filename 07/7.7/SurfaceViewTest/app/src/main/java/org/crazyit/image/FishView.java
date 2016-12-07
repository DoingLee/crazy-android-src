package org.crazyit.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class FishView extends SurfaceView
		implements SurfaceHolder.Callback
{
	private SurfaceHolder holder;
	private UpdateViewThread updateThread;
	private boolean hasSurface;
	private Bitmap back;
	private Bitmap[] fishs;
	private int fishIndex = 0; // 定义变量记录绘制第几张鱼的图片
	// 下面定义2个变量，记录鱼的初始位置
	private float fishX = 778;
	private float fishY = 500;
	private float fishSpeed = 6; // 鱼的游动速度
	// 定义鱼游动的角度
	private int fishAngle = new Random().nextInt(60);
	Matrix matrix = new Matrix();
	public FishView(Context ctx, AttributeSet set)
	{
		super(ctx, set);
		// 获取该SurfaceView对应的SurfaceHolder，并将该类的实例作为其Callback
		holder = getHolder();
		holder.addCallback(this);
		hasSurface = false;
		back = BitmapFactory.decodeResource(ctx.getResources()
				, R.drawable.fishbg);
		fishs = new Bitmap[10];
		// 初始化鱼游动动画的10张图片
		for(int i = 0 ; i < 10 ; i++)
		{
			try
			{
				int fishId = (Integer)R.drawable.class
						.getField("fish" + i).get(null);
				fishs[i] = BitmapFactory.decodeResource(
						ctx.getResources(), fishId);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void resume()
	{
		// 创建和启动图像更新线程
		if (updateThread == null)
		{
			updateThread = new UpdateViewThread();
			if (hasSurface == true)
				updateThread.start();
		}
	}

	public void pause()
	{
		// 停止图像更新线程
		if (updateThread != null)
		{
			updateThread.requestExitAndWait();
			updateThread = null;
		}
	}

	// 当SurfaceView被创建时回调该方法
	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		hasSurface = true;
		resume();
	}

	// 当SurfaceView将要被销毁时回调该方法
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		hasSurface = false;
		pause();
	}

	// 当SurfaceView发生改变时回调该方法
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h)
	{
		if (updateThread != null)
			updateThread.onWindowResize(w, h);
	}

	class UpdateViewThread extends Thread
	{
		// 定义一个记录图形是否更新完成的旗标
		private boolean done;
		UpdateViewThread()
		{
			super();
			done = false;
		}

		@Override
		public void run()
		{
			SurfaceHolder surfaceHolder = holder;
			// 重复绘图循环，直到线程停止
			while (!done)
			{
				// 锁定SurfaceView，并返回到要绘图的Canvas
				Canvas canvas = surfaceHolder.lockCanvas();  // ①
				// 绘制背景图片
				canvas.drawBitmap(back, 0, 0, null);
				// 如果鱼“游出”屏幕之外，重新初始鱼的位置
				if(fishX < 0)
				{
					fishX = 778;
					fishY = 500;
					fishAngle = new Random().nextInt(60);
				}
				if(fishY < 0)
				{
					fishX = 778;
					fishY = 500;
					fishAngle = new Random().nextInt(60);

				}
				// 使用Matrix来控制鱼的旋转角度和位置
				matrix.reset();
				matrix.setRotate(fishAngle);
				matrix.postTranslate(fishX -= fishSpeed * Math
					.cos(Math.toRadians(fishAngle))
					, fishY -= fishSpeed * Math.sin(Math.toRadians(fishAngle)));
				canvas.drawBitmap(fishs[fishIndex++ % fishs.length], matrix, null);
				// 解锁Canvas，并渲染当前图像
				surfaceHolder.unlockCanvasAndPost(canvas);  // ②
				try
				{
					Thread.sleep(60);
				}
				catch (InterruptedException e){}
			}
		}

		public void requestExitAndWait()
		{
			// 把这个线程标记为完成，并合并到主程序线程
			done = true;
			try
			{
				join();
			}
			catch (InterruptedException ex){}
		}

		public void onWindowResize(int w, int h){
			// 处理SurfaceView的大小改变事件
		}
	}
}