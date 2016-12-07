package org.crazyit.event;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class SendSmsListener implements OnLongClickListener
{
	private Activity act;
	private EditText address;
	private EditText content;
	public SendSmsListener(Activity act, EditText address
		, EditText content)
	{
		this.act = act;
		this.address = address;
		this.content = content;
	}
	@Override
	public boolean onLongClick(View source)
	{
		String addressStr = address.getText().toString();
		String contentStr = content.getText().toString();
		// 获取短信管理器
		SmsManager smsManager = SmsManager.getDefault();
		// 创建发送短信的PendingIntent
		PendingIntent sentIntent = PendingIntent.getBroadcast(act
				, 0, new Intent(), 0);
		// 发送文本短信
		smsManager.sendTextMessage(addressStr, null, contentStr
				, sentIntent, null);
		Toast.makeText(act, "短信发送完成", Toast.LENGTH_LONG).show();
		return false;
	}
}
