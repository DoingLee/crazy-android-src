package org.crazyit.auction.client;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ViewBidFragment extends Fragment
{
	Button bnHome;
	ListView bidList;

	@Override
	public View onCreateView(LayoutInflater inflater
			, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.view_bid
				, container , false);
		// 获取界面上的返回按钮
		bnHome = (Button) rootView.findViewById(R.id.bn_home);
		bidList = (ListView) rootView.findViewById(R.id.bidList);
		// 为返回按钮的单击事件绑定事件监听器
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		// 定义发送请求的URL
		String url = HttpUtil.BASE_URL + "viewBid.jsp";
		try
		{
			// 向指定URL发送请求，并把服务器响应包装成JSONArray对象
			JSONArray jsonArray = new JSONArray(HttpUtil.getRequest(url));
			// 将JSONArray包装成Adapter
			JSONArrayAdapter adapter = new JSONArrayAdapter(getActivity()
					, jsonArray, "item", true);
			bidList.setAdapter(adapter);
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity(), "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}
		bidList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id)
			{
				// 查看竞价详情
				viewBidDetail(position);
			}
		});
		return rootView;
	}

	private void viewBidDetail(int position)
	{
		// 加载bid_detail.xml界面布局代表的视图
		View detailView = getActivity().getLayoutInflater()
				.inflate(R.layout.bid_detail, null);
		// 获取bid_detail界面中的文本框
		TextView itemName = (TextView) detailView
				.findViewById(R.id.itemName);
		TextView bidPrice = (TextView) detailView
				.findViewById(R.id.bidPrice);
		TextView bidTime = (TextView) detailView
				.findViewById(R.id.bidTime);
		TextView bidUser = (TextView) detailView
				.findViewById(R.id.bidUser);
		// 获取被单击项目所包装的JSONObject
		JSONObject jsonObj = (JSONObject) bidList.getAdapter()
				.getItem(position);
		try
		{
			// 使用文本框来显示竞价详情。
			itemName.setText(jsonObj.getString("item"));
			bidPrice.setText(jsonObj.getString("price"));
			bidTime.setText(jsonObj.getString("bidDate"));
			bidUser.setText(jsonObj.getString("user"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		DialogUtil.showDialog(getActivity(), detailView);
	}
}