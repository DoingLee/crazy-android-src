package org.crazyit.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity
{
	public final static String CRAZYIT_ACTION =
			"org.crazyit.intent.action.CRAZYIT_ACTION";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bn = (Button) findViewById(R.id.bn);
		// 为bn按钮绑定事件监听器
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 创建Intent对象
				Intent intent = new Intent();
				// 为Intent设置Action属性（属性值就是一个普通字符串）
				intent.setAction(MainActivity.CRAZYIT_ACTION);
				startActivity(intent);
			}
		});
	}
}

