package com.jfitness.history;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.jfitness.persistance.DatabaseAdapter;

public class History {
	DatabaseAdapter DatabaseAdapter;
	
	ActivityHistory walkerHistory;
	ActivityHistory runnerHistory;
	ActivityHistory weightLossHistory;
	
	public History(){	
		
	}
	public History(String tableName, String recommendationDate, String recommendation, String activityDate, String activity, String monitor){
		DatabaseAdapter.insertActivity(tableName, recommendation, activityDate, activity, monitor);
	}
	
	boolean isWalkerEmpty(Context context){
		DatabaseAdapter = new DatabaseAdapter(context);
		DatabaseAdapter.open();
		return DatabaseAdapter.isEmpty(DatabaseAdapter.WALKER_HISTORY_TABLE);
	}
	
	boolean isRunnerEmpty(Context context){
		DatabaseAdapter = new DatabaseAdapter(context);
		DatabaseAdapter.open();
		return DatabaseAdapter.isEmpty(DatabaseAdapter.RUNNER_HISTORY_TABLE);
	}
	
	boolean isWeightLossEmpty(Context context){
		DatabaseAdapter = new DatabaseAdapter(context);
		DatabaseAdapter.open();
		return DatabaseAdapter.isEmpty(DatabaseAdapter.WEIGHT_LOSS_HISTORY_TABLE);
	}
	
	public ActivityHistory getWalkerHistory(Context context){
		DatabaseAdapter = new DatabaseAdapter(context);
		DatabaseAdapter.open();
		
		if(DatabaseAdapter.isEmpty(DatabaseAdapter.WALKER_HISTORY_TABLE)){
			DatabaseAdapter.close();
			return null;
		}
		else{
			walkerHistory = getHistory(DatabaseAdapter.WALKER_HISTORY_TABLE, context);
	
			DatabaseAdapter.close();
			return walkerHistory;
		}
	}
	
	public ActivityHistory getRunnerHistory(Context context){
		DatabaseAdapter = new DatabaseAdapter(context);
		DatabaseAdapter.open();
		
		if(DatabaseAdapter.isEmpty(DatabaseAdapter.RUNNER_HISTORY_TABLE)){
			return null;
		}
		else{
			runnerHistory = getHistory(DatabaseAdapter.WALKER_HISTORY_TABLE, context);		
	
			DatabaseAdapter.close();
			return runnerHistory;
		}
	}
	
	public ActivityHistory getHistory(String tableName, Context context){
		DatabaseAdapter= new DatabaseAdapter(context) ; 
		DatabaseAdapter.open();
		Cursor history = DatabaseAdapter.getAllActivityRecords(tableName);
		ActivityHistory activity = new ActivityHistory();
		history.moveToFirst();
		while(!history.isAfterLast()){
			activity.recommendation.add(history.getString(history.getColumnIndex(DatabaseAdapter.RECOMMENDATION)));
			activity.activityDate.add(history.getString(history.getColumnIndex(DatabaseAdapter.ACTIVITY_DATE)));
			activity.activity.add(history.getString(history.getColumnIndex(DatabaseAdapter.ACTIVITY)));
			activity.monitor.add(history.getString(history.getColumnIndex(DatabaseAdapter.MONITOR)));
			history.moveToNext();			
		}
		return activity;

	}

}
