package com.example.finalexam;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	ImageButton btTracnghiem, btTudien, btTrogiup;
	Intent iTracnghiem, iTudien, iTrogiup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btTracnghiem = (ImageButton) findViewById(R.id.imageButton1);
		btTracnghiem.setOnClickListener(this);
		
		btTudien = (ImageButton) findViewById(R.id.imageButton2);
		btTudien.setOnClickListener(this);
		
		btTrogiup = (ImageButton) findViewById(R.id.imageButton3);
		btTrogiup.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		if(arg0 == btTracnghiem){
			iTracnghiem = new Intent(MainActivity.this, TracNghiem.class);
			startActivity(iTracnghiem);
		}

		if(arg0 == btTudien){
			iTudien = new Intent(MainActivity.this,Tudien.class);
			startActivity(iTudien);
		}
		
		if(arg0 == btTrogiup){
			//setContentView(R.layout.trogiup);
			iTrogiup = new Intent(MainActivity.this,Trogiup.class);
			startActivity(iTrogiup);
		}
	}

}

