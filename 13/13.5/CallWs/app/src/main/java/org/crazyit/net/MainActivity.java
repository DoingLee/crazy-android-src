package org.crazyit.net;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;

public class MainActivity extends Activity
{
	final static String SERVICE_NS = "http://ws.app.crazyit.org/";
	final static String SERVICE_URL = "http://192.168.1.88:9999/crazyit";
	private EditText txt1;
	private EditText txt2;
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case 0x123:
					txt1.setText(msg.obj.toString());
					break;
				case 0x234:
					txt2.setText(msg.obj.toString());
					break;
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt1 = (EditText) findViewById(R.id.txt1);
		txt2 = (EditText) findViewById(R.id.txt2);
		// 调用的方法
		String methodName = "getUserList";
		// 创建HttpTransportSE传输对象
		final HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);  // ①
		ht.debug = true;
		// 使用SOAP1.1协议创建Envelop对象
		final SoapSerializationEnvelope envelope = new
				SoapSerializationEnvelope(SoapEnvelope.VER11);  // ②
		// 实例化SoapObject对象
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName); // ③
		soapObject.addProperty("arg0", "客户端参数:");  // ④
		// 将soapObject对象设置为 SoapSerializationEnvelope对象的传出SOAP消息
		envelope.bodyOut = soapObject;  // ⑤
		new Thread()
		{
			public void run()
			{
				try
				{
					// 调用Web Service
					ht.call(null,  envelope);  // ⑥
					if (envelope.getResponse() != null)
					{
						// 获取服务器响应返回的SOAP消息
						SoapObject result = (SoapObject) envelope.bodyIn; // ⑦
						// 接下来就是从SoapObject对象中解析响应数据的过程了
						SoapObject detail1 = (SoapObject) result
								.getProperty(0);
						SoapObject detail2 = (SoapObject) result
								.getProperty(1);
						StringBuilder person1 = new StringBuilder();
						person1.append("用户名：");
						person1.append(detail1.getProperty(3));
						person1.append("\n密码");
						person1.append(detail1.getProperty(0));
						person1.append("\n身高：");
						person1.append(detail1.getProperty(1));
						Message msg = new Message();
						msg.what = 0x123;
						msg.obj = person1.toString();
						handler.sendMessage(msg);
						StringBuilder person2 = new StringBuilder();
						person2.append("用户名：");
						person2.append(detail2.getProperty(3));
						person2.append("\n密码：");
						person2.append(detail2.getProperty(0));
						person2.append("\n身高：");
						person2.append(detail2.getProperty(1));
						Message msg2 = new Message();
						msg2.what = 0x234;
						msg2.obj = person2.toString();
						handler.sendMessage(msg2);
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (XmlPullParserException e)
				{
					e.printStackTrace();
				}
			}
		}.start();
	}
}

