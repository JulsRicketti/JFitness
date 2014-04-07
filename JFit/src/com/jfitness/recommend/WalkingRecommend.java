package com.jfitness.recommend;

import android.content.Context;

import com.jfitness.persistance.DatabaseAdapter;

//try to make it a singleton
public class WalkingRecommend implements Recommend {

	DatabaseAdapter dbAdapter;
	
	
	
	
	//take a look at context! I am not sure what this is!
	public String recommend(Context context){
		dbAdapter = new DatabaseAdapter(context);
		dbAdapter.open();
		
		if(dbAdapter.isEmpty(dbAdapter.WALKER_HISTORY_TABLE)){
			
		}
		else{
			
		}
		return null;
	}
	
}
