package com.jfitness.history;

import java.util.ArrayList;

public class ActivityHistory {
	
	public ArrayList <String> recommendationDate;
	public ArrayList <String> recommendation;
	public ArrayList <String> activityDate;
	public ArrayList <String> activity;
	public ArrayList <String> monitor;
	
	public History history;
	
	public ActivityHistory(){
		recommendation = new ArrayList<String>();
		recommendationDate = new ArrayList<String>();
		activityDate = new ArrayList<String>();
		activity = new ArrayList<String>();
		monitor = new ArrayList<String>();
	}
	
	public ArrayList<String> getRecommendationDate() {
		return recommendationDate;
	}
	public void setRecommendationDate(ArrayList<String> recommendationDate) {
		this.recommendationDate = recommendationDate;
	}
	public ArrayList<String> getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(ArrayList<String> recommendation) {
		this.recommendation = recommendation;
	}
	public ArrayList<String> getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(ArrayList<String> activityDate) {
		this.activityDate = activityDate;
	}
	public ArrayList<String> getActivity() {
		return activity;
	}
	public void setActivity(ArrayList<String> activity) {
		this.activity = activity;
	}

	public ArrayList<String> getMonitor() {
		return monitor;
	}

	public void setMonitor(ArrayList<String> monitor) {
		this.monitor = monitor;
	}

}
