package com.jfitness.recommend;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.Days;
import android.content.Context;
import com.jfitness.others.Mediator;

//try to make it a singleton
public class WalkingRecommend implements Recommend {

	static boolean completedWeeklyDistance = false;
	static int numberOfDays = 7; //this is the number of days we take into consideration
	
	static float weeklyMinDistance = 5500;
	static float dailyMinDistance = weeklyMinDistance/numberOfDays;
	
	static float currentDailyMinDistance;
	
	ArrayList <String> activityDateHistory = new ArrayList<String>();
	ArrayList <String> recommendationHistory = new ArrayList<String>();
	ArrayList <String> activitiesHistory = new ArrayList<String>();
	ArrayList <String> monitorHistory = new ArrayList<String>();
	
	Mediator mediator = new Mediator();
	
	float recommendationNumber;
	
	
	public WalkingRecommend(){
		this.activitiesHistory = mediator.getActivityDateHistory();
		this.recommendationHistory = mediator.getRecommendationHistory();
		this.activitiesHistory = mediator.getActivityHistory();
		this.monitorHistory = mediator.getAnalysesHistory();
		
	}
	
	public String recommend(Context context){
		//if this is the first time the person is walking
		if(activitiesHistory.isEmpty()){
			return Float.toString(dailyMinDistance);
		}
		else{
			//verifies if the user hasn't already done an activity today
			if(!isSameDay()){
				if(currentDailyMinDistance==dailyMinDistance){ //we can't go any lower than 5.5km per week
					return Float.toString(dailyMinDistance);
				}
				else{
					
				}
			}
			else{
				
				
			}
		}
		return null;
	}
	
	DateTime getLastDate(){
		DateTime lastDate = new DateTime(activityDateHistory.get(activityDateHistory.size()-1));
		return lastDate;
	}
	
	boolean isSameDay(){
		DateTime today = new DateTime();
		if(Days.daysBetween(getLastDate(), today).getDays() == 0){
			return true;
		}
		return false;
	}
	

}
