package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;

public class AddItem extends FragmentActivity
{
	@Override
	public Fragment getFragment()
	{
		return new AddItemFragment();
	}
}