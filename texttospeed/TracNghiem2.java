package com.example.finalexam;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TracNghiem2 extends Activity implements OnClickListener,
		OnInitListener {

	// TTS object
	private TextToSpeech myTTS;
	// status check code
	private int MY_DATA_CHECK_CODE = 0;
	ImageButton btNext, nghe;
	Intent it2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tracnghiem2);
		btNext = (ImageButton) findViewById(R.id.imageButton1);
		btNext.setOnClickListener(this);

		nghe = (ImageButton) findViewById(R.id.imageButton2);
		nghe.setOnClickListener(this);
		// check for TTS data
		Intent checkTTSIntent = new Intent();
		checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		EditText enteredText = (EditText) findViewById(R.id.editText1);

		if(arg0 == nghe){
			speakWords("pig");
		}
		else if (enteredText.getText().toString().compareTo("pig") == 0) {
			String words = "Correct . Next to part three ";
			// String words = enteredText.getText().toString();
			speakWords(words);
			if (arg0 == btNext) {
				it2 = new Intent(TracNghiem2.this, TracNghiem3.class);
				startActivity(it2);
			}
		} else {
			String word = "Failure. Try again !";
			speakWords(word);
		}
	}

	// speak the user text
	private void speakWords(String speech) {
		// speak straight away
		myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
	}

	// act on result of TTS data check
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// the user has the necessary data - create the TTS
				myTTS = new TextToSpeech(this, this);
			} else {
				// no data - install it now
				Intent installTTSIntent = new Intent();
				installTTSIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installTTSIntent);
			}
		}
	}

	@Override
	public void onInit(int initStatus) {
		// TODO Auto-generated method stub
		// check for successful instantiation
		if (initStatus == TextToSpeech.SUCCESS) {
			if (myTTS.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
				myTTS.setLanguage(Locale.US);
		} else if (initStatus == TextToSpeech.ERROR) {
			Toast.makeText(this, "Sorry! Text To Speech failed...",
					Toast.LENGTH_LONG).show();
		}
	}
}
