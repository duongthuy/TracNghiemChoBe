package com.example.finalexam;

//import com.example.texttospeed.R;

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
import android.widget.TextView;
import android.widget.Toast;

public class Tudien extends Activity implements OnClickListener, OnInitListener {

	// TTS object
	private TextToSpeech myTTS;
	// status check code
	private int MY_DATA_CHECK_CODE = 0;
	ImageButton speak;
	TextView tv;
	EditText enteredText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tudien);
		speak = (ImageButton) findViewById(R.id.imageButton1);
		speak.setOnClickListener(this);

		tv = (TextView) findViewById(R.id.textView3);
		enteredText = (EditText) findViewById(R.id.editText1);

		// check for TTS data
		Intent checkTTSIntent = new Intent();
		checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// get the text entered

		String words = enteredText.getText().toString();
		speakWords(words);

		if (enteredText.getText().toString().compareTo("cat") == 0) {
			tv.setText(enteredText.getText() + ":  Con m�o ");
		}

		else if (enteredText.getText().toString().compareTo("dog") == 0) {
			tv.setText(enteredText.getText() + ":  Con ch� ");
		}

		else if (enteredText.getText().toString().compareTo("pig") == 0) {
			tv.setText(enteredText.getText() + ":  Con heo ");
		}
		
		else if (enteredText.getText().toString().compareTo("cat dog") == 0) {
			tv.setText(enteredText.getText() + ":  m�o ch�  ");
		}
		else if (enteredText.getText().toString().compareTo(" cat dog pig") == 0) {
			tv.setText(enteredText.getText() + ":  m�o ch� heo ");
		}
		else if (enteredText.getText().toString().compareTo("dog pig") == 0) {
			tv.setText(enteredText.getText() + ":  ch� heo ");
		}
		else if (enteredText.getText().toString().compareTo(" cat pig") == 0) {
			tv.setText(enteredText.getText() + ":  m�o heo ");
		}
		else
			tv.setText("Khong tim thay");
	}

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
