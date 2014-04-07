package com.jfitness.monitor;



public class WalkingMonitor extends Monitor {
	
	static final float weeklyMinDistance = 5500;
	static final float dailyMinDistance = weeklyMinDistance/7;
	static final float goodDistance = 1000;
	
	@Override
	public void fuzzifier() {
		// TODO Auto-generated method stub

	}

	void timeFuzzifier(float timeInput){
		float []mAndB = new float[2];
		float membershipBad, membershipAverage, membershipGood;
		
		if(timeInput<=15){
			membershipBad = 1;
			membershipAverage=0;
			membershipGood = 0;
		}
		if(timeInput>15 && timeInput<=30){
			mAndB = findLinearFunction(15, 1, 30, 0);
			membershipBad = findDegreeOfMembership(timeInput, mAndB);
			
			mAndB = findLinearFunction(15, 0, 30, 1);
			membershipAverage = findDegreeOfMembership(timeInput, mAndB);
			
			membershipGood =0;
		}
		if(timeInput>30 && timeInput<=45){
			membershipBad =0;
			membershipAverage= 1;
			membershipGood = 0;
		}
		if(timeInput>45 && timeInput<=60){
			membershipBad =0;
			
			mAndB = findLinearFunction(45, 1, 60, 0);
			membershipAverage = findDegreeOfMembership(timeInput, mAndB);
			
			mAndB = findLinearFunction(45, 0, 60, 1);
			membershipGood = findDegreeOfMembership(timeInput, mAndB);
		}
		if(timeInput>60){
			membershipBad=0;
			membershipAverage=0;
			membershipGood=1;
		}
		
	}
	
	void distanceFuzzifier(float distanceInput){
		float []mAndB = new float[2];
		float membershipBad, membershipAverage, membershipGood;
		if(distanceInput<=dailyMinDistance/2){
			membershipBad = 1;
			membershipAverage = 0;
			membershipGood=0;
		}
		if(distanceInput>dailyMinDistance/2 && distanceInput<=dailyMinDistance){
			mAndB = findLinearFunction(dailyMinDistance/2, 1, dailyMinDistance, 0);
			membershipBad = findDegreeOfMembership(distanceInput, mAndB);
			
			mAndB = findLinearFunction(dailyMinDistance/2, 0, dailyMinDistance, 1);
			membershipAverage =findDegreeOfMembership(distanceInput, mAndB);
			
			membershipGood =0;
		}
		if(distanceInput>dailyMinDistance && distanceInput<=goodDistance){
			membershipBad =0;
			
			mAndB = findLinearFunction(dailyMinDistance, 1, goodDistance, 0);
			membershipAverage = findDegreeOfMembership(distanceInput, mAndB);
			
			mAndB = findLinearFunction(dailyMinDistance, 0, goodDistance, 1);
			membershipAverage = findDegreeOfMembership(distanceInput, mAndB);
		}
		if(distanceInput>goodDistance){
			membershipBad =0;
			membershipAverage=0;
			membershipGood=1;
		}
	}
	
	//obs: average speed will depend on the user, so I have to find a way to get that!
	void speedFuzzifier(float speedInput, float averageSpeed){
		float []mAndB = new float[2];
		float membershipBad, membershipAverage, membershipGood;
		if(speedInput<averageSpeed){
			mAndB = findLinearFunction(speedInput, 1, averageSpeed, 0);
			membershipBad = findDegreeOfMembership(speedInput, mAndB);
			
			mAndB = findLinearFunction(speedInput, 0, averageSpeed, 1);
			membershipAverage = findDegreeOfMembership(speedInput, mAndB);
			
			membershipGood=0;
		}
		if(speedInput==averageSpeed){
			membershipBad =0;
			membershipAverage =1;
			membershipGood=0;
		}
		//revise this one!!!!
		if(speedInput>averageSpeed){
			membershipBad=0;
			
			mAndB = findLinearFunction(speedInput, 1, averageSpeed, 0);
			membershipAverage = findDegreeOfMembership(speedInput, mAndB);
			
			mAndB = findLinearFunction(speedInput, 0, averageSpeed, 1);
			membershipGood = findDegreeOfMembership(speedInput, mAndB);
		}
		
		//I think I need one more for membershipGood = 1;
	}
	
	@Override
	public void fuzzyRules() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inferenceEngine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deffuzifier() {
		// TODO Auto-generated method stub
		
	}

}
