package org.crazyit.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class HelloWorld extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 使用activity_main文件定义的界面布局
		setContentView(R.layout.main);
	}
	public void clickHandler(View source)
	{
		// 获取UI界面中ID为R.id.show的文本框
		TextView tv = (TextView) findViewById(R.id.show);
		// 改变文本框的文本内容
		tv.setText("Hello Android-" + new java.util.Date());
	}
}
