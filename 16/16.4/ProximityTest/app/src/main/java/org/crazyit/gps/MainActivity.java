package org.crazyit.gps;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 定位服务的常量
		String locService = Context.LOCATION_SERVICE;
		// 定位服务管理器实例
		LocationManager locationManager;
		// 通过getSystemService方法获得LocationManager实例
		locationManager = (LocationManager) getSystemService(locService);
		// 定义"疯狂软件教育中心"的经度、纬度
		double longitude = 113.401863;
		double latitude = 23.132636;
		// 定义半径（5公里）
		float radius = 5000;
		// 定义Intent
		Intent intent = new Intent(this, ProximityAlertReciever.class);
		// 将Intent包装成PendingIntent
		PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);
		// 添加临近警告
		locationManager.addProximityAlert(latitude, longitude, radius, -1, pi);
	}
}
