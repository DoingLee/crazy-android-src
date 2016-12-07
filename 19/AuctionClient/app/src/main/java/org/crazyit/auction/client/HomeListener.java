package org.crazyit.auction.client;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeListener implements OnClickListener
{
	private Activity activity;
	public HomeListener(Activity activity)
	{
		this.activity = activity;
	}
	@Override
	public void onClick(View source)
	{
		Intent i = new Intent(activity , AuctionClientActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(i);
	}
}

