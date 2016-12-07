package org.crazyit.desktop;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

public class DesktopApp extends AppWidgetProvider
{
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
						 int[] appWidgetIds)
	{
		// 加载指定界面布局文件，创建RemoteViews对象
		RemoteViews remoteViews = new RemoteViews(
				context.getPackageName(),
				R.layout.main);  // ①
		// 为show ImageView设置图片
		remoteViews.setImageViewResource(R.id.show
				, R.drawable.logo);   // ②
		// 将AppWidgetProvider子类实例包装成ComponentName对象
		ComponentName componentName = new ComponentName(
				context, DesktopApp.class);  // ③
		// 调用AppWidgetManager将remoteViews添加到ComponentName中
		appWidgetManager.updateAppWidget(componentName
				, remoteViews);  // ④
	}
}
