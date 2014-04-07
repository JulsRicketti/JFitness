package com.jfitness.monitor;

public abstract class Monitor {
	
	abstract void fuzzifier();
	abstract void fuzzyRules();
	abstract void inferenceEngine();
	abstract void deffuzifier();
	
	
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
	
	
	float findDegreeOfMembership(float x, float []mAndB){
		return mAndB[0]*x +mAndB[1];
	}
	
	//remember: y = xm + b
	float[] findLinearFunction(float x1, float y1, float x2, float y2){
		float [] mAndB = new float[2];
		mAndB[0] = findM(x1, y1, x2, y2);
		mAndB[1] = findB(x1, y1, mAndB[0]);
		
		
		return mAndB;
	}
	
	float findM(float x1, float y1, float x2, float y2){
		return (y1 - y2)/(x1-x2);
	}
	
	float findB(float x, float y, float m){
		return y - m*x;
	}

}
