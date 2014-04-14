package com.jfitness.recommend;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.Days;
import android.content.Context;
import com.jfitness.others.Mediator;

//try to make it a singleton
public class WalkingRecommend implements Recommend {

	static int numberOfDays = 7; //this is the number of days we take into consideration
	
	static float weeklyMinDistance = 5500;
	static float dailyMinDistance = weeklyMinDistance/numberOfDays;
	
	ArrayList <String> recommendationHistory = new ArrayList<String>();
	Mediator mediator = new Mediator();
	
	float recommendationNumber;
	
	
	//take a look at context! I am not sure what this is!
	public String recommend(Context context){
		recommendationHistory = mediator.getRecommendationHistory();
		
		//if it's the first time an exercise is done
		if(recommendationHistory.isEmpty()){
			recommendationNumber = dailyMinDistance;
			return Float.toString(recommendationNumber);
		}
		else{
			
			DateTime lastWalkingDate = new DateTime(mediator.activityDateHistory.get(mediator.activityDateHistory.size()-1));
			if(getDaysInBetween(lastWalkingDate)<=numberOfDays){
				return Float.toString(calculateRecommendation(recommendationHistory));
			}
			else{
				//start over.
				recommendationNumber = dailyMinDistance;
				return Float.toString(recommendationNumber);
			}
			
		}
	}
	
	//will verify the significance of how distant the days are from each other
	int verifyDayDistance(ArrayList<String> history){
		DateTime today = new DateTime();
		DateTime[] lastSevenActivities = new DateTime[numberOfDays];
		for(int i=0; i<numberOfDays; i++)
			lastSevenActivities[i] = new DateTime(mediator.activityDateHistory.get((mediator.activityDateHistory.size()-1)-i));
		
		if(getDaysInBetween(lastSevenActivities[0])>7){
			return 0;
		}
		if(getDaysInBetween(lastSevenActivities[1])>10){
			return 1;
		}
		if(getDaysInBetween(lastSevenActivities[2])>12){
			return 2;
		}
		if(getDaysInBetween(lastSevenActivities[3])>13){
			return 3;
		}		
		if(getDaysInBetween(lastSevenActivities[4])>14){
			return 4;
		}
		if(getDaysInBetween(lastSevenActivities[5])>15){
			return 5;
		}
		return -1;
	}
	
	
	float calculateRecommendation(ArrayList<String> history){		
		DateTime today = new DateTime();
		DateTime[] lastSevenActivityDates = new DateTime[numberOfDays];
		float[] lastSevenActivities = new float[numberOfDays];
		for(int i=0; i<numberOfDays; i++){
			lastSevenActivityDates[i] = new DateTime(mediator.activityDateHistory.get((mediator.activityDateHistory.size()-1)-i));
			lastSevenActivities[i] = Float.parseFloat(mediator.activityHistory.get((mediator.activityHistory.size()-1)-i));
		}

		if(verifyDayDistance(history)==-1){ //this means limits between days weren't surpassed
			int distanceFirstAndLastDay = getDaysInBetween(lastSevenActivityDates[6]);
			int totalDistance =0;
			for(int i=0; i<lastSevenActivities.length;i++){
				totalDistance += lastSevenActivities[i];
			}
			
			if(distanceFirstAndLastDay<=7){
				if(totalDistance/numberOfDays < dailyMinDistance){
					
				}
			}
			else{
				
			}
		}
		else{
			
		}

		
		return 0;
	}
	
	//function that will get the last time the exercise was performed
	int getDaysInBetween(DateTime lastWalkingDate){
		DateTime today = new DateTime();
		
		//we need to find out how many days we have in between the two dates
		return Days.daysBetween(lastWalkingDate, today).getDays();
	}
}
