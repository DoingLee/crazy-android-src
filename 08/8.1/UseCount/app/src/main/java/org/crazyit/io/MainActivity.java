package org.crazyit.io;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity
{
	SharedPreferences preferences;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		preferences = getSharedPreferences("count"
			, MODE_PRIVATE);
		// 读取SharedPreferences里的count数据
		int count = preferences.getInt("count", 0);
		// 显示程序以前使用的次数
		Toast.makeText(this, "程序以前被使用了" + count + "次。"
				, Toast.LENGTH_LONG).show();
		SharedPreferences.Editor editor = preferences.edit();
		// 存入数据
		editor.putInt("count", ++count);
		// 提交修改
		editor.commit();
	}
}

