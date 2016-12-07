package org.crazyit.map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;


public class MainActivity extends Activity
	implements OnClickListener
{
	Button parseBn, reverseBn;
	EditText etLng, etLat, etAddress, etResult;
	GeocodeSearch search;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取界面中的可视化组件
		parseBn = (Button) findViewById(R.id.parse);
		reverseBn = (Button) findViewById(R.id.reverse);
		etLng = (EditText) findViewById(R.id.lng);
		etLat = (EditText) findViewById(R.id.lat);
		etAddress = (EditText) findViewById(R.id.address);
		etResult = (EditText) findViewById(R.id.result);
		parseBn.setOnClickListener(this);
		reverseBn.setOnClickListener(this);
		// 创建GeocodeSearch对象
		search = new GeocodeSearch(this);
		// 设置解析监听器
		search.setOnGeocodeSearchListener(new OnGeocodeSearchListener()  // ①
		{
			@Override
			public void onRegeocodeSearched(RegeocodeResult
				regeocodeResult, int i)
			{
				RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
				etResult.setText("经度:" + etLng.getText() + "、纬度:"
					+ etLat.getText() + "的地址为：\n" + addr.getFormatAddress());
			}

			@Override
			public void onGeocodeSearched(GeocodeResult
				geocodeResult, int i)
			{
				GeocodeAddress addr = geocodeResult.getGeocodeAddressList().get(0);
				LatLonPoint latlng = addr.getLatLonPoint();
				etResult.setText(etAddress.getText() + "的经度是:" +
					latlng.getLongitude() + "、纬度是:" + latlng.getLatitude());
			}
		});
	}
	@Override
	public void onClick(View source)
	{
		switch (source.getId())
		{
			// 单击了“解析”按钮
			case R.id.parse:
				String address = etAddress.getText().toString().trim();
				if (address.equals(""))
				{
					Toast.makeText(this, "请输入有效的地址"
						, Toast.LENGTH_LONG).show();
				}
				else
				{
					GeocodeQuery query = new GeocodeQuery(address, "广州");
					// 根据地理名称执行异步解析
					search.getFromLocationNameAsyn(query);
				}
				break;
			// 单击了“反向解析”按钮
			case R.id.reverse:
				String lng = etLng.getText().toString().trim();
				String lat = etLat.getText().toString().trim();
				if (lng.equals("") || lat.equals(""))
				{
					Toast.makeText(this, "请输入有效的经度、纬度!"
						, Toast.LENGTH_LONG)
						.show();
				}
				else
				{
					// 根据经纬度执行异步查询
					search.getFromLocationAsyn(new RegeocodeQuery(
						new LatLonPoint(Double.parseDouble(lat)
						, Double.parseDouble(lng))
						, 20 // 区域半径
						, GeocodeSearch.GPS));
				}
				break;
		}
	}
}
