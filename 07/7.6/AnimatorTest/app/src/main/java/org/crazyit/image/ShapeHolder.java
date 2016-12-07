package org.crazyit.image;

import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.drawable.ShapeDrawable;

public class ShapeHolder
{
	private float x = 0, y = 0;
	private ShapeDrawable shape;
	private int color;
	private RadialGradient gradient;
	private float alpha = 1f;
	private Paint paint;

	public ShapeHolder(ShapeDrawable s)
	{
		shape = s;
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public ShapeDrawable getShape()
	{
		return shape;
	}

	public void setShape(ShapeDrawable shape)
	{
		this.shape = shape;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	public RadialGradient getGradient()
	{
		return gradient;
	}

	public void setGradient(RadialGradient gradient)
	{
		this.gradient = gradient;
	}

	public float getAlpha()
	{
		return alpha;
	}

	public void setAlpha(float alpha)
	{
		this.alpha = alpha;
	}

	public Paint getPaint()
	{
		return paint;
	}

	public void setPaint(Paint paint)
	{
		this.paint = paint;
	}
}