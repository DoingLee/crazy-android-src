package org.crazyit.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.crazyit.domain.Person;

public class ResultActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		TextView name = (TextView) findViewById(R.id.name);
		TextView passwd = (TextView) findViewById(R.id.passwd);
		TextView gender = (TextView) findViewById(R.id.gender);
		// 获取启动该Activity的Intent
		Intent intent = getIntent();
		// 直接通过Intent取出它所携带的Bundle数据包中的数据
		Person p = (Person) intent.getSerializableExtra("person");
		name.setText("您的用户名为：" + p.getName());
		passwd.setText("您的密码为：" + p.getPasswd());
		gender.setText("您的性别为：" + p.getGender());
	}
}
