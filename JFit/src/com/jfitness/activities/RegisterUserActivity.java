package com.jfitness.activities;

import java.util.ArrayList;

import com.example.jfit.R;
import com.example.jfit.R.id;
import com.example.jfit.R.layout;
import com.example.jfit.R.menu;
import com.jfitness.persistance.DatabaseAdapter;
import com.jfitness.persistance.DatabaseAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterUserActivity extends Activity {

//	DatabaseAdapter dbadapter;
	DatabaseAdapter dbAdapter;
	
	Button finishRegisterButton;
	
	EditText ageRegisterEditText;
	EditText weightRegisterEditText;
	EditText heightRegisterEditText;
	
	
	Spinner sexRegisterSpinner;
	ArrayList<String> sexSpinnerList = new ArrayList<String>();
	
	Spinner parameterRegisterSpinner;
	ArrayList<String> parameterSpinnerList = new ArrayList<String>();
	
	CheckBox weightLossRegisterCheckBox;
	CheckBox runningRegisterCheckBox;
	CheckBox walkingRegisterCheckBox;
	
	String age = "";
	String weight = "";
	String height = "";
	String sex = "Select Sex";
	String objectives ="";
	String weightLossObjective = "false";
	String runningObjective = "false";
	String walkingObjective = "false";	
	String parameter = "Select parameter"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_user);
		
		dbAdapter = new DatabaseAdapter(this);
		dbAdapter = dbAdapter.open();
		
		ageRegisterEditText = (EditText)findViewById(R.id.ageRegisterEditText);
		ageRegisterEditText.addTextChangedListener(ageTextWatcher);
		weightRegisterEditText = (EditText) findViewById(R.id.weightRegisterEditText);
		weightRegisterEditText.addTextChangedListener(weightTextWatcher);
		heightRegisterEditText = (EditText) findViewById(R.id.heightRegisterEditText);
		heightRegisterEditText.addTextChangedListener(heightTextWatcher);
		
		sexRegisterSpinner = (Spinner) findViewById(R.id.sexRegisterSpinner);
		sexSpinnerList.add("Select Sex");
		sexSpinnerList.add("Male");
		sexSpinnerList.add("Female");
		ArrayAdapter<String> sexArrayAdapter = new ArrayAdapter<String>(RegisterUserActivity.this,android.R.layout.simple_spinner_item,sexSpinnerList);
		sexRegisterSpinner.setAdapter(sexArrayAdapter);
		
		sexRegisterSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				sex = sexRegisterSpinner.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		parameterRegisterSpinner = (Spinner)findViewById(R.id.parameterRegisterSpinner);
		parameterSpinnerList.add("Select parameter");
		parameterSpinnerList.add("Distance");
		parameterSpinnerList.add("Velocity");
		parameterSpinnerList.add("Cardiac Frequency");
		ArrayAdapter<String> parameterArrayAdapter = new ArrayAdapter<String>(RegisterUserActivity.this,android.R.layout.simple_spinner_item,parameterSpinnerList);
		parameterRegisterSpinner.setAdapter(parameterArrayAdapter);
		parameterRegisterSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				parameter = parameterRegisterSpinner.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		weightLossRegisterCheckBox = (CheckBox) findViewById(R.id.weightLossRegisterCheckBox);
		runningRegisterCheckBox = (CheckBox) findViewById(R.id.runningRegisterCheckBox);
		walkingRegisterCheckBox = (CheckBox) findViewById(R.id.walkingRegisterCheckBox);
		setCheckBoxes();
		
		finishRegisterButton = (Button)findViewById(R.id.finishRegisterButton);
		finishRegisterButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!parameter.equals("Select parameter") &&!sex.equals("Select Sex") && !weight.equals("") && !height.equals("") && !age.equals("") && verifyCheckBoxes()){
					dbAdapter.deleteAll(dbAdapter.USER_PROFILE_TABLE); //we make sure only one is made for this application
					dbAdapter.insertUser(age, weight, height, sex, weightLossObjective, runningObjective, walkingObjective, parameter);
					Toast.makeText(getApplicationContext(), "Done!  "+dbAdapter.getAllUserRecords().getCount(), Toast.LENGTH_SHORT).show();
					Intent i  = new Intent(RegisterUserActivity.this,DashboardActivity.class);
					startActivity(i);
				}
				else{
					Toast.makeText(getApplicationContext(), "Please fill out the whole form", Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	void setCheckBoxes(){
		if(weightLossRegisterCheckBox.isChecked()){
			weightLossObjective = "true";
		}
		else
			weightLossObjective = "false";
		if(runningRegisterCheckBox.isChecked()){
			runningObjective="true";
		}
		else
			runningObjective="false";
		if(walkingRegisterCheckBox.isChecked()){
			walkingObjective="true";
		}
		else
			walkingObjective="false";
	}
	
	boolean verifyCheckBoxes(){
		if(weightLossRegisterCheckBox.isChecked() || runningRegisterCheckBox.isChecked() || walkingRegisterCheckBox.isChecked())
			return true;
		else
			return false;
	}
	
	TextWatcher ageTextWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			age = s.toString();
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	TextWatcher weightTextWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			weight = s.toString();
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	};
	
	TextWatcher heightTextWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			height = s.toString();
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_user, menu);
		return true;
	}

}
