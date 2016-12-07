package org.crazyit.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
	// 保持所启动的Service的IBinder对象
	BindService.MyBinder binder;
	// 定义启动Service的Intent
	Intent intent;
	// 定义一个ServiceConnection对象
	private ServiceConnection conn = new ServiceConnection()
	{
		// 当该Activity与Service连接成功时回调该方法
		@Override
		public void onServiceConnected(ComponentName name
				, IBinder service)
		{
			System.out.println("--Service Connected--");
			// 获取Service的onBind方法所返回的MyBinder对象
			binder = (BindService.MyBinder) service; // ①
		}

		// 当该Activity与Service断开连接时回调该方法
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			System.out.println("--Service Disconnected--");
		}
	};

	public void start(View source)
	{
		// 启动intent对应的Service
		startService(intent);
	}

	public void bind(View source)
	{
		// 启动intent对应的Service
		// 绑定指定Serivce
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
	}

	public void unBind(View source)
	{
		// 解除绑定Serivce
		unbindService(conn);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 创建启动Service的Intent
		intent = new Intent(this , BindService.class);
	}
}
