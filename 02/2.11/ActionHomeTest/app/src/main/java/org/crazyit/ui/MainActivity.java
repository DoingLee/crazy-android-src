package org.crazyit.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity
{
	private TextView txt;
	ActionBar actionBar;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt = (TextView) findViewById(R.id.txt);
		actionBar = getActionBar();
		// 设置是否显示应用程序图标
		actionBar.setDisplayShowHomeEnabled(true);
		// 将应用程序图标设置为可点击的按钮
//		actionBar.setHomeButtonEnabled(true);
		// 将应用程序图标设置为可点击的按钮，并在图标上添加向左箭头
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflator = new MenuInflater(this);
		// 状态R.menu.menu_main对应的菜单，并添加到menu中
		inflator.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	// 选项菜单的菜单项被单击后的回调方法
	public boolean onOptionsItemSelected(MenuItem mi)
	{
		if(mi.isCheckable())
		{
			mi.setChecked(true);
		}
		// 判断单击的是哪个菜单项，并有针对性地作出响应
		switch (mi.getItemId())
		{
			case android.R.id.home:
				// 创建启动FirstActivity的Intent
				Intent intent = new Intent(this, FirstActivity.class);
				// 添加额外的Flag，将Activity栈中处于FirstActivity之上的Activity弹出
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// 启动intent对应的Activity
				startActivity(intent);
				break;
			case R.id.font_10:
				txt.setTextSize(10 * 2);
				break;
			case R.id.font_12:
				txt.setTextSize(12 * 2);
				break;
			case R.id.font_14:
				txt.setTextSize(14 * 2);
				break;
			case R.id.font_16:
				txt.setTextSize(16 * 2);
				break;
			case R.id.font_18:
				txt.setTextSize(18 * 2);
				break;
			case R.id.red_font:
				txt.setTextColor(Color.RED);
				mi.setChecked(true);
				break;
			case R.id.green_font:
				txt.setTextColor(Color.GREEN);
				mi.setChecked(true);
				break;
			case R.id.blue_font:
				txt.setTextColor(Color.BLUE);
				mi.setChecked(true);
				break;
			case R.id.plain_item:
				Toast toast = Toast.makeText(MainActivity.this, "您单击了普通菜单项",
						Toast.LENGTH_SHORT);
				toast.show();
				break;
		}
		return true;
	}
}
