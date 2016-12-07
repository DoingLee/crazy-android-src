package org.crazyit.auction.client;

import java.util.HashMap;
import java.util.Map;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddBidFragment extends Fragment
{
	// 定义界面中文本框
	TextView itemName, itemDesc,itemRemark,itemKind
			,initPrice , maxPrice ,endTime;
	EditText bidPrice;
	// 定义界面中两个按钮
	Button bnAdd, bnCancel;
	// 定义当前正在拍卖的物品
	JSONObject jsonObj;
	@Override
	public View onCreateView(LayoutInflater inflater
			, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.add_bid
				, container , false);
		// 获取界面中编辑框
		itemName = (TextView)rootView.findViewById(R.id.itemName);
		itemDesc = (TextView)rootView.findViewById(R.id.itemDesc);
		itemRemark = (TextView)rootView.findViewById(R.id.itemRemark);
		itemKind = (TextView)rootView.findViewById(R.id.itemKind);
		initPrice = (TextView)rootView.findViewById(R.id.initPrice);
		maxPrice = (TextView)rootView.findViewById(R.id.maxPrice);
		endTime = (TextView)rootView.findViewById(R.id.endTime);
		bidPrice = (EditText)rootView.findViewById(R.id.bidPrice);
		// 获取界面中的两个按钮
		bnAdd = (Button)rootView.findViewById(R.id.bnAdd);
		bnCancel = (Button)rootView.findViewById(R.id.bnCancel);
		// 为取消按钮的单击事件绑定事件监听器
		bnCancel.setOnClickListener(new HomeListener(getActivity()));
		// 定义发送请求的URL
		String url = HttpUtil.BASE_URL + "getItem.jsp?itemId="
				+ getArguments().getInt("itemId");
		try
		{
			// 获取指定的拍卖物品
			jsonObj = new JSONObject(HttpUtil.getRequest(url));
			// 使用文本框来显示拍卖物品的详情
			itemName.setText(jsonObj.getString("name"));
			itemDesc.setText(jsonObj.getString("desc"));
			itemRemark.setText(jsonObj.getString("remark"));
			itemKind.setText(jsonObj.getString("kind"));
			initPrice.setText(jsonObj.getString("initPrice"));
			maxPrice.setText(jsonObj.getString("maxPrice"));
			endTime.setText(jsonObj.getString("endTime"));
		}
		catch (Exception e1)
		{
			DialogUtil.showDialog(getActivity(), "服务器响应出现异常！", false);
			e1.printStackTrace();
		}
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					// 执行类型转换
					double curPrice = Double.parseDouble(
							bidPrice.getText().toString());
					// 执行输入校验
					if(curPrice < jsonObj.getDouble("maxPrice"))  // ①
					{
						DialogUtil.showDialog(getActivity(),
								"您输入的竞价必须高于当前竞价", false);
					}
					else
					{
						// 添加竞价
						String result = addBid(jsonObj.getString("id")
								, curPrice + "");  // ②
						// 显示对话框
						DialogUtil.showDialog(getActivity()
								, result , true);
					}
				}
				catch(NumberFormatException ne)
				{
					DialogUtil.showDialog(getActivity()
							, "您输入的竞价必须是数值", false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					DialogUtil.showDialog(getActivity()
							, "服务器响应出现异常，请重试！", false);
				}
			}
		});
		return rootView;
	}

	private String addBid(String itemId , String bidPrice)
			throws Exception
	{
		// 使用Map封装请求参数
		Map<String , String> map = new HashMap<>();
		map.put("itemId" , itemId);
		map.put("bidPrice" , bidPrice);
		// 定义请求将会发送到addKind.jsp页面
		String url = HttpUtil.BASE_URL + "addBid.jsp";
		// 发送请求
		return HttpUtil.postRequest(url , map);
	}
}