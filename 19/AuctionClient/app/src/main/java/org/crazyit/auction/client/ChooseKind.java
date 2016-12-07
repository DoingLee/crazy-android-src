package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public class ChooseKind extends FragmentActivity
	implements Callbacks
{
	@Override
	public Fragment getFragment()
	{
		return new ChooseKindFragment();
	}
	@Override
	public void onItemSelected(Integer id, Bundle bundle)
	{
		Intent intent = new Intent(this , ChooseItem.class);
		intent.putExtra("kindId", bundle.getLong("kindId"));
		startActivity(intent);
	}
}
