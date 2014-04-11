package com.jfitness.monitor;

import java.util.HashMap;



public class WalkingMonitor extends Monitor {
	
	//set names
	static final String BAD = "bad";
	static final String AVERAGE = "average";
	static final String GOOD = "good";
	
	//output set names
	static final String INSUFFICIENT = "insufficient";
	static final String SUFFICIENT = "sufficient";
	static final String AVERAGE_SUFICIENT = "average";
	
	//output definitions:
	static final float perfect = 100;
	static final float poor = perfect/4; //(25)
	static final float average_bad = perfect/2; //(50)
	static final float average_good = average_bad + poor; //(75)
	
	//distance definitions 
	static final float weeklyMinDistance = 5500;
	static final float dailyMinDistance = weeklyMinDistance/7;
	static final float goodDistance = 1000;
	
	static float averageSpeed; //I have to get this information from the user's profile
	
	FuzzySet[] timeSet;
	FuzzySet[] distanceSet;
	HashMap <String, FuzzySet> timeMap;
	HashMap <String, FuzzySet>distanceMap;
	@Override
	public void fuzzifier(float timeInput, float distanceInput, float speedInput) {
		FuzzySet []timeFuzzySet = new FuzzySet[3];
		FuzzySet []distanceFuzzySet = new FuzzySet[3];
		
		timeFuzzySet = timeFuzzifier(timeInput);
		distanceFuzzySet = distanceFuzzifier(distanceInput);

	}

	FuzzySet[] timeFuzzifier(float timeInput){
		float []mAndB = new float[2];
		float membershipBad=0, membershipAverage=0, membershipGood=0;
		
		float [] badInterval = new float[2];
		float []averageInterval = new float[2];
		float []goodInterval = new float[2];
		badInterval[0]=0; badInterval[1]=30;
		averageInterval[0] = 15; averageInterval[1]=45;
		goodInterval[0]=45; goodInterval[1]=999;
		
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
		
		FuzzySet[] timeInputs = new FuzzySet[3];
		timeInputs[0]= new FuzzySet(BAD, membershipBad);
		timeInputs[0].setInterval(badInterval);
		timeInputs[1] = new FuzzySet(AVERAGE, membershipAverage);
		timeInputs[1].setInterval(averageInterval);
		timeInputs[2] = new FuzzySet(GOOD, membershipGood);
		timeInputs[2].setInterval(goodInterval);
		
		timeMap.put(BAD, timeInputs[0]);
		timeMap.put(AVERAGE, timeInputs[1]);
		timeMap.put(GOOD, timeInputs[2]);

		return timeInputs;
		
	}
	
	FuzzySet[] distanceFuzzifier(float distanceInput){
		FuzzySet result;
		float []mAndB = new float[2];
		float membershipBad=0, membershipAverage=0, membershipGood=0;
		float [] badInterval = new float[2];
		float []averageInterval = new float[2];
		float []goodInterval = new float[2];
		badInterval[0] = 0; badInterval[1]=dailyMinDistance/2;
		averageInterval[0] = dailyMinDistance/2; averageInterval[1]=dailyMinDistance;
		goodInterval[0]=dailyMinDistance; goodInterval[1]=goodDistance;
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
		
//		result = max( membershipBad, BAD, membershipAverage, AVERAGE);
//		result = max(membershipGood, GOOD, result.getDegreeOfMembership(), result.getName());
//				
//		return result;
		FuzzySet[] distanceInputs = new FuzzySet[3];
		distanceInputs [0]= new FuzzySet(BAD, membershipBad);
		distanceInputs[0].setInterval(badInterval);
		distanceInputs [1] = new FuzzySet(AVERAGE, membershipAverage);
		distanceInputs[1].setInterval(averageInterval);
		distanceInputs [2] = new FuzzySet(GOOD, membershipGood);
		distanceInputs[2].setInterval(goodInterval);
		
		
		distanceMap.put(BAD, distanceInputs[0]);
		distanceMap.put(AVERAGE, distanceInputs[1]);
		distanceMap.put(GOOD,distanceInputs[2]);

		return distanceInputs;
		
	}
	
	//obs: average speed will depend on the user, so I have to find a way to get that!
	
