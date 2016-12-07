package org.crazyit.intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class SecondActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		EditText show = (EditText) findViewById(R.id.show);
		// 获取该Activity对应的Intent的Action属性
		String action = getIntent().getAction();
		// 显示Action属性
		show.setText("Action为：" + action);
	}
}
