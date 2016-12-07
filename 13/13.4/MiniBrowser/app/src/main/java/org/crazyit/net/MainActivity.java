package org.crazyit.net;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends Activity
{
	EditText url;
	WebView show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取页面中文本框、WebView组件
		url = (EditText) findViewById(R.id.url);
		show = (WebView) findViewById(R.id.show);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_MENU)
		{
			String urlStr = url.getText().toString();
			// 加载、并显示urlStr对应的网页
			show.loadUrl(urlStr);
			return true;
		}
		return false;
	}
}
