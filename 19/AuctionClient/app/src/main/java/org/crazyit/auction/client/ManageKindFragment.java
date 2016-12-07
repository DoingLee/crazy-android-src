package org.crazyit.auction.client;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;

import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ManageKindFragment extends Fragment
{
	public static final int ADD_KIND = 0x1007;
	Button bnHome , bnAdd;
	ListView kindList;
	Callbacks mCallbacks;
	@Override
	public View onCreateView(LayoutInflater inflater
			, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.manage_kind
				, container , false);
		// 获取界面布局上的两个按钮
		bnHome = (Button) rootView.findViewById(R.id.bn_home);
		bnAdd = (Button) rootView.findViewById(R.id.bnAdd);
		kindList = (ListView) rootView.findViewById(R.id.kindList);
		// 为返回按钮的单击事件绑定事件监听器
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		// 为添加按钮的单击事件绑定事件监听器
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// 当添加按钮被单击时，
				// 调用该Fragment所在Activity的onItemSelected方法
				mCallbacks.onItemSelected(ADD_KIND , null);
			}
		});
		// 定义发送请求的URL
		String url = HttpUtil.BASE_URL + "viewKind.jsp";
		try
		{
			// 向指定URL发送请求，并把响应包装成JSONArray对象
			final JSONArray jsonArray = new JSONArray(
					HttpUtil.getRequest(url));
			// 把JSONArray对象包装成Adapter
			kindList.setAdapter(new KindArrayAdapter(jsonArray
					, getActivity()));
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity()
					, "服务器响应异常，请稍后再试！" ,false);
			e.printStackTrace();
		}
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
					"ManageKindFragment所在的Activity必须实现Callbacks接口!");
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
}