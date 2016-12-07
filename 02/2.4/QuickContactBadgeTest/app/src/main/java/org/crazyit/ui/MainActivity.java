package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.QuickContactBadge;


public class MainActivity extends Activity
{
	QuickContactBadge badge;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取QuickContactBadge组件
		badge = (QuickContactBadge) findViewById(R.id.badge);
		// 将QuickContactBadge组件与特定电话号码对应的联系人建立关联
		badge.assignContactFromPhone("020-88888888", false);
	}
}

