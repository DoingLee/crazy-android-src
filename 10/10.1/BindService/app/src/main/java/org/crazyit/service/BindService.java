package org.crazyit.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService extends Service
{
	private int count;
	private boolean quit;
	// 定义onBinder方法所返回的对象
	private MyBinder binder = new MyBinder();
	// 通过继承Binder来实现IBinder类
	public class MyBinder extends Binder  // ①
	{
		public int getCount()
		{
			// 获取Service的运行状态：count
			return count;
		}
	}
	// 必须实现的方法，绑定该Service时回调该方法
	@Override
	public IBinder onBind(Intent intent)
	{
		System.out.println("Service is Binded");
		// 返回IBinder对象
		return binder;
	}
	// Service被创建时回调该方法
	@Override
	public void onCreate()
	{
		super.onCreate();
		System.out.println("Service is Created");
		// 启动一条线程，动态地修改count状态值
		new Thread()
		{
			@Override
			public void run()
			{
				while (!quit)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
					}
					count++;
				}
			}
		}.start();
	}
	// Service被断开连接时回调该方法
	@Override
	public boolean onUnbind(Intent intent)
	{
		System.out.println("Service is Unbinded");
		return true;
	}
	// Service被关闭之前回调该方法
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		this.quit = true;
		System.out.println("Service is Destroyed");
	}
}
