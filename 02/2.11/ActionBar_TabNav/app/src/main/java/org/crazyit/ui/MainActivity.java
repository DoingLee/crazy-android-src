package org.crazyit.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements
	ActionBar.TabListener
{
	private static final String SELECTED_ITEM = "selected_item";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ActionBar actionBar = getActionBar();
		// 设置ActionBar的导航方式：Tab导航
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// 依次添加三个Tab页，并为三个Tab标签添加事件监听器
		actionBar.addTab(actionBar.newTab().setText("第一页")
			.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("第二页")
			.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("第三页")
			.setTabListener(this));
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		if (savedInstanceState.containsKey(SELECTED_ITEM))
		{
			// 选中前面保存的索引对应的Fragment页
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(SELECTED_ITEM));
		}
	}
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		// 将当前选中的Fragment页的索引保存到Bundle中
		outState.putInt(SELECTED_ITEM,
				getActionBar().getSelectedNavigationIndex());
	}
	@Override
	public void onTabUnselected(ActionBar.Tab tab,
		FragmentTransaction fragmentTransaction)
	{
	}
	// 当指定Tab被选中时激发该方法
	@Override
	public void onTabSelected(ActionBar.Tab tab,
		FragmentTransaction fragmentTransaction)
	{
		// 创建一个新的Fragment对象
		Fragment fragment = new DummyFragment();
		// 创建一个Bundle对象，用于向Fragment传入参数
		Bundle args = new Bundle();
		args.putInt(DummyFragment.ARG_SECTION_NUMBER,
				tab.getPosition() + 1);
		// 向fragment传入参数
		fragment.setArguments(args);
		// 获取FragmentTransaction对象
		FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		// 使用fragment代替该Activity中的container组件
		ft.replace(R.id.container, fragment);
		// 提交事务
		ft.commit();
	}
	@Override
	public void onTabReselected(ActionBar.Tab tab,
		FragmentTransaction fragmentTransaction)
	{
	}
}

