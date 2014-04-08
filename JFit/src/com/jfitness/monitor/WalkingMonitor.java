package com.jfitness.monitor;



public class WalkingMonitor extends Monitor {
	
	//set names
	static final String BAD = "bad";
	static final String AVERAGE = "average";
	static final String GOOD = "good";
	
	//distance definitions 
	static final float weeklyMinDistance = 5500;
	static final float dailyMinDistance = weeklyMinDistance/7;
	static final float goodDistance = 1000;
	
	static float averageSpeed; //I have to get this information from the user's profile
	
	FuzzySet timeSet, distanceSet, speedSet;
	@Override
	public void fuzzifier(float timeInput, float distanceInput, float speedInput) {
		// TODO Auto-generated method stub
		if(timeInput>0){
			timeSet = timeFuzzifier(timeInput);
		}
		else{
			timeSet = new FuzzySet("INVALID", 0);
		}
		if(distanceInput>0){
			distanceSet = distanceFuzzifier(distanceInput);
		}
		else{
			distanceSet = new FuzzySet("INVALID", 0);
		}
		if(speedInput>0){
			speedSet = speedFuzzifier(speedInput, averageSpeed);
		}
		else{
			speedSet = new FuzzySet("INVALID", 0);
		}

	}

	FuzzySet timeFuzzifier(float timeInput){
		FuzzySet result;
		float []mAndB = new float[2];
		float membershipBad=0, membershipAverage=0, membershipGood=0;
		
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
		
		result = max( membershipBad, BAD, membershipAverage, AVERAGE);
		result = max(membershipGood, GOOD, result.getDegreeOfMembership(), result.getName());
				
		return result;
		
	}
	
	FuzzySet distanceFuzzifier(float distanceInput){
		FuzzySet result;
		float []mAndB = new float[2];
		float membershipBad=0, membershipAverage=0, membershipGood=0;
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
		
		result = max( membershipBad, BAD, membershipAverage, AVERAGE);
		result = max(membershipGood, GOOD, result.getDegreeOfMembership(), result.getName());
				
		return result;
		
	}
	
	//obs: average speed will depend on the user, so I have to find a way to get that!
	FuzzySet speedFuzzifier(float speedInput, float averageSpeed){
		FuzzySet result;
		float []mAndB = new float[2];
		float membershipBad=0, membershipAverage=0, membershipGood=0;
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
		
		if(speedInput>=2*averageSpeed){
			membershipBad=0;
			membershipAverage=0;
			membershipGood=1;
		}
		
		result = max( membershipBad, BAD, membershipAverage, AVERAGE);
		result = max(membershipGood, GOOD, result.getDegreeOfMembership(), result.getName());
				
		return result;
	}
	
	@Override
	public void fuzzyRules() {
		// TODO Auto-generated method stub
		FuzzySet output;
		output = max(timeSet, distanceSet); //when both of them belong to the same set, the results will be in that set
		if(timeSet.getName().equals(GOOD) && distanceSet.getName().equals(BAD)){
			output.setName(AVERAGE);
		}
		if(timeSet.getName().equals(GOOD) && distanceSet.getName().equals(AVERAGE)){
			output.setName(GOOD);
		}
		if(timeSet.getName().equals(AVERAGE) && distanceSet.getName().equals(GOOD)){
			output.setName(GOOD);
		}
		if(timeSet.getName().equals(AVERAGE) && distanceSet.getName().equals(BAD)){
			output.setName(BAD);
		}
		if(timeSet.getName().equals(BAD) && distanceSet.getName().equals(GOOD)){
			output.setName(AVERAGE);
		}
		if(timeSet.getName().equals(BAD) && distanceSet.getName().equals(AVERAGE)){
			output.setName(BAD);
		}
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
