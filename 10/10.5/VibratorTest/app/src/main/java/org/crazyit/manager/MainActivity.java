package org.crazyit.manager;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;


public class MainActivity extends Activity
{
	Vibrator vibrator;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取系统的Vibrator服务
		vibrator = (Vibrator) getSystemService(
				Service.VIBRATOR_SERVICE);
	}
	// 重写onTouchEvent方法，当用户触碰触摸屏时触发该方法
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		Toast.makeText(this, "手机振动"
				, Toast.LENGTH_SHORT).show();
		// 控制手机振动2秒
		vibrator.vibrate(2000);
		return super.onTouchEvent(event);
	}
}
