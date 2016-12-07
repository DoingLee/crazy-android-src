package org.crazyit.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver
{
	// 当接收到短信时被触发
	@Override
	public void onReceive(Context context, Intent intent)
	{
		// 如果是接收到短信
		if (intent.getAction().equals(
				"android.provider.Telephony.SMS_RECEIVED"))
		{
			// 取消广播（这行代码将会让系统收不到短信）
			abortBroadcast();  // ①
			StringBuilder sb = new StringBuilder();
			// 接收由SMS传过来的数据
			Bundle bundle = intent.getExtras();
			// 判断是否有数据
			if (bundle != null)
			{
				// 通过pdus可以获得接收到的所有短信消息
				Object[] pdus = (Object[]) bundle.get("pdus");
				// 构建短信对象array,并依据收到的对象长度来创建array的大小
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++)
				{
					messages[i] = SmsMessage
							.createFromPdu((byte[]) pdus[i]);
				}
				// 将发送来的短信合并自定义信息于StringBuilder当中
				for (SmsMessage message : messages)
				{
					sb.append("短信来源:");
					// 获得接收短信的电话号码
					sb.append(message.getDisplayOriginatingAddress());
					sb.append("\n------短信内容------\n");
					// 获得短信的内容
					sb.append(message.getDisplayMessageBody());
				}
			}
			Toast.makeText(context, sb.toString()
					, Toast.LENGTH_LONG).show();
		}
	}

//	public void onReceive(Context context, Intent intent) {
//		SmsMessage msg = null;
//		Bundle bundle = intent.getExtras();
//		if (bundle != null) {
//			Object[] pdusObj = (Object[]) bundle.get("pdus");
//			for (Object p : pdusObj) {
//				msg= SmsMessage.createFromPdu((byte[]) p);
//
//				String msgTxt =msg.getMessageBody();//得到消息的内容
//
//				Date date = new Date(msg.getTimestampMillis());//时间
//				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String receiveTime = format.format(date);
//
//				String senderNumber = msg.getOriginatingAddress();
//
//				if (msgTxt.equals("Testing!")) {
//					Toast.makeText(context, "success!", Toast.LENGTH_LONG)
//							.show();
//					System.out.println("success!");
//					return;
//				} else {
//					Toast.makeText(context, msgTxt, Toast.LENGTH_LONG).show();
//					System.out.println("发送人："+senderNumber+"  短信内容："+msgTxt+"接受时间："+receiveTime);
//					return;
//				}
//			}
//			return;
//		}
//	}
}
