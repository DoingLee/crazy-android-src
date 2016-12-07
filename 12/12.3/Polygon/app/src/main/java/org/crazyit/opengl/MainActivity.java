package org.crazyit.opengl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 创建一个GLSurfaceView，用于显示OpenGL绘制的图形
		GLSurfaceView glView = new GLSurfaceView(this);
		// 创建GLSurfaceView的内容绘制器
		MyRenderer myRender = new MyRenderer();
		// 为GLSurfaceView设置绘制器
		glView.setRenderer(myRender);
		setContentView(glView);
	}
}

