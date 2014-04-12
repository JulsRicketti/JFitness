package com.jfitness.history;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.jfitness.persistance.DatabaseAdapter;

public class History {
	
	DatabaseAdapter dbAdapter;
	
	ActivityHistory walkerHistory;
	ActivityHistory runnerHistory;
	ActivityHistory weightLossHistory;
	
	boolean isWalkerEmpty(Context context){
		dbAdapter = new DatabaseAdapter(context);
		dbAdapter.open();
		return dbAdapter.isEmpty(DatabaseAdapter.WALKER_HISTORY_TABLE);
	}
	
	boolean isRunnerEmpty(Context context){
		dbAdapter = new DatabaseAdapter(context);
		dbAdapter.open();
		return dbAdapter.isEmpty(DatabaseAdapter.RUNNER_HISTORY_TABLE);
	}
	
	boolean isWeightLossEmpty(Context context){
		dbAdapter = new DatabaseAdapter(context);
		dbAdapter.open();
		return dbAdapter.isEmpty(DatabaseAdapter.WEIGHT_LOSS_HISTORY_TABLE);
	}
	
	public ActivityHistory getWalkerHistory(Context context){
		dbAdapter = new DatabaseAdapter(context);
		dbAdapter.open();
		
		if(dbAdapter.isEmpty(DatabaseAdapter.WALKER_HISTORY_TABLE)){
			dbAdapter.close();
			return null;
		}
		else{
			walkerHistory = getHistory(DatabaseAdapter.WALKER_HISTORY_TABLE);
	
			dbAdapter.close();
			return walkerHistory;
		}
	}
	
	public ActivityHistory getRunnerHistory(Context context){
		dbAdapter = new DatabaseAdapter(context);
		dbAdapter.open();
		
		if(dbAdapter.isEmpty(DatabaseAdapter.RUNNER_HISTORY_TABLE)){
			return null;
		}
		else{
			runnerHistory = getHistory(DatabaseAdapter.WALKER_HISTORY_TABLE);		
	
			dbAdapter.close();
			return runnerHistory;
		}
	}
	
	public ActivityHistory getHistory(String tableName){
		
		Cursor history = dbAdapter.getAllData(tableName);
		ActivityHistory activity = new ActivityHistory();
		history.moveToFirst();
		while(!history.isAfterLast()){
			activity.recommendation.add(history.getString(history.getColumnIndex(DatabaseAdapter.RECOMMENDATION)));
			activity.recommendationDate.add(history.getString(history.getColumnIndex(DatabaseAdapter.RECOMMENDATION_DATE)));
			activity.activityDate.add(history.getString(history.getColumnIndex(DatabaseAdapter.ACTIVITY_DATE)));
			activity.activity.add(history.getString(history.getColumnIndex(DatabaseAdapter.ACTIVITY)));
			activity.analyses.add(history.getString(history.getColumnIndex(DatabaseAdapter.ANALYSES)));
			history.moveToNext();			
		}
		return activity;
	}
}
