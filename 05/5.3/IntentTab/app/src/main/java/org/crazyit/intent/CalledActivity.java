package org.crazyit.intent;

import android.app.Activity;
import android.os.Bundle;

public class CalledActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.called);
	}
}
