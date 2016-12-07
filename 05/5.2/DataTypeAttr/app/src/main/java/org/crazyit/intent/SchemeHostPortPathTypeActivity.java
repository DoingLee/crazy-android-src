package org.crazyit.intent;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class SchemeHostPortPathTypeActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("指定Scheme、Host、Port、Path、Type匹配的Activity");
		tv.setTextSize(30);
		setContentView(tv);
	}
}