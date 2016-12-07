package org.crazyit.io;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

public class MainActivity extends Activity
{
	TextToSpeech tts;
	EditText editText;
	Button speech;
	Button record;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 初始化TextToSpeech对象
		tts = new TextToSpeech(this, new OnInitListener()
		{
			@Override
			public void onInit(int status)
			{
				// 如果装载TTS引擎成功
				if (status == TextToSpeech.SUCCESS)
				{
					// 设置使用美式英语朗读
					int result = tts.setLanguage(Locale.US);
					// 如果不支持所设置的语言
					if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
							&& result != TextToSpeech.LANG_AVAILABLE)
					{
						Toast.makeText(MainActivity.this
							, "TTS暂时不支持这种语言的朗读。"
							, Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		editText = (EditText) findViewById(R.id.txt);
		speech = (Button) findViewById(R.id.speech);
		record = (Button) findViewById(R.id.record);
		speech.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 执行朗读
				tts.speak(editText.getText().toString(),
					TextToSpeech.QUEUE_ADD, null , "speech");
			}
		});
		record.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 将朗读文本的音频记录到指定文件
				tts.synthesizeToFile(editText.getText().toString() , null,
						new File("/mnt/sdcard/sound.wav"), "record");
				Toast.makeText(MainActivity.this, "声音记录成功！"
						, Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	public void onDestroy()
	{
		// 关闭TextToSpeech对象
		if (tts != null)
		{
			tts.shutdown();
		}
	}
}


