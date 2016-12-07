package org.crazyit.net;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


public class MainActivity extends Activity
{
	ImageView show;
	// 代表从网络下载得到的图片
	Bitmap bitmap;
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if(msg.what == 0x123)
			{
				// 使用ImageView显示该图片
				show.setImageBitmap(bitmap);
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		show = (ImageView) findViewById(R.id.show);
		new Thread()
		{
			public void run()
			{
				try
				{
					// 定义一个URL对象
					URL url = new URL("http://www.crazyit.org/"
							+ "attachments/month_1008/20100812_7763e970f"
							+ "822325bfb019ELQVym8tW3A.png");
					// 打开该URL对应的资源的输入流
					InputStream is = url.openStream();
					// 从InputStream中解析出图片
					bitmap = BitmapFactory.decodeStream(is);
					// 发送消息、通知UI组件显示该图片
					handler.sendEmptyMessage(0x123);
					is.close();
					// 再次打开URL对应的资源的输入流
					is = url.openStream();
					// 打开手机文件对应的输出流
					OutputStream os = openFileOutput("crazyit.png"
						, MODE_PRIVATE);
					byte[] buff = new byte[1024];
					int hasRead = 0;
					// 将URL对应的资源下载到本地
					while((hasRead = is.read(buff)) > 0)
					{
						os.write(buff, 0 , hasRead);
					}
					is.close();
					os.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}.start();
	}
}

