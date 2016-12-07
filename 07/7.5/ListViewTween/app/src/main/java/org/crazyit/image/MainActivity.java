package org.crazyit.image;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;


public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取ListView组件
		ListView list = (ListView) findViewById(R.id.list);
		WindowManager windowManager = (WindowManager)
				getSystemService(WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrice = new DisplayMetrics();
		// 获取屏幕的宽和高
		display.getMetrics(metrice);
		// 设置对ListView组件应用动画
		list.setAnimation(new MyAnimation(metrice.xdpi / 2
				, metrice.ydpi / 2, 3500));
	}
}


