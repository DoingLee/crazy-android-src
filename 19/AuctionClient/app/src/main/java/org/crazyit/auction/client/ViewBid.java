package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;

public class ViewBid extends FragmentActivity
{
	@Override
	protected Fragment getFragment()
	{
		return new ViewBidFragment();
	}
}
