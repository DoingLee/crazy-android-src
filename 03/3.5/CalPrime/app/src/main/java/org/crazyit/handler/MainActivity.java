package org.crazyit.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
	static final String UPPER_NUM = "upper";
	EditText etNum;
	CalThread calThread;
	// 定义一个线程类
	class CalThread extends Thread
	{
		public Handler mHandler;
		public void run()
		{
			Looper.prepare();
			mHandler = new Handler()
			{
				// 定义处理消息的方法
				@Override
				public void handleMessage(Message msg)
				{
					if(msg.what == 0x123)
					{
						int upper = msg.getData().getInt(UPPER_NUM);
						List<Integer> nums = new ArrayList<Integer>();
						// 计算从2开始、到upper的所有质数
						outer:
						for (int i = 2 ; i <= upper ; i++)
						{
							// 用i除以从2开始、到i的平方根的所有数
							for (int j = 2 ; j <= Math.sqrt(i) ; j++)
							{
								// 如果可以整除，则表明这个数不是质数
								if(i != 2 && i % j == 0)
								{
									continue outer;
								}
							}
							nums.add(i);
						}
						// 使用Toast显示统计出来的所有质数
						Toast.makeText(MainActivity.this, nums.toString()
							, Toast.LENGTH_LONG).show();
					}
				}
			};
			Looper.loop();
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		etNum = (EditText)findViewById(R.id.etNum);
		calThread = new CalThread();
		// 启动新线程
		calThread.start();
	}
	// 为按钮的点击事件提供事件处理方法
	public void cal(View source)
	{
		// 创建消息
		Message msg = new Message();
		msg.what = 0x123;
		Bundle bundle = new Bundle();
		bundle.putInt(UPPER_NUM ,
				Integer.parseInt(etNum.getText().toString()));
		msg.setData(bundle);
		// 向新线程中的Handler发送消息
		calThread.mHandler.sendMessage(msg);
	}
}

