package org.crazyit.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	final String TAG = "--CrazyIt--";
	Button finish ,startActivity;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 输出日志
		Log.d(TAG, "-------onCreate------");
		finish = (Button) findViewById(R.id.finish);
		startActivity = (Button) findViewById(R.id.startActivity);
		// 为startActivity按钮绑定事件监听器
		startActivity.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				Intent intent = new Intent(MainActivity.this
					, SecondActivity.class);
				startActivity(intent);
			}
		});
		// 为finish按钮绑定事件监听器
		finish.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// 结束该Activity
				MainActivity.this.finish();
			}
		});
	}
	@Override
	public void onStart()
	{
		super.onStart();
		// 输出日志
		Log.d(TAG, "-------onStart------");
	}
	@Override
	public void onRestart()
	{
		super.onRestart();
		// 输出日志
		Log.d(TAG, "-------onRestart------");
	}
	@Override
	public void onResume()
	{
		super.onResume();
		// 输出日志
		Log.d(TAG, "-------onResume------");
	}
	@Override
	public void onPause()
	{
		super.onPause();
		// 输出日志
		Log.d(TAG, "-------onPause------");
	}
	@Override
	public void onStop()
	{
		super.onStop();
		// 输出日志
		Log.d(TAG, "-------onStop------");
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// 输出日志
		Log.d(TAG, "-------onDestroy------");
	}
}


