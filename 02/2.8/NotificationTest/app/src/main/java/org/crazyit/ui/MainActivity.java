package org.crazyit.ui;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity
{
	static final int NOTIFICATION_ID = 0x123;
	NotificationManager nm;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取系统的NotificationManager服务
		nm = (NotificationManager)
				getSystemService(NOTIFICATION_SERVICE);
	}
	// 为发送通知的按钮的点击事件定义事件处理方法
	public void send(View source)
	{
		// 创建一个启动其他Activity的Intent
		Intent intent = new Intent(MainActivity.this
				, OtherActivity.class);
		PendingIntent pi = PendingIntent.getActivity(
				MainActivity.this, 0, intent, 0);
		Notification notify = new Notification.Builder(this)
			// 设置打开该通知，该通知自动消失
			.setAutoCancel(true)
			// 设置显示在状态栏的通知提示信息
			.setTicker("有新消息")
			// 设置通知的图标
			.setSmallIcon(R.drawable.notify)
			// 设置通知内容的标题
			.setContentTitle("一条新通知")
			// 设置通知内容
			.setContentText("恭喜你，您加薪了，工资增加20%!")
			// 设置使用系统默认的声音、默认LED灯
			// .setDefaults(Notification.DEFAULT_SOUND
			// |Notification.DEFAULT_LIGHTS)
			// 设置通知的自定义声音
			.setSound(Uri.parse("android.resource://org.crazyit.ui/"
				+ R.raw.msg))
			.setWhen(System.currentTimeMillis())
			// 设改通知将要启动程序的Intent
			.setContentIntent(pi)  // ①
			.build();
		// 发送通知
		nm.notify(NOTIFICATION_ID, notify);
	}
	// 为删除通知的按钮的点击事件定义事件处理方法
	public void del(View v)
	{
		// 取消通知
		nm.cancel(NOTIFICATION_ID);
	}
}
