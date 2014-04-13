package com.jfitness.recommend;

import java.util.ArrayList;

import android.content.Context;

import com.jfitness.others.Mediator;
import com.jfitness.persistance.DatabaseAdapter;

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
			
		}
		return null;
	}
	
}
