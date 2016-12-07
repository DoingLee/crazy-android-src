package org.crazyit.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void startService(View source)
	{
		// 创建需要启动的Service的Intent
		Intent intent = new Intent(this, MyService.class);
		// 启动Service
		startService(intent);
	}
	public void startIntentService(View source)
	{
		// 创建需要启动的IntentService的Intent
		Intent intent = new Intent(this, MyIntentService.class);
		// 启动IntentService
		startService(intent);
	}
}

