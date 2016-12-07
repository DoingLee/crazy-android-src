package org.crazyit.intent;

import android.app.Activity;
import android.content.ComponentName;
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
		// 获取该Activity对应的Intent的Component属性
		ComponentName comp = getIntent().getComponent();
		// 显示该ComponentName对象的包名、类名
		show.setText("组件包名为：" + comp.getPackageName()
				+ "\n组件类名为：" + comp.getClassName());
	}
}
