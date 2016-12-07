package org.crazyit.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	Button startActivity, addFragment, backFragment, replaceFragment ,finish;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		startActivity = (Button) findViewById(R.id.startActivity);
		addFragment = (Button) findViewById(R.id.addFragment);
		backFragment = (Button) findViewById(R.id.backFragment);
		replaceFragment = (Button) findViewById(R.id.replaceFragment);
		finish = (Button) findViewById(R.id.finish);
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
		// 为addFragment按钮绑定事件监听器
		addFragment.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				LifecycleFragment fragment = new LifecycleFragment();
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment)
						.commit();
			}
		});
		// 为backFragment按钮绑定事件监听器
		backFragment.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				SecondFragment fragment = new SecondFragment();
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment)
						.addToBackStack("aa")// 将替换前的Fragment添加到Back栈
						.commit();
			}
		});
		// 为replaceFragment按钮绑定事件监听器
		replaceFragment.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SecondFragment fragment = new SecondFragment();
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment)
						.commit();
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
}
