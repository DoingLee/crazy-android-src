package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewFlipper;


public class MainActivity extends Activity
{
	private ViewFlipper viewFlipper;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		viewFlipper = (ViewFlipper) findViewById(R.id.details);
	}
	public void prev(View source)
	{
		viewFlipper.setInAnimation(this , R.anim.slide_in_right);
		viewFlipper.setOutAnimation(this , R.anim.slide_out_left);
		// 显示上一个组件
		viewFlipper.showPrevious();
		// 停止自动播放
		viewFlipper.stopFlipping();
	}
	public void next(View source)
	{
		viewFlipper.setInAnimation(this , android.R.anim.slide_in_left);
		viewFlipper.setOutAnimation(this , android.R.anim.slide_out_right);
		// 显示下一个组件
		viewFlipper.showNext();
		// 停止自动播放
		viewFlipper.stopFlipping();
	}
	public void auto(View source)
	{
		viewFlipper.setInAnimation(this , android.R.anim.slide_in_left);
		viewFlipper.setOutAnimation(this , android.R.anim.slide_out_right);
		// 开始自动播放
		viewFlipper.startFlipping();
	}
}

