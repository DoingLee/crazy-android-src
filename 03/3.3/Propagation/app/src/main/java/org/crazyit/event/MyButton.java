package org.crazyit.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

public class MyButton extends Button
{
	public MyButton(Context context , AttributeSet set)
	{
		super(context , set);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode , event);
		Log.v("-MyButton-", "the onKeyDown in MyButton");
		// 返回false，表明并未完全处理该事件，该事件依然向外扩散
		return false;
	}
}
