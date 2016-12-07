package org.crazyit.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity
{
	EditText show;
	Button bn;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		show = (EditText) findViewById(R.id.show);
		bn = (Button) findViewById(R.id.bn);
		// 使用匿名内部类的实例作为事件监听器
		bn.setOnClickListener(new OnClickListener()
		{
			// 实现事件处理方法
			@Override
			public void onClick(View v)
			{
				show.setText("bn按钮被单击了！");
			}
		});
	}
}
