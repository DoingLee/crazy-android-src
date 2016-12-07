package org.crazyit.desktop;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService
{
	// 重写该方法，该方法返回一个RemoteViewsFactory对象
	// RemoteViewsFactory对象的作用类似于Adapter
	// 它负责为RemoteView中的指定组件提供多个列表项
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent)
	{
		return new StackRemoteViewsFactory(this.getApplicationContext(),
			intent);  // ①
	}
	class StackRemoteViewsFactory implements
			RemoteViewsService.RemoteViewsFactory
	{
		// 定义一个数组来保存该组件生成的多个列表项
		private int[] items = null;
		private Context mContext;
		public StackRemoteViewsFactory(Context context, Intent intent)
		{
			mContext = context;
		}
		@Override
		public void onCreate()
		{
			// 初始化items数组
			items = new int[] { R.drawable.bomb5, R.drawable.bomb6,
					R.drawable.bomb7, R.drawable.bomb8, R.drawable.bomb9,
					R.drawable.bomb10, R.drawable.bomb11, R.drawable.bomb12,
					R.drawable.bomb13, R.drawable.bomb14, R.drawable.bomb15,
					R.drawable.bomb16
			};
		}
		@Override
		public void onDestroy()
		{
			items = null;
		}
		// 该方法的返回值控制该对象包含多少个列表项
		@Override
		public int getCount()
		{
			return items.length;
		}
		// 该方法的返回值控制各位置所显示的RemoteViews
		@Override
		public RemoteViews getViewAt(int position)
		{
			// 创建RemoteViews对象，加载/res/layout目录下的widget_item.xml文件
			RemoteViews rv = new RemoteViews(mContext.getPackageName(),
					R.layout.widget_item);
			// 更新widget_item.xml布局文件中的widget_item组件
			rv.setImageViewResource(R.id.widget_item,
					items[position]);
			// 创建Intent、用于传递数据
			Intent fillInIntent = new Intent();
			fillInIntent.putExtra(StackWidgetProvider.EXTRA_ITEM, position);
			// 设置当单击该RemoteViews时传递fillInIntent包含的数据
			rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
			// 此处让线程暂停0.5秒来模拟加载该组件
			try
			{
				System.out.println("加载【" + position + "】位置的组件");
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			return rv;
		}
		@Override
		public RemoteViews getLoadingView()
		{
			return null;
		}
		@Override
		public int getViewTypeCount()
		{
			return 1;
		}
		@Override
		public long getItemId(int position)
		{
			return position;
		}
		@Override
		public boolean hasStableIds()
		{
			return true;
		}
		@Override
		public void onDataSetChanged()
		{
		}
	}
}
