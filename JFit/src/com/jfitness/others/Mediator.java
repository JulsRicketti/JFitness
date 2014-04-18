package com.jfitness.others;

import java.util.ArrayList;

import android.content.Context;

import com.jfitness.analyse.Analyse;
import com.jfitness.analyse.WalkingAnalyse;
import com.jfitness.history.ActivityHistory;
import com.jfitness.history.History;
import com.jfitness.monitor.Monitor;
import com.jfitness.monitor.WalkingMonitor;
import com.jfitness.recommend.Recommend;
import com.jfitness.recommend.WalkingRecommend;

public class Mediator {

	Context context;
	
	public ArrayList <String> recommendationHistory= new ArrayList<String>();
	public ArrayList <String> activityDateHistory= new ArrayList<String>();
	public ArrayList <String> activityHistory= new ArrayList<String>();
	public ArrayList <String> analysesHistory= new ArrayList<String>();
	
	
	History history;
	Recommend recommendation;
	Monitor monitor;
	Analyse analyses;
	
	public Mediator(){
		this.history = new History();
		this.recommendation = new WalkingRecommend();
		this.analyses = new WalkingAnalyse();
		this.monitor = new WalkingMonitor();
	}
	
	
	//get all history functions
	 void setWalkingHistory(Context context){
		recommendationHistory = history.getWalkerHistory(context).recommendation;
		activityHistory = history.getWalkerHistory(context).activity;
		activityDateHistory = history.getWalkerHistory(context).activityDate;
		analysesHistory = history.getWalkerHistory(context).monitor;
		
	}
	
	 void setRunningHistory(Context context){
		 recommendationHistory = history.getRunnerHistory(context).recommendation;
		 activityHistory = history.getRunnerHistory(context).activity;
		 activityDateHistory = history.getRunnerHistory(context).activityDate;
		 analysesHistory = history.getRunnerHistory(context).monitor;
	}
	
	 void setWeightLossHistory(Context context){
		 recommendationHistory = history.getHistory("WeightLossHistoryTable", context).recommendation;
		 activityHistory = history.getHistory("WeightLossHistoryTable", context).activity;
		 activityDateHistory = history.getHistory("WeightLossHistoryTable",context).activityDate;
		 analysesHistory = history.getHistory("WeightLossHistoryTable", context).monitor;
	}


	public ArrayList<String> getRecommendationHistory() {
		return recommendationHistory;
	}


	public ArrayList<String> getActivityDateHistory() {
		return activityDateHistory;
	}


	public ArrayList<String> getActivityHistory() {
		return activityHistory;
	}


	public ArrayList<String> getAnalysesHistory() {
		return analysesHistory;
	}
	
	//functions to send the things things to the database:
	
}
