package com.jfitness.activities;

import com.example.jfit.R;
import com.jfitness.persistance.DatabaseAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class InitialActivity extends Activity {

	DatabaseAdapter dbadapter;
	
	Button registerInitialButton;
	Button startInitialButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);
		
		dbadapter = new DatabaseAdapter(this);
		dbadapter = dbadapter.open();
		
		registerInitialButton = (Button) findViewById(R.id.reginsterInitialButton);
		registerInitialButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i  = new Intent(InitialActivity.this,RegisterUserActivity.class);
				startActivity(i);
			}
		});
		
		startInitialButton = (Button) findViewById(R.id.startInitialButton);
		startInitialButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//dbadapter.deleteUseTable();
				Intent i  = new Intent(InitialActivity.this,PhysicalActivity.class);
				startActivity(i);
				if(dbadapter.userProfileIsEmpty()){
					Toast.makeText(getApplicationContext(), "It is empty!", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(getApplicationContext(), "It isn't empty!", Toast.LENGTH_SHORT).show();
//				Intent i  = new Intent(CreateGameActivity.this,ChooseGameActivity.class);
//				startActivity(i);		
			}
		});
//put this back whenever testing is over
//		if(dbadapter.userProfileIsEmpty()){
//			startInitialButton.setVisibility(View.GONE);
//		}
//		else{
//			registerInitialButton.setText("Edit Profile");
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.initial, menu);
		return true;
	}

}
