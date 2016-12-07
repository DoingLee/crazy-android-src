package org.crazyit.image;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity
{
	String[] images = null;
	AssetManager assets = null;
	int currentImg = 0;
	ImageView image;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image = (ImageView) findViewById(R.id.image);
		try
		{
			assets = getAssets();
			// 获取/assets/目录下所有文件
			images = assets.list("");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		// 获取next按钮
		final Button next = (Button) findViewById(R.id.next);
		// 为next按钮绑定事件监听器，该监听器将会查看下一张图片
		next.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View sources)
			{
				// 如果发生数组越界
				if (currentImg >= images.length)
				{
					currentImg = 0;
				}
				// 找到下一个图片文件
				while (!images[currentImg].endsWith(".png")
						&& !images[currentImg].endsWith(".jpg")
						&& !images[currentImg].endsWith(".gif"))
				{
					currentImg++;
					// 如果已发生数组越界
					if (currentImg >= images.length)
					{
						currentImg = 0;
					}
				}
				InputStream assetFile = null;
				try
				{
					// 打开指定资源对应的输入流
					assetFile = assets.open(images[currentImg++]);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				BitmapDrawable bitmapDrawable = (BitmapDrawable) image
					.getDrawable();
				// 如果图片还未回收，先强制回收该图片
				if (bitmapDrawable != null
						&& !bitmapDrawable.getBitmap().isRecycled()) // ①
				{
					bitmapDrawable.getBitmap().recycle();
				}
				// 改变ImageView显示的图片
				image.setImageBitmap(BitmapFactory
					.decodeStream(assetFile)); // ②
			}
		});
	}
}

