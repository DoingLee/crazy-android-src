package org.crazyit.res;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{
	TextView tvShow;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvShow = (TextView) findViewById(R.id.show);
		// 设置文本框所显示的文本
		tvShow.setText(R.string.msg);
	}
}

