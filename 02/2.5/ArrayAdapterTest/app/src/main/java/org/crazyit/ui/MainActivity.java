package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView list1 = (ListView) findViewById(R.id.list1);
		// 定义一个数组
		String[] arr1 = { "孙悟空", "猪八戒", "牛魔王" };
		// 将数组包装为ArrayAdapter
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
				(this, R.layout.array_item, arr1);
		// 为ListView设置Adapter
		list1.setAdapter(adapter1);
		ListView list2 = (ListView) findViewById(R.id.list2);
		// 定义一个数组
		String[] arr2 = { "Java", "Hibernate", "Spring" , "Android" };
		// 将数组包装为ArrayAdapter
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
				(this, R.layout.checked_item, arr2);
		// 为ListView设置Adapter
		list2.setAdapter(adapter2);
	}
}
