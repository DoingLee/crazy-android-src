package org.crazyit.manager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity
{
	EditText number, content;
	Button send;
	SmsManager sManager;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取SmsManager
		sManager = SmsManager.getDefault();
		// 获取程序界面上的两个文本框和按钮
		number = (EditText) findViewById(R.id.number);
		content = (EditText) findViewById(R.id.content);
		send = (Button) findViewById(R.id.send);
		// 为send按钮的单击事件绑定监听器
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 创建一个PendingIntent对象
				PendingIntent pi = PendingIntent.getActivity(
						MainActivity.this, 0, new Intent(), 0);
				// 发送短信
				sManager.sendTextMessage(number.getText().toString(),
						null, content.getText().toString(), pi, null);
				// 提示短信发送完成
				Toast.makeText(MainActivity.this, "短信发送完成", 8000).show();
			}
		});
	}
}

