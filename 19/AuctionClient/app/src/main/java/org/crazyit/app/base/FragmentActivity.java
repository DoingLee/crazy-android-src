package org.crazyit.app.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.LinearLayout;

public abstract class FragmentActivity extends Activity
{
	private static final int ROOT_CONTAINER_ID = 0x90001;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		setContentView(layout);
		layout.setId(ROOT_CONTAINER_ID);
		getFragmentManager().beginTransaction()
			.replace(ROOT_CONTAINER_ID , getFragment())
			.commit();
	}
	protected abstract Fragment getFragment();
}
