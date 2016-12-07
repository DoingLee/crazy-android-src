package org.crazyit.net;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class MainActivity extends Activity
{
	WebView show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取程序中的WebView组件
		show = (WebView) findViewById(R.id.show);
		StringBuilder sb = new StringBuilder();
		// 拼接一段HTML代码
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> 欢迎您 </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2> 欢迎您访问<a href=\"http://www.crazyit.org\">"
				+ "疯狂Java联盟</a></h2>");
		sb.append("</body>");
		sb.append("</html>");
		// 使用简单的loadData方法会导致乱码，可能是Android API的Bug
		// show.loadData(sb.toString() , "text/html" , "utf-8");
		// 加载、并显示HTML代码
		show.loadDataWithBaseURL(null, sb.toString()
				, "text/html" , "utf-8", null);
	}
}