	void outputFuzzifier(float output){
		float membershipInsufficient, membershipAverage, membershipSufficient;
		float [] mAndB = new float[2];
		if(output<=poor){
			membershipInsufficient=1;
			membershipAverage=0;
			membershipSufficient=0;
		}
		if(output>poor && output<=average_bad){
			mAndB = findLinearFunction(poor, 1, average_bad, 0);
			membershipInsufficient = findDegreeOfMembership(output, mAndB);
			
			mAndB = findLinearFunction(poor, 0, average_bad, 1);
			membershipAverage = findDegreeOfMembership(output, mAndB);
			
			membershipSufficient=0;
		}
		if(output>average_bad && output<=average_good){
			membershipInsufficient = 0;
			membershipAverage=1;
			membershipSufficient=0;
		}
		if(output>average_good && output<=perfect){
			membershipInsufficient=0;
			
			mAndB = findLinearFunction(average_good, 1, perfect, 0);
			membershipAverage = findDegreeOfMembership(output, mAndB);
			
			mAndB = findLinearFunction(average_good, 0, perfect, 1);
			membershipSufficient=findDegreeOfMembership(output, mAndB);
		}
		if(output>perfect){
			membershipInsufficient=0;
			membershipAverage=0;
			membershipSufficient=1;
		}
	}
	
	@Override
	public void fuzzyRules(FuzzySet[] inputSet) {
		// TODO Auto-generated method stub
		FuzzySet []outputs = new FuzzySet[9]; //these are the rules
		
		outputs[0] = new FuzzySet(INSUFFICIENT, min(timeMap.get(BAD).getDegreeOfMembership(), distanceMap.get(BAD).getDegreeOfMembership()));
		outputs[1] = new FuzzySet(AVERAGE_SUFICIENT, min(timeMap.get(AVERAGE).getDegreeOfMembership(), distanceMap.get(AVERAGE).getDegreeOfMembership()));
		outputs[2] = new FuzzySet(SUFFICIENT, min(timeMap.get(GOOD).getDegreeOfMembership(), distanceMap.get(GOOD).getDegreeOfMembership()));
		outputs[3] = new FuzzySet(AVERAGE_SUFICIENT, min(timeMap.get(GOOD).getDegreeOfMembership(), distanceMap.get(BAD).getDegreeOfMembership()));
		outputs[4] = new FuzzySet(SUFFICIENT, min(timeMap.get(GOOD).getDegreeOfMembership(), distanceMap.get(AVERAGE).getDegreeOfMembership()));
		outputs[5] = new FuzzySet(SUFFICIENT, min(timeMap.get(AVERAGE).getDegreeOfMembership(), distanceMap.get(GOOD).getDegreeOfMembership()));
		outputs[6] = new FuzzySet(INSUFFICIENT, min(timeMap.get(AVERAGE).getDegreeOfMembership(), distanceMap.get(BAD).getDegreeOfMembership()));
		outputs[7] = new FuzzySet(AVERAGE_SUFICIENT, min(timeMap.get(BAD).getDegreeOfMembership(), distanceMap.get(GOOD).getDegreeOfMembership()));
		outputs[8] = new FuzzySet(INSUFFICIENT, min(timeMap.get(BAD).getDegreeOfMembership(), distanceMap.get(AVERAGE).getDegreeOfMembership()));
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


//I may use this but only later... So for now I separated it from the rest.
//FuzzySet speedFuzzifier(float speedInput, float averageSpeed){
//FuzzySet result;
//float []mAndB = new float[2];
//float membershipBad=0, membershipAverage=0, membershipGood=0;
//if(speedInput<averageSpeed){
//	mAndB = findLinearFunction(speedInput, 1, averageSpeed, 0);
//	membershipBad = findDegreeOfMembership(speedInput, mAndB);
//	
//	mAndB = findLinearFunction(speedInput, 0, averageSpeed, 1);
//	membershipAverage = findDegreeOfMembership(speedInput, mAndB);
//	
//	membershipGood=0;
//}
//if(speedInput==averageSpeed){
//	membershipBad =0;
//	membershipAverage =1;
//	membershipGood=0;
//}
////revise this one!!!!
//if(speedInput>averageSpeed){
//	membershipBad=0;
//	
//	mAndB = findLinearFunction(speedInput, 1, averageSpeed, 0);
//	membershipAverage = findDegreeOfMembership(speedInput, mAndB);
//	
//	mAndB = findLinearFunction(speedInput, 0, averageSpeed, 1);
//	membershipGood = findDegreeOfMembership(speedInput, mAndB);
//}
//
//if(speedInput>=2*averageSpeed){
//	membershipBad=0;
//	membershipAverage=0;
//	membershipGood=1;
//}
//
//result = max( membershipBad, BAD, membershipAverage, AVERAGE);
//result = max(membershipGood, GOOD, result.getDegreeOfMembership(), result.getName());
//		
//return result;
//}
