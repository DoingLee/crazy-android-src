package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public class ManageKind extends FragmentActivity
		implements Callbacks
{
	@Override
	public Fragment getFragment()
	{
		return new ManageKindFragment();
	}
	@Override
	public void onItemSelected(Integer id, Bundle bundle)
	{
		// 当用户单击ManageKindFragment中添加按钮时，启动AddKind Activity
		Intent i = new Intent(this , AddKind.class);
		startActivity(i);
	}
}
