package org.crazyit.ui;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivity extends TabActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取该Activity里面的TabHost组件
		TabHost tabHost = getTabHost();
		// 创建第一个Tab页
		TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
				.setIndicator("已接电话") // 设置标题
				.setContent(R.id.tab01); //设置内容
		// 添加第一个标签页
		tabHost.addTab(tab1);
		TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2")
				// 在标签标题上放置图标
				.setIndicator("呼出电话", getResources()
						.getDrawable(R.drawable.ic_launcher))
				.setContent(R.id.tab02);
		// 添加第二个标签页
		tabHost.addTab(tab2);
		TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3")
				.setIndicator("未接电话")
				.setContent(R.id.tab03);
		// 添加第三个标签页
		tabHost.addTab(tab3);
	}
}

