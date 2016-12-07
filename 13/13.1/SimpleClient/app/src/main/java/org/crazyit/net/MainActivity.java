package org.crazyit.net;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends Activity
{
	EditText show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		show = (EditText) findViewById(R.id.show);
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					// 建立连接到远程服务器的Socket
					Socket socket = new Socket("192.168.1.88" , 30000);  // ①
					// 将Socket对应的输入流包装成BufferedReader
					BufferedReader br = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					// 进行普通I/O操作
					String line = br.readLine();
					show.setText("来自服务器的数据：" + line);
					// 关闭输入流、socket
					br.close();
					socket.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}.start();
	}
}


