package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.os.Bundle;

public class AddBid extends FragmentActivity
{
	@Override
	public Fragment getFragment()
	{
		AddBidFragment fragment = new AddBidFragment();
		Bundle args = new Bundle();
		args.putInt("itemId", getIntent()
			.getIntExtra("itemId", -1));
		fragment.setArguments(args);
		return fragment;
	}
}
