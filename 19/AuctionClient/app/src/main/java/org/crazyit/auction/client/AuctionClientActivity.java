package org.crazyit.auction.client;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public class AuctionClientActivity extends Activity
		implements Callbacks
{
	// 定义一个旗标，用于标识该应用是否支持大屏幕
	private boolean mTwoPane;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 指定加载R.layout.activity_main对应的界面布局文件
		// 但实际上该应用会根据屏幕分辨率家在不同的界面布局文件
		setContentView(R.layout.activity_main);
		// 如果加载的界面布局文件中包含ID为auction_detail_container的组件
		if (findViewById(R.id.auction_detail_container) != null)
		{
			mTwoPane = true;
			((AuctionListFragment) getFragmentManager()
					.findFragmentById(R.id.auction_list))
					.setActivateOnItemClick(true);
		}
	}
	@Override
	public void onItemSelected(Integer id , Bundle bundle)
	{
		if (mTwoPane)
		{
			Fragment fragment = null;
			switch ((int) id)
			{
				// 查看竞得物品
				case 0:
					// 创建ViewItemFragment
					fragment = new ViewItemFragment();
					// 创建Bundle，准备向Fragment传入参数
					Bundle arguments = new Bundle();
					arguments.putString("action", "viewSucc.jsp");
					// 向Fragment传入参数
					fragment.setArguments(arguments);
					break;
				// 浏览流拍物品
				case 1:
					// 创建ViewItemFragment
					fragment = new ViewItemFragment();
					// 创建Bundle，准备向Fragment传入参数
					Bundle arguments2 = new Bundle();
					arguments2.putString("action", "viewFail.jsp");
					// 向Fragment传入参数
					fragment.setArguments(arguments2);
					break;
				// 管理物品种类
				case 2:
					// 创建ManageKindFragment
					fragment = new ManageKindFragment();
					break;
				// 管理物品
				case 3:
					// 创建ManageItemFragment
					fragment = new ManageItemFragment();
					break;
				// 浏览拍卖物品（选择物品种类）
				case 4:
					// 创建ChooseKindFragment
					fragment = new ChooseKindFragment();
					break;
				// 查看自己的竞标
				case 5:
					// 创建ViewBidFragment
					fragment = new ViewBidFragment();
					break;
				case ManageItemFragment.ADD_ITEM:
					fragment = new AddItemFragment();
					break;
				case ManageKindFragment.ADD_KIND:
					fragment = new AddKindFragment();
					break;
				case ChooseKindFragment.CHOOSE_ITEM:
					fragment = new ChooseItemFragment();
					Bundle args = new Bundle();
					args.putLong("kindId", bundle.getLong("kindId"));
					fragment.setArguments(args);
					break;
				case ChooseItemFragment.ADD_BID:
					fragment = new AddBidFragment();
					Bundle args2 = new Bundle();
					args2.putInt("itemId", bundle.getInt("itemId"));
					fragment.setArguments(args2);
					break;
			}
			// 使用fragment替换auction_detail_container容器当前显示的Fragment
			getFragmentManager().beginTransaction()
					.replace(R.id.auction_detail_container, fragment)
					.addToBackStack(null).commit();
		}
		else
		{
			Intent intent = null;
			switch ((int) id)
			{
				// 查看竞得物品
				case 0:
					// 启动ViewItem Activity
					intent = new Intent(this, ViewItem.class);
					// action属性为请求的Servlet地址。
					intent.putExtra("action", "viewSucc.jsp");
					startActivity(intent);
					break;
				// 浏览流拍物品
				case 1:
					// 启动ViewItem Activity
					intent = new Intent(this, ViewItem.class);
					// action属性为请求的Servlet的URL。
					intent.putExtra("action", "viewFail.jsp");
					startActivity(intent);
					break;
				// 管理物品种类
				case 2:
					// 启动ManageKind Activity
					intent = new Intent(this, ManageKind.class);
					startActivity(intent);
					break;
				// 管理物品
				case 3:
					// 启动ManageItem Activity
					intent = new Intent(this, ManageItem.class);
					startActivity(intent);
					break;
				// 浏览拍卖物品（选择物品种类）
				case 4:
					// 启动ChooseKind Activity
					intent = new Intent(this, ChooseKind.class);
					startActivity(intent);
					break;
				// 查看自己的竞标
				case 5:
					// 启动ViewBid Activity
					intent = new Intent(this, ViewBid.class);
					startActivity(intent);
					break;
			}
		}
	}
}
