/**
 * 
 */
package com.eshiah.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author akaruppaiah
 *
 */
public class RuleListDatabaseHelper {

	private static final int DATABASE_VERSION = 4;
	private static final String DATABASE_NAME = "ruletracker.db";
	private static final String TABLE_NAME = "rules";
	public static final String RULETRACKER_COLUMN_ID = "id";
	public static final String RULETRACKER_COLUMN_RULENAME = "rulename";
	public static final String RULETRACKER_COLUMN_RULETRIGGER = "ruletrigger";
	public static final String RULETRACKER_COLUMN_RULEACTION = "ruleaction";

	       
	
	private RuleTrackerOpenHelper openHelper;
	private SQLiteDatabase database;
	
	public RuleListDatabaseHelper(Context context) {
		openHelper=new RuleTrackerOpenHelper(context);
		database = openHelper.getWritableDatabase();
	}
	
	
	
	private class RuleTrackerOpenHelper extends SQLiteOpenHelper{

		


		 RuleTrackerOpenHelper(Context context) {
			// TODO Auto-generated constructor stub
			super(context, DATABASE_NAME,null,DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS timerecords");
			onCreate(db);
		}

	}
}
