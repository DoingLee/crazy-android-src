package org.crazyit.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity
{
	private TextView show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		show = (TextView) findViewById(R.id.show);
	}
	// 重写该方法，为界面的按钮提供事件响应方法
	public void download(View source) throws MalformedURLException
	{
		DownTask task = new DownTask(this);
		task.execute(new URL("http://www.crazyit.org/ethos.php"));
	}
	class DownTask extends AsyncTask<URL, Integer, String>
	{
		// 可变长的输入参数，与AsyncTask.exucute()对应
		ProgressDialog pdialog;
		// 定义记录已经读取行的数量
		int hasRead = 0;
		Context mContext;
		public DownTask(Context ctx)
		{
			mContext = ctx;
		}
		@Override
		protected String doInBackground(URL... params)
		{
			StringBuilder sb = new StringBuilder();
			try
			{
				URLConnection conn = params[0].openConnection();
				// 打开conn连接对应的输入流，并将它包装成BufferedReader
				BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()
						, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null)
				{
					sb.append(line + "\n");
					hasRead++;
					publishProgress(hasRead);
				}
				return sb.toString();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// 返回HTML页面的内容
			show.setText(result);
			pdialog.dismiss();
		}
		@Override
		protected void onPreExecute()
		{
			pdialog = new ProgressDialog(mContext);
			// 设置对话框的标题
			pdialog.setTitle("任务正在执行中");
			// 设置对话框显示的内容
			pdialog.setMessage("任务正在执行中，敬请等待...");
			// 设置对话框不能用“取消”按钮关闭
			pdialog.setCancelable(false);
			// 设置该进度条的最大进度值
			pdialog.setMax(202);
			// 设置对话框的进度条风格
			pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// 设置对话框的进度条是否显示进度
			pdialog.setIndeterminate(false);
			pdialog.show();
		}
		@Override
		protected void onProgressUpdate(Integer... values)
		{
			// 更新进度
			show.setText("已经读取了【" + values[0] + "】行！");
			pdialog.setProgress(values[0]);
		}
	}
}


