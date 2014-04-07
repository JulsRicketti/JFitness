package com.jfitness.monitor;



public class WalkingMonitor extends Monitor {

	@Override
	public void fuzzifier() {
		// TODO Auto-generated method stub

	}

	public void timeFuzzifier(int timeInput){
		float membershipBad, membershipAverage, membershipGood;
		
		if(timeInput<=15){
			membershipBad = 1;
			membershipAverage=0;
			membershipGood = 0;
		}
		if(timeInput>15 && timeInput<30){
			
		}
		
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
