package org.crazyit.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

public class MyButton extends Button
{
	public MyButton(Context context, AttributeSet set)
	{
		super(context, set);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		Log.v("-crazyit.org-", "the onKeyDown in MyButton");
		// 返回true，表明该事件不会向外扩散
		return true;
	}
}
