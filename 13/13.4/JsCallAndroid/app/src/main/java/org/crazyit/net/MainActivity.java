package org.crazyit.net;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity
{
	WebView myWebView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myWebView = (WebView) findViewById(R.id.webview);
		// 此处为了简化编程，使用file协议加载本地assets目录下的HTML页面
		// 如果有需要，也可使用http协议加载远程网站的HTML页面
		myWebView.loadUrl("file:///android_asset/test.html");
		// 获取WebView的设置对象
		WebSettings webSettings = myWebView.getSettings();
		// 开启JavaScript调用
		webSettings.setJavaScriptEnabled(true);
		// 将MyObject对象暴露给JavaScript脚本
		// 这样test.html页面中的JavaScript可以通过myObj来调用MyObject的方法
		myWebView.addJavascriptInterface(new MyObject(this), "myObj");
	}
}
