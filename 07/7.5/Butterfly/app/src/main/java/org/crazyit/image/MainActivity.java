package org.crazyit.image;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity
{
	// 记录蝴蝶ImageView当前的位置
	private float curX = 0;
	private float curY = 30;
	// 记录蝴蝶ImageView下一个位置的坐标
	float nextX = 0;
	float nextY = 0;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取显示蝴蝶的ImageView组件
		final ImageView imageView = (ImageView)
				findViewById(R.id.butterfly);
		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if (msg.what == 0x123)
				{
					// 横向上一直向右飞
					if (nextX > 320)
					{
						curX = nextX = 0;
					}
					else
					{
						nextX += 8;
					}
					// 纵向上可以随机上下
					nextY = curY + (float) (Math.random() * 10 - 5);
					// 设置显示蝴蝶的ImageView发生位移改变
					TranslateAnimation anim = new TranslateAnimation(
							curX, nextX, curY, nextY);
					curX = nextX;
					curY = nextY;
					anim.setDuration(200);
					// 开始位移动画
					imageView.startAnimation(anim); // ①
				}
			}
		};
		final AnimationDrawable butterfly = (AnimationDrawable)
				imageView.getBackground();
		imageView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 开始播放蝴蝶振翅的逐帧动画
				butterfly.start();  // ②
				// 通过定制器控制每0.2秒运行一次TranslateAnimation动画
				new Timer().schedule(new TimerTask()
				{
					@Override
					public void run()
					{
						handler.sendEmptyMessage(0x123);
					}
				}, 0, 200);
			}
		});
	}
}


