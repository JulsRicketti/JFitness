package com.jfitness.persistance;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
	
	public DatabaseHelper(Context context,String name,CursorFactory cursofac,int version)
	{
		super(context,name,cursofac,version);
	}

	@Override
	public void onCreate(SQLiteDatabase _db) {
	
	_db.execSQL(DatabaseAdapter.CREATE_USER_TABLE);
	_db.execSQL(DatabaseAdapter.CREATE_RUNNER_TABLE);
	_db.execSQL(DatabaseAdapter.CREATE_WALKER_TABLE);
	_db.execSQL(DatabaseAdapter.CREATE_WEIGHT_LOSS_TABLE);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
		
	Log.w( "DataAdapter","Upgrading Database from"+oldVersion+"to"+newVersion);
	_db.execSQL("Drop Table if Exists"+DatabaseAdapter.USER_PROFILE_TABLE);
	//this part hasnt been tested...
	_db.execSQL("Drop Table if Exists"+DatabaseAdapter.WALKER_HISTORY_TABLE);
	_db.execSQL("Drop Table if Exists"+DatabaseAdapter.RUNNER_HISTORY_TABLE);
	_db.execSQL("Drop Table if Exists"+DatabaseAdapter.WEIGHT_LOSS_HISTORY_TABLE);
	onCreate(_db);
		
	}

}
