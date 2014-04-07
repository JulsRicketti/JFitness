package com.jfitness.activities;

import com.example.jfit.R;
import com.example.jfit.R.layout;
import com.example.jfit.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DashboardActivity extends Activity {

	Button walkerDashboardButton;
	Button runnerDashboardButton;
	Button weightLossDashboardButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		walkerDashboardButton = (Button)findViewById(R.id.walkerDashboardButton);
		runnerDashboardButton = (Button)findViewById(R.id.runnerDashboardButton);
		weightLossDashboardButton = (Button)findViewById(R.id.weightLossDashboardButton);
		
		setButtons();
	}

	void setButtons(){
		walkerDashboardButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		runnerDashboardButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		weightLossDashboardButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
