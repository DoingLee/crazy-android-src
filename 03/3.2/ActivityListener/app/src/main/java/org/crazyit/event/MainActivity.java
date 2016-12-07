package org.crazyit.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


// 实现事件监听器接口
public class MainActivity extends Activity
		implements View.OnClickListener
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
		// 直接使用Activity作为事件监听器
		bn.setOnClickListener(this);
	}
	// 实现事件处理方法
	@Override
	public void onClick(View v)
	{
		show.setText("bn按钮被单击了！");
	}
}

