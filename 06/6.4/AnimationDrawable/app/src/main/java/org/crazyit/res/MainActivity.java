package org.crazyit.res;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageView image = (ImageView)findViewById(R.id.image);
		// 加载动画资源
		final Animation anim = AnimationUtils.loadAnimation(this,
				R.anim.my_anim);
		// 设置动画结束后保留结束状态
		anim.setFillAfter(true);  // ①
		Button bn = (Button) findViewById(R.id.bn);
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 开始动画
				image.startAnimation(anim);
			}
		});
	}
}
