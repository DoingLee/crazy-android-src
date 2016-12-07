package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public class ManageItem extends FragmentActivity
		implements Callbacks
{
	@Override
	public Fragment getFragment()
	{
		return new ManageItemFragment();
	}
	@Override
	public void onItemSelected(Integer id, Bundle bundle)
	{
		// 当用户单击添加按钮时，将会启动AddItem Activity
		Intent i = new Intent(this , AddItem.class);
		startActivity(i);
	}
}