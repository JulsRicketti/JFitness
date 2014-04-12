package com.jfitness.others;

import java.util.ArrayList;

import android.content.Context;

import com.jfitness.analyse.Analyse;
import com.jfitness.analyse.WalkingAnalyse;
import com.jfitness.history.ActivityHistory;
import com.jfitness.history.History;
import com.jfitness.monitor.Monitor;
import com.jfitness.monitor.WalkingMonitor;
import com.jfitness.recommend.Recommend;
import com.jfitness.recommend.WalkingRecommend;

public class Mediator {

	History history;
	Recommend recommendation;
	Monitor monitor;
	Analyse analyses;
	
	public Mediator(){
		this.history = new History();
		this.recommendation = new WalkingRecommend();
		this.analyses = new WalkingAnalyse();
		this.monitor = new WalkingMonitor();
	}
	
	ActivityHistory getWalkingHistory(Context context){
		return history.getWalkerHistory(context);
	}
	
	ActivityHistory getRunningHistory(Context context){
		return history.getRunnerHistory(context);
	}
	
	ActivityHistory getWeightLossHistory(Context context){
		return history.getHistory("WeightLossHistoryTable");
	}
	
}
