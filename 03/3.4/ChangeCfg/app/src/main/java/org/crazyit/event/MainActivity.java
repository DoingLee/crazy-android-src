package org.crazyit.event;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bn = (Button) findViewById(R.id.bn);
		// 为按钮绑定事件监听器
		bn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				Configuration config = getResources().getConfiguration();
				// 如果当前是横屏
				if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
					// 设为竖屏
					MainActivity.this.setRequestedOrientation(
						ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				// 如果当前是竖屏
				if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
					// 设为横屏
					MainActivity.this.setRequestedOrientation(
						ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}
			}
		});
	}
	// 重写该方法，用于监听系统设置的更改，主要是监控屏幕方向的更改
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		String screen = newConfig.orientation ==
			Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕";
		Toast.makeText(this, "系统的屏幕方向发生改变" + "\n修改后的屏幕方向为："
				+ screen, Toast.LENGTH_LONG).show();
	}
}
