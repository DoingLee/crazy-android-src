package org.crazyit.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bn = (Button) findViewById(R.id.bn);
		// 为bn按钮添加一个监听器
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 创建Intent
				Intent intent = new Intent();
				String data = "http://www.crazyit.org";
				// 根据指定字符串解析出Uri对象
				Uri uri = Uri.parse(data);
				// 为Intent设置Action属性
				intent.setAction(Intent.ACTION_VIEW);
				// 设置Data属性
				intent.setData(uri);
				startActivity(intent);
			}
		});
		Button edit = (Button) findViewById(R.id.edit);
		// 为edit按钮添加一个监听器
		edit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 创建Intent
				Intent intent = new Intent();
				// 为Intent设置Action属性（动作为：编辑）
				intent.setAction(Intent.ACTION_EDIT);
				String data = "content://com.android.contacts/contacts/1";
				// 根据指定字符串解析出Uri对象
				Uri uri = Uri.parse(data);
				// 设置Data属性
				intent.setData(uri);
				startActivity(intent);
			}
		});
		Button call = (Button) findViewById(R.id.call);
		// 为call按钮添加一个监听器
		call.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 创建Intent
				Intent intent = new Intent();
				// 为Intent设置Action属性（动作为：拨号）
				intent.setAction(Intent.ACTION_DIAL);
				String data = "tel:13800138000";
				// 根据指定字符串解析出Uri对象
				Uri uri = Uri.parse(data);
				// 设置Data属性
				intent.setData(uri);
				startActivity(intent);
			}
		});
	}
}

