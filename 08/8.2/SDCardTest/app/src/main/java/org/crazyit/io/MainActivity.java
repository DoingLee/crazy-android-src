package org.crazyit.io;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;


public class MainActivity extends Activity
{
	final String FILE_NAME = "/crazyit.bin";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取两个按钮
		Button read = (Button) findViewById(R.id.read);
		Button write = (Button) findViewById(R.id.write);
		// 获取两个文本框
		final EditText edit1 = (EditText) findViewById(R.id.edit1);
		final EditText edit2 = (EditText) findViewById(R.id.edit2);
		// 为write按钮绑定事件监听器
		write.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// 将edit1中的内容写入文件中
				write(edit1.getText().toString());
				edit1.setText("");
			}
		});
		read.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 读取指定文件中的内容，并显示出来
				edit2.setText(read());
			}
		});
	}
	private String read()
	{
		try
		{
			// 如果手机插入了SD卡，而且应用程序具有访问SD的权限
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED))
			{
				// 获取SD卡对应的存储目录
				File sdCardDir = Environment.getExternalStorageDirectory();
				System.out.println("----------------" + sdCardDir);
				// 获取指定文件对应的输入流
				FileInputStream fis = new FileInputStream(
						sdCardDir.getCanonicalPath() + FILE_NAME);
				// 将指定输入流包装成BufferedReader
				BufferedReader br = new BufferedReader(new
						InputStreamReader(fis));
				StringBuilder sb = new StringBuilder("");
				String line = null;
				// 循环读取文件内容
				while ((line = br.readLine()) != null)
				{
					sb.append(line);
				}
				// 关闭资源
				br.close();
				return sb.toString();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	private void write(String content)
	{
		try
		{
			// 如果手机插入了SD卡，而且应用程序具有访问SD的权限
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED))
			{
				// 获取SD卡的目录
				File sdCardDir = Environment.getExternalStorageDirectory();
				File targetFile = new File(sdCardDir
						.getCanonicalPath() + FILE_NAME);
				// 以指定文件创建 RandomAccessFile对象
				RandomAccessFile raf = new RandomAccessFile(
						targetFile, "rw");
				// 将文件记录指针移动到最后
				raf.seek(targetFile.length());
				// 输出文件内容
				raf.write(content.getBytes());
				// 关闭RandomAccessFile
				raf.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
