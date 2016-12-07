package org.crazyit.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity
{
	private ICat catService;
	private Button get;
	EditText color, weight;
	private ServiceConnection conn = new ServiceConnection()
	{
		@Override
		public void onServiceConnected(ComponentName name
			, IBinder service)
		{
			// 获取远程Service的onBind方法返回的对象的代理
			catService = ICat.Stub.asInterface(service);
		}
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			catService = null;
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		get = (Button) findViewById(R.id.get);
		color = (EditText) findViewById(R.id.color);
		weight = (EditText) findViewById(R.id.weight);
		// 创建所需绑定的Service的Intent
		Intent intent = new Intent(this, ICat.class);
//		intent.setAction("org.crazyit.aidl.action.AIDL_SERVICE");
//
//		bindService(new Intent(IRemoteService.class.getName()),
//				mConnection, Context.BIND_AUTO_CREATE);
//		bindService(new Intent(ISecondary.class.getName()),
//				mSecondaryConnection, Context.BIND_AUTO_CREATE);

		// 绑定远程Service
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
		get.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				try
				{
					// 获取并显示远程Service的状态
					color.setText(catService.getColor());
					weight.setText(catService.getWeight() + "");
				}
				catch (RemoteException e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// 解除绑定
		this.unbindService(conn);
	}
}

