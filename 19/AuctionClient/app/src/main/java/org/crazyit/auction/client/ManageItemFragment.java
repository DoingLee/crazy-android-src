package org.crazyit.auction.client;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ManageItemFragment extends Fragment
{
	public static final int ADD_ITEM = 0x1006;;
	Button bnHome, bnAdd;
	ListView itemList;
	Callbacks mCallbacks;

	@Override
	public View onCreateView(LayoutInflater inflater
			, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.manage_item
				, container , false);
		bnHome = (Button) rootView.findViewById(R.id.bn_home);
		bnAdd = (Button) rootView.findViewById(R.id.bnAdd);
		itemList = (ListView) rootView.findViewById(R.id.itemList);
		// 为返回按钮的单击事件绑定事件监听器
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{

				mCallbacks.onItemSelected(ADD_ITEM , null);
			}
		});
		// 定义发送请求的URL
		String url = HttpUtil.BASE_URL + "viewOwnerItem.jsp";
		try
		{
			// 向指定URL发送请求
			JSONArray jsonArray = new JSONArray(HttpUtil.getRequest(url));
			// 将服务器响应包装成Adapter
			JSONArrayAdapter adapter = new JSONArrayAdapter(getActivity()
					, jsonArray, "name", true);
			itemList.setAdapter(adapter);
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity()
					, "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}
		itemList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id)
			{
				viewItemInBid(position);  // ①
			}
		});
		return rootView;
	}
	// 当该Fragment被添加、显示到Activity时，回调该方法
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		// 如果Activity没有实现Callbacks接口，抛出异常
		if (!(activity instanceof Callbacks))
		{
			throw new IllegalStateException(
					"ManagerItemFragment所在的Activity必须实现Callbacks接口!");
		}
		// 把该Activity当成Callbacks对象
		mCallbacks = (Callbacks) activity;
	}
	// 当该Fragment从它所属的Activity中被删除时回调该方法
	@Override
	public void onDetach()
	{
		super.onDetach();
		// 将mCallbacks赋为null。
		mCallbacks = null;
	}

	private void viewItemInBid(int position)
	{
		// 加载detail_in_bid.xml界面布局代表的视图
		View detailView = getActivity().getLayoutInflater()
				.inflate(R.layout.detail_in_bid, null);
		// 获取detail_in_bid.xml界面中的文本框
		TextView itemName = (TextView) detailView
				.findViewById(R.id.itemName);
		TextView itemKind = (TextView) detailView
				.findViewById(R.id.itemKind);
		TextView maxPrice = (TextView) detailView
				.findViewById(R.id.maxPrice);
		TextView initPrice = (TextView) detailView
				.findViewById(R.id.initPrice);
		TextView endTime = (TextView) detailView
				.findViewById(R.id.endTime);
		TextView itemRemark = (TextView) detailView
				.findViewById(R.id.itemRemark);
		// 获取被单击列表项所包装的JSONObject
		JSONObject jsonObj = (JSONObject) itemList.getAdapter().getItem(
				position);
		try
		{
			// 通过文本框显示物品详情
			itemName.setText(jsonObj.getString("name"));
			itemKind.setText(jsonObj.getString("kind"));
			maxPrice.setText(jsonObj.getString("maxPrice"));
			itemRemark.setText(jsonObj.getString("desc"));
			initPrice.setText(jsonObj.getString("initPrice"));
			endTime.setText(jsonObj.getString("endTime"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		DialogUtil.showDialog(getActivity(), detailView);
	}
}