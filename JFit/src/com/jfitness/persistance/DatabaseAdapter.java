package com.jfitness.persistance;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DatabaseAdapter {
	private final String TAG="com.jfit.persistance";
	static final String DATABASE_NAME="jfitdb.txt";
	static final int DATABASE_VERSION=1;
	static final String KEY_ID="_ID";
	
	public static final String USER_PROFILE_TABLE="UserProfile";
	public static final String AGE="age";
	public static final String WEIGHT="weight";
	public static final String HEIGHT="height";
	public static final String SEX="sex";
	public static final String WEIGHT_LOSS_OBJECTIVE = "weight loss objective";
	public static final String RUNNING_OBJECTIVE = "running objective";
	public static final String WALKING_OBJECTIVE = "walking objective";
	public static final String PARAMETER = "parameter";
		
	public static final String RUNNER_HISTORY_TABLE = "RunnerHistoryTable";
	public static final String RECOMMENDATION_DATE = "recommendation date";
	public static final String RECOMMENDATION = "recommendation";
	public static final String ACTIVITY_DATE = "activity date";
	public static final String ACTIVITY = "activity";
	public static final String ANALYSES = "analyses";
	
	public static final String WALKER_HISTORY_TABLE = "WalkerHistoryTable";
//	static final String WALKER_RECOMMENDATION_DATE = "recommendation date";
//	static final String WALKER_RECOMMENDATION = "recommendation";
//	static final String WALKER_ACTIVITY_DATE = "activity date";
//	static final String WALKER_ACTIVITY = "activity";
//	static final String WALKER_ANALYSES = "analyses";
	
	public static final String WEIGHT_LOSS_HISTORY_TABLE = "WeightLossHistoryTable";
//	static final String WEIGHT_LOSS_RECOMMENDATION_DATE = "recommendation date";
//	static final String WEIGHT_LOSS_RECOMMENDATION = "recommendation";
//	static final String WEIGHT_LOSS_ACTIVITY_DATE = "activity date";
//	static final String WEIGHT_LOSS_ACTIVITY = "activity";
//	static final String WEIGHT_LOSS_ANALYSES = "analyses";
	
	private static String key=""; 
	public static long rowid1;
	
	static String CREATE_USER_TABLE = "CREATE TABLE " + USER_PROFILE_TABLE + "(" 
	+ KEY_ID + " INTEGER PRIMARY KEY," +AGE + " TEXT ," +  WEIGHT + " TEXT ," + HEIGHT + " TEXT ," + SEX + " TEXT ," + WEIGHT_LOSS_OBJECTIVE +" TEXT ," + RUNNING_OBJECTIVE +" TEXT ," + WALKING_OBJECTIVE+" TEXT ," + PARAMETER + " TEXT)";
	
	static String CREATE_RUNNER_TABLE = "CREATE TABLE " + RUNNER_HISTORY_TABLE + "(" 
			+ KEY_ID + " INTEGER PRIMARY KEY," +RECOMMENDATION_DATE + " TEXT ," +  RECOMMENDATION+ " TEXT ," + ACTIVITY_DATE + " TEXT ," + ACTIVITY+ " TEXT ," + ANALYSES + " TEXT)";
	
	static String CREATE_WALKER_TABLE = "CREATE TABLE " + WALKER_HISTORY_TABLE+ "(" 
			+ KEY_ID + " INTEGER PRIMARY KEY," +RECOMMENDATION_DATE + " TEXT ," +  RECOMMENDATION+ " TEXT ," + ACTIVITY_DATE + " TEXT ," + ACTIVITY+ " TEXT ," + ANALYSES + " TEXT)";
	
	static String CREATE_WEIGHT_LOSS_TABLE = "CREATE TABLE " + WEIGHT_LOSS_HISTORY_TABLE + "(" 
			+ KEY_ID + " INTEGER PRIMARY KEY," +RECOMMENDATION_DATE + " TEXT ," +  RECOMMENDATION+ " TEXT ," + ACTIVITY_DATE + " TEXT ," + ACTIVITY+ " TEXT ," + ANALYSES + " TEXT)";
	
	SQLiteDatabase db;
	Context context;
	DatabaseHelper mdb;
	  
	  public DatabaseAdapter(Context con)
	  {
		  context=con;
		  mdb = new DatabaseHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
		  
	  }
	  public  DatabaseAdapter  open() throws SQLException
	  {
		db=mdb.getWritableDatabase();
		return this;
	  }
	public void close() 
	{
		db.close();
	}

	public void EnterCreateUser(String age, String weight, String height,String sex,String weightLossObjective, String runningObjective, String walkingObjective, String parameter){
		ContentValues values= new ContentValues();
				
		values.put(AGE,age);
		values.put(WEIGHT,weight);
		values.put(HEIGHT,height);
		values.put(SEX, sex);
		values.put(WEIGHT_LOSS_OBJECTIVE,weightLossObjective);
		values.put(RUNNING_OBJECTIVE,runningObjective);
		values.put(WALKING_OBJECTIVE,walkingObjective);
		values.put(PARAMETER, parameter);
		
		long rowid=db.insert(USER_PROFILE_TABLE, null, values);
		rowid1=rowid;
		Log.d(TAG, "IDD"+rowid1);
		
	}
	
	public void EnterCreateHistory(String tableName, String recommendationDate, String recommendation, String activityDate, String activity, String analyses){
		ContentValues values= new ContentValues();
		
		values.put(RECOMMENDATION_DATE,recommendationDate);
		values.put(RECOMMENDATION,recommendation);
		values.put(ACTIVITY_DATE,activityDate);
		values.put(ACTIVITY, activity);
		values.put(ANALYSES,analyses);
		
		long rowid=db.insert(tableName, null, values);
		rowid1=rowid;
		Log.d(TAG, "IDD"+rowid1);
		
	}
//	
//	public void EnterCreateRunnerHistory(String recommendationDate, String recommendation, String activityDate, String activity, String analyses){
//		ContentValues values= new ContentValues();
//		
//		values.put(RUNNER_RECOMMENDATION_DATE,recommendationDate);
//		values.put(RUNNER_RECOMMENDATION,recommendation);
//		values.put(RUNNER_ACTIVITY_DATE,activityDate);
//		values.put(RUNNER_ACTIVITY, activity);
//		values.put(RUNNER_ANALYSES,analyses);
//		
//		long rowid=db.insert(RUNNER_HISTORY_TABLE, null, values);
//		rowid1=rowid;
//		Log.d(TAG, "IDD"+rowid1);
//		
//	}
//	
//	public void EnterCreateWeightLossHistory(String recommendationDate, String recommendation, String activityDate, String activity, String analyses){
//		ContentValues values= new ContentValues();
//		
//		values.put(WEIGHT_LOSS_RECOMMENDATION_DATE,recommendationDate);
//		values.put(WEIGHT_LOSS_RECOMMENDATION,recommendation);
//		values.put(WEIGHT_LOSS_ACTIVITY_DATE,activityDate);
//		values.put(WEIGHT_LOSS_ACTIVITY, activity);
//		values.put(WEIGHT_LOSS_ANALYSES,analyses);
//		
//		long rowid=db.insert(WEIGHT_LOSS_HISTORY_TABLE, null, values);
//		rowid1=rowid;
//		Log.d(TAG, "IDD"+rowid1);
//		
//	}
	
	public long getID(long __id){
		
		rowid1=__id;
		return __id;
	}

	//stuff for the game, we select things based on the primary key
	
	public long getRowId() {
		return rowid1;
	}
	
	public String getAge(String keyid) {
		Cursor cursor = db.query(USER_PROFILE_TABLE, null, "KEYID=?",new String[]{keyid},null, null,null,null);
		if(cursor.getCount()<1)
		{
			cursor.close();
			return "????";
		}
		cursor.moveToFirst();
		String age = cursor.getString(cursor.getColumnIndex(AGE));
		cursor.close();
		return age;
	}
	
	public String getWeight (String keyid){
		Cursor cursor = db.query(USER_PROFILE_TABLE, null, "KEYID=?",new String[]{keyid},null, null,null,null);
		if(cursor.getCount()<1)
		{
			cursor.close();
			return "????";
		}
		cursor.moveToFirst();
		String weight = cursor.getString(cursor.getColumnIndex(WEIGHT));
		cursor.close();
		return weight;
	}
	
	public String getHeight (String keyid){
		Cursor cursor = db.query(USER_PROFILE_TABLE, null, "KEYID=?",new String[]{keyid},null, null,null,null);
		if(cursor.getCount()<1)
		{
			cursor.close();
			return "????";
		}
		cursor.moveToFirst();
		String height = cursor.getString(cursor.getColumnIndex(HEIGHT));
		cursor.close();
		return height;
	}
	
	public String getSex(String keyid){
		Cursor cursor = db.query(USER_PROFILE_TABLE, null, "KEYID=?",new String[]{keyid},null, null,null,null);
		if(cursor.getCount()<1)
		{
			cursor.close();
			return "????";
		}
		cursor.moveToFirst();
		String sex = cursor.getString(cursor.getColumnIndex(SEX));
		cursor.close();
		return sex;
	}
	
	
	public Cursor getAllData(String tableName) {
        // using simple SQL query
        return db.rawQuery("SELECT * FROM " + tableName, null);
    }
	public Cursor getallCols(String id) throws SQLException {
        Cursor mCursor = db.query(USER_PROFILE_TABLE, new String[] { AGE,WEIGHT,HEIGHT,SEX, WEIGHT_LOSS_OBJECTIVE, RUNNING_OBJECTIVE, WALKING_OBJECTIVE, PARAMETER
                 }, null, null, null, null, null);
        Log.e("getallcols zmv", "opening successfull"+mCursor);
        return mCursor;
    }
 
    public Cursor getColsById(String id) throws SQLException {
        Cursor mCursor = db.query(USER_PROFILE_TABLE, new String[] { AGE,WEIGHT,HEIGHT,SEX, WEIGHT_LOSS_OBJECTIVE, RUNNING_OBJECTIVE, WALKING_OBJECTIVE, PARAMETER
        		}, KEY_ID + " = " + id, null, null, null, null);
        Log.e("getallcols zmv", "opening successfull"+mCursor);
        return mCursor;
    }
    
    public boolean userProfileIsEmpty(){
    	Cursor mCursor = db.rawQuery("SELECT * FROM " + USER_PROFILE_TABLE, null);

    	if (mCursor.moveToFirst())
    	  return false;
    	else
    	   return true;
    	
    }
	
    public void deleteUserTable(){
    	db.delete(USER_PROFILE_TABLE, null, null);
    }
    
    public boolean isEmpty(String tableName){
    	Cursor mCursor = db.rawQuery("SELECT * FROM " + tableName, null);

    	if (mCursor.moveToFirst()) //is not empty
    	  return false;
    	else //is empty
    	   return true;

    }
    
    public void deleteTable(String tableName){
    	db.delete(tableName, null, null);
    }
}
