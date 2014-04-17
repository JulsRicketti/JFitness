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

	public ArrayList <String> recommendationDateHistory = new ArrayList<String>();
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
		recommendationDateHistory = history.getWalkerHistory(context).recommendationDate;
		activityHistory = history.getWalkerHistory(context).activity;
		activityDateHistory = history.getWalkerHistory(context).activityDate;
		analysesHistory = history.getWalkerHistory(context).monitor;
		
	}
	
	 void setRunningHistory(Context context){
		 recommendationHistory = history.getRunnerHistory(context).recommendation;
		 recommendationDateHistory = history.getRunnerHistory(context).recommendationDate;
		 activityHistory = history.getRunnerHistory(context).activity;
		 activityDateHistory = history.getRunnerHistory(context).activityDate;
		 analysesHistory = history.getRunnerHistory(context).monitor;
	}
	
	 void setWeightLossHistory(Context context){
		 recommendationHistory = history.getHistory("WeightLossHistoryTable").recommendation;
		 recommendationDateHistory = history.getHistory("WeightLossHistoryTable").recommendationDate;
		 activityHistory = history.getHistory("WeightLossHistoryTable").activity;
		 activityDateHistory = history.getHistory("WeightLossHistoryTable").activityDate;
		 analysesHistory = history.getHistory("WeightLossHistoryTable").monitor;
	}


	public ArrayList<String> getRecommendationDateHistory() {
		return recommendationDateHistory;
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
