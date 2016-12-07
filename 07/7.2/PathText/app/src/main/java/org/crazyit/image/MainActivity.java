package org.crazyit.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new TextView(this));
	}
	class TextView extends View
	{
		final String DRAW_STR = "疯狂Android讲义";
		Path[] paths = new Path[3];
		Paint paint;
		public TextView(Context context)
		{
			super(context);
			paths[0] = new Path();
			paths[0].moveTo(0, 0);
			for (int i = 1; i <= 20; i++)
			{
				// 生成20个点，随机生成它们的Y坐标，并将它们连成一条Path
				paths[0].lineTo(i * 30, (float) Math.random() * 30);
			}
			paths[1] = new Path();
			RectF rectF = new RectF(0, 0, 600, 360);
			paths[1].addOval(rectF, Path.Direction.CCW);
			paths[2] = new Path();
			paths[2].addArc(rectF, 60, 180);
			// 初始化画笔
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(Color.CYAN);
			paint.setStrokeWidth(1);
		}
		@Override
		protected void onDraw(Canvas canvas)
		{
			canvas.drawColor(Color.WHITE);
			canvas.translate(40, 40);
			// 设置从右边开始绘制（右对齐）
			paint.setTextAlign(Paint.Align.RIGHT);
			paint.setTextSize(20);
			// 绘制路径
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawPath(paths[0], paint);
			paint.setTextSize(40);
			// 沿着路径绘制一段文本
			paint.setStyle(Paint.Style.FILL);
			canvas.drawTextOnPath(DRAW_STR, paths[0], -8, 20, paint);
			// 对Canvas进行坐标变换：画布下移60
			canvas.translate(0, 60);
			// 绘制路径
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawPath(paths[1], paint);
			// 沿着路径绘制一段文本
			paint.setStyle(Paint.Style.FILL);
			canvas.drawTextOnPath(DRAW_STR, paths[1], -20, 20, paint);
			// 对Canvas进行坐标变换： 画布下移360
			canvas.translate(0, 360);
			// 绘制路径
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawPath(paths[2], paint);
			// 沿着路径绘制一段文本
			paint.setStyle(Paint.Style.FILL);
			canvas.drawTextOnPath(DRAW_STR, paths[2], -10, 20, paint);
		}
	}
}

