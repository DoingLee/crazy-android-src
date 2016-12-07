package org.crazyit.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		this.setContentView(layout);
		// 创建一个TextView来显示该Activity和它所在Task ID
		TextView tv = new TextView(this);
		tv.setText("Activity为：" + this.toString()
				+ "\n" + "，Task ID为:" + this.getTaskId());
		Button button = new Button(this);
		button.setText("启动SecondActivity");
		// 添加TextView和Button
		layout.addView(tv);
		layout.addView(button);
		// 为button添加事件监听器，使用隐式Intent启动目标Activity
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 使用隐式Intent启动Second Activity
				Intent intent = new Intent();
				intent.setAction("org.crazyit.intent.action.CRAZYIT_ACTION");
				startActivity(intent);
			}
		});
	}
}
