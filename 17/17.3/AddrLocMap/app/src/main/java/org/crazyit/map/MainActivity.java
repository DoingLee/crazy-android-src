package org.crazyit.map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;

public class MainActivity extends Activity
	implements GeocodeSearch.OnGeocodeSearchListener
{
	private MapView mapView;
	private AMap aMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mapView = (MapView) findViewById(R.id.map);
		// 必须回调MapView的onCreate()方法
		mapView.onCreate(savedInstanceState);
		init();
		Button bn = (Button) findViewById(R.id.loc);
		final TextView addrTv = (TextView) findViewById(R.id.address);
		bn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String addr = addrTv.getText().toString();
				if (addr.equals(""))
				{
					Toast.makeText(MainActivity.this, "请输入有效的地址"
							, Toast.LENGTH_LONG).show();
				}
				else
				{
					GeocodeSearch search = new GeocodeSearch(MainActivity.this);
					search.setOnGeocodeSearchListener(MainActivity.this);
					GeocodeQuery query = new GeocodeQuery(addr, "广州");
					// 根据地址执行异步地址解析
					search.getFromLocationNameAsyn(query);  // ①
				}
			}
		});
	}

	// 初始化AMap对象
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			// 创建一个设置放大级别的CameraUpdate
			CameraUpdate cu = CameraUpdateFactory.zoomTo(16);
			// 设置地图的默认放大级别
			aMap.moveCamera(cu);
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		// 必须回调MapView的onResume()方法
		mapView.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
		// 必须回调MapView的onPause()方法
		mapView.onPause();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// 必须回调MapView的onSaveInstanceState()方法
		mapView.onSaveInstanceState(outState);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 必须回调MapView的onDestroy()方法
		mapView.onDestroy();
	}

	@Override
	public void onRegeocodeSearched(RegeocodeResult
		regeocodeResult, int i){}

	@Override
	public void onGeocodeSearched(GeocodeResult geocodeResult, int i)
	{
		// 获取解析得到的第一个地址
		GeocodeAddress geo = geocodeResult.getGeocodeAddressList().get(0);
		// 获取解析得到的经纬度
		LatLonPoint pos = geo.getLatLonPoint();
		LatLng targetPos = new LatLng(pos.getLatitude(), pos.getLongitude());
		// 创建一个设置经纬度的CameraUpdate
		CameraUpdate cu = CameraUpdateFactory.changeLatLng(targetPos);
		// 更新地图的显示区域
		aMap.moveCamera(cu);
		// 创建一个GroundOverlayOptions（用于向地图上添加图片)
		GroundOverlayOptions options = new GroundOverlayOptions()
			// 设置GroundOverlayOptions包装的图片
			.image(BitmapDescriptorFactory
				.fromResource(R.drawable.ic_launcher))
			.position(targetPos , 64);
		// 添加图片
		aMap.addGroundOverlay(options);
		// 创建一个CircleOptions（用于向地图上添加圆形）
		CircleOptions cOptions = new CircleOptions()
			.center(targetPos)  // 设置圆心
			.fillColor(0x80ffff00)  // 设置圆形的填充颜色
			.radius(80)  // 设置圆形的半径
			.strokeWidth(1)  // 设置圆形的线条宽度
			.strokeColor(0xff000000);  // 设置圆形的线条颜色
		aMap.addCircle(cOptions);
	}
	private void GroundOverlayOptions(){}
}
