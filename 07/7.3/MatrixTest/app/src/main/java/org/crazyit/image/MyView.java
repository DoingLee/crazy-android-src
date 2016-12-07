package org.crazyit.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

public class MyView extends View
{
	// 初始的图片资源
	private Bitmap bitmap;
	// Matrix 实例
	private Matrix matrix = new Matrix();
	// 设置倾斜度
	private float sx = 0.0f;
	// 位图宽和高
	private int width, height;
	// 缩放比例
	private float scale = 1.0f;
	// 判断缩放还是旋转
	private boolean isScale = false;
	public MyView(Context context , AttributeSet set)
	{
		super(context , set);
		// 获得位图
		bitmap = ((BitmapDrawable) context.getResources().getDrawable(
			R.drawable.a)).getBitmap();
		// 获得位图宽
		width = bitmap.getWidth();
		// 获得位图高
		height = bitmap.getHeight();
		// 使当前视图获得焦点
		this.setFocusable(true);
	}
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// 重置Matrix
		matrix.reset();
		if (!isScale)
		{
			// 旋转Matrix
			matrix.setSkew(sx, 0);
		}
		else
		{
			// 缩放Matrix
			matrix.setScale(scale, scale);
		}
		// 根据原始位图和Matrix创建新图片
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		// 绘制新位图
		canvas.drawBitmap(bitmap2, matrix, null);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch(keyCode)
		{
			// 向左倾斜
			case KeyEvent.KEYCODE_A:
				isScale = false;
				sx += 0.1;
				postInvalidate();
				break;
			// 向右倾斜
			case KeyEvent.KEYCODE_D:
				isScale = false;
				sx -= 0.1;
				postInvalidate();
				break;
			// 放大
			case KeyEvent.KEYCODE_W:
				isScale = true;
				if (scale < 2.0)
					scale += 0.1;
				postInvalidate();
				break;
			// 缩小
			case KeyEvent.KEYCODE_S:
				isScale = true;
				if (scale > 0.5)
					scale -= 0.1;
				postInvalidate();
				break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
