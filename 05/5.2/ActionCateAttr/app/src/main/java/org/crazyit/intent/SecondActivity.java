package org.crazyit.intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Set;

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
		EditText cate = (EditText) findViewById(R.id.cate);
		// 获取该Activity对应的Intent的Category属性
		Set<String> cates = getIntent().getCategories();
		// 显示Category属性
		cate.setText("Category属性为：" + cates);
	}
}
