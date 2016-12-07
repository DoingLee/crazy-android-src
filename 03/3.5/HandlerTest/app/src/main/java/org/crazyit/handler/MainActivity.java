package org.crazyit.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity
{
	// 定义周期性显示的图片的ID
	int[] imageIds = new int[]
		{
			R.drawable.java,
			R.drawable.javaee,
			R.drawable.ajax,
			R.drawable.android,
			R.drawable.swift
		};
	int currentImageId = 0;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageView show = (ImageView) findViewById(R.id.show);
		final Handler myHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				// 如果该消息是本程序所发送的
				if (msg.what == 0x1233)
				{
					// 动态地修改所显示的图片
					show.setImageResource(imageIds[currentImageId++
							% imageIds.length]);
				}
			}
		};
		// 定义一个计时器，让该计时器周期性地执行指定任务
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				// 发送空消息
				myHandler.sendEmptyMessage(0x1233);
			}
		}, 0, 1200);
	}
}

