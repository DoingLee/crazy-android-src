package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public class ChooseItem extends FragmentActivity
	implements Callbacks
{
	@Override
	public Fragment getFragment()
	{
		ChooseItemFragment fragment = new ChooseItemFragment();
		Bundle args = new Bundle();
		args.putLong("kindId", getIntent()
			.getLongExtra("kindId", -1));
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onItemSelected(Integer id, Bundle bundle)
	{
		Intent intent = new Intent(this , AddBid.class);
		intent.putExtra("itemId", bundle.getInt("itemId"));
		startActivity(intent);
	}
}
