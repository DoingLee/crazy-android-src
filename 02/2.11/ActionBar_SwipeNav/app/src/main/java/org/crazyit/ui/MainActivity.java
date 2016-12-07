package org.crazyit.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity
	implements ActionBar.TabListener
{
	ViewPager viewPager;
	ActionBar actionBar;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取ActionBar对象
		actionBar = getActionBar();
		// 获取ViewPager
		viewPager = (ViewPager) findViewById(R.id.pager);
		// 创建一个FragmentPagerAdapter对象，该对象负责为ViewPager提供多个Fragment
		FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(
			getSupportFragmentManager())
		{
			// 获取第position位置的Fragment
			@Override
			public Fragment getItem(int position)
			{
				Fragment fragment = new DummyFragment();
				Bundle args = new Bundle();
				args.putInt(DummyFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;
			}
			// 该方法的返回值i表明该Adapter总共包括多少个Fragment
			@Override
			public int getCount()
			{
				return 3;
			}
			// 该方法的返回值决定每个Fragment的标题
			@Override
			public CharSequence getPageTitle(int position)
			{
				switch (position)
				{
					case 0:
						return "第一页";
					case 1:
						return "第二页";
					case 2:
						return "第三页";
				}
				return null;
			}
		};
		// 设置ActionBar使用Tab导航方式
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// 遍历pagerAdapter对象所包含的全部Fragment
		// 每个Fragment对应创建一个Tab标签
		for (int i = 0; i < pagerAdapter.getCount(); i++)
		{
			actionBar.addTab(actionBar.newTab()
				.setText(pagerAdapter.getPageTitle(i))
				.setTabListener(this));
		}
		// 为ViewPager组件设置FragmentPagerAdapter
		viewPager.setAdapter(pagerAdapter); // ①
		// 为ViewPager组件绑定事件监听器
		viewPager.setOnPageChangeListener(
			new ViewPager.SimpleOnPageChangeListener()
			{
				// 当ViewPager显示的Fragment发生改变时激发该方法
				@Override
				public void onPageSelected(int position)
				{
					actionBar.setSelectedNavigationItem(position);
				}
			});
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
		viewPager.setCurrentItem(tab.getPosition());  // ②
	}
	@Override
	public void onTabReselected(ActionBar.Tab tab,
		FragmentTransaction fragmentTransaction)
	{
	}
}

