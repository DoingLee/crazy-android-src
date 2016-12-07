package org.crazyit.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	Button start, stop;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取程序界面中的start、stop两个按钮
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		// 创建启动Service的Intent
		final Intent intent = new Intent(this , FirstService.class);
		start.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 启动指定Service
				startService(intent);
			}
		});
		stop.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 停止指定Service
				stopService(intent);
			}
		});
	}
}


