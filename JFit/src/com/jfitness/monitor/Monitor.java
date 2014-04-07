package com.jfitness.monitor;

public abstract class Monitor {
//test
	abstract void fuzzifier();
	abstract void fuzzyRules();
	abstract void inferenceEngine();
	abstract void deffuzifier();
	
	void blah(){
		
	}
	
	float min(float input1, float input2){
		if(input1<=input2)
			return input1;
		else
			return input2;
		
	}
	
	float max(float input1, float input2){
		if(input1>=input2)
			return input1;
		else
			return input2;
	}
}
