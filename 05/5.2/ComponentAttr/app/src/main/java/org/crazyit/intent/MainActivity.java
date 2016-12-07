package org.crazyit.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bn = (Button) findViewById(R.id.bn);
		// 为bn按钮绑定事件监听器
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 创建一个ComponentName对象
				ComponentName comp = new ComponentName(MainActivity.this,
					SecondActivity.class);
				Intent intent = new Intent();
				// 为Intent设置Component属性
				intent.setComponent(comp);
				startActivity(intent);
			}
		});
	}
}

