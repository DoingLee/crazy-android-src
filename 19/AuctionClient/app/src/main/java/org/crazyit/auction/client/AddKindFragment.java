package org.crazyit.auction.client;

import java.util.HashMap;
import java.util.Map;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddKindFragment extends Fragment
{
	// 定义界面中两个文本框
	EditText kindName, kindDesc;
	// 定义界面中两个按钮
	Button bnAdd, bnCancel;
	@Override
	public View onCreateView(LayoutInflater inflater
			, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.add_kind
				, container , false);
		// 获取界面中两个编辑框
		kindName = (EditText)rootView.findViewById(R.id.kindName);
		kindDesc = (EditText)rootView.findViewById(R.id.kindDesc);
		// 获取界面中的两个按钮
		bnAdd = (Button)rootView.findViewById(R.id.bnAdd);
		bnCancel = (Button)rootView.findViewById(R.id.bnCancel);
		// 为取消按钮的单击事件绑定事件监听器
		bnCancel.setOnClickListener(new HomeListener(getActivity()));
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 输入校验
				if (validate())
				{
					// 获取用户输入的种类名、种类描述
					String name = kindName.getText().toString();
					String desc = kindDesc.getText().toString();
					try
					{
						// 添加物品种类
						String result =  addKind(name, desc);
						// 使用对话框来显示添加结果
						DialogUtil.showDialog(getActivity()
								, result , true);
					}
					catch (Exception e)
					{
						DialogUtil.showDialog(getActivity()
								, "服务器响应异常，请稍后再试！" , false);
						e.printStackTrace();
					}
				}
			}
		});
		return rootView;
	}

	// 对用户输入的种类名称进行校验
	private boolean validate()
	{
		String name = kindName.getText().toString().trim();
		if (name.equals(""))
		{
			DialogUtil.showDialog(getActivity() , "种类名称是必填项！" , false);
			return false;
		}
		return true;
	}

	private String addKind(String name, String desc)
			throws Exception
	{
		// 使用Map封装请求参数
		Map<String , String> map = new HashMap<>();
		map.put("kindName" , name);
		map.put("kindDesc" , desc);
		// 定义发送请求的URL
		String url = HttpUtil.BASE_URL + "addKind.jsp";
		// 发送请求
		return HttpUtil.postRequest(url , map);
	}
}