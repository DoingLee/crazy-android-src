package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import java.util.Calendar;


public class MainActivity extends Activity
{
	// 定义5个记录当前时间的变量
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DatePicker datePicker = (DatePicker)
				findViewById(R.id.datePicker);
		TimePicker timePicker = (TimePicker)
				findViewById(R.id.timePicker);
		// 获取当前的年、月、日、小时、分钟
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR);
		minute = c.get(Calendar.MINUTE);
		// 初始化DatePicker组件，初始化时指定监听器
		datePicker.init(year, month, day, new OnDateChangedListener()
		{
			@Override
			public void onDateChanged(DatePicker arg0, int year
					, int month, int day)
			{
				MainActivity.this.year = year;
				MainActivity.this.month = month;
				MainActivity.this.day = day;
				// 显示当前日期、时间
				showDate(year, month, day, hour, minute);
			}
		});
		timePicker.setEnabled(true);
		// 为TimePicker指定监听器
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener()
		{
			@Override
			public void onTimeChanged(TimePicker view
					, int hourOfDay, int minute)
			{
				MainActivity.this.hour = hourOfDay;
				MainActivity.this.minute = minute;
				// 显示当前日期、时间
				showDate(year, month, day, hour, minute);
			}
		});
	}
	// 定义在EditText中显示当前日期、时间的方法
	private void showDate(int year, int month
			, int day, int hour, int minute)
	{
		EditText show = (EditText) findViewById(R.id.show);
		show.setText("您的购买日期为：" + year + "年"
				+ (month + 1) + "月" + day + "日  "
				+ hour + "时" + minute + "分");
	}
}

