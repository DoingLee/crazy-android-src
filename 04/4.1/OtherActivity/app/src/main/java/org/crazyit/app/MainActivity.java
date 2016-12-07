package org.crazyit.app;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


public class MainActivity extends LauncherActivity
{
	//定义两个Activity的名称
	String[] names = {"设置程序参数" ,  "查看星际兵种"};
	//定义两个Activity对应的实现类
	Class<?>[] clazzs = {PreferenceActivityTest.class
			, ExpandableListActivityTest.class};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1 , names);
		// 设置该窗口显示的列表所需的Adapter
		setListAdapter(adapter);
	}
	//根据列表项返回指定Activity对应的Intent
	@Override
	public Intent intentForPosition(int position)
	{
		return new Intent(MainActivity.this , clazzs[position]);
	}
}

