/**
 * 
 */
package com.eshiah.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author akaruppaiah
 *
 */
public class RuleListDatabaseHelper {

	private static final int DATABASE_VERSION = 5;
	private static final String DATABASE_NAME = "ruletracker.db";
	private static final String TABLE_NAME = "rules";
	public static final String RULETRACKER_COLUMN_ID = "_id";
	public static final String RULETRACKER_COLUMN_RULENAME = "rulename";
	public static final String RULETRACKER_COLUMN_RULETRIGGER = "ruletrigger";
	public static final String RULETRACKER_COLUMN_RULEACTION = "ruleaction";

	       
	
	private RuleTrackerOpenHelper openHelper;
	private SQLiteDatabase database;
	
	public RuleListDatabaseHelper(Context context) {
		openHelper=new RuleTrackerOpenHelper(context);
		database = openHelper.getWritableDatabase();
	}
	public void saveRuleRecord(String ruleName,String ruleTrigger, String ruleAction) {
		//database.execSQL("INSERT INTO "+TABLE_NAME
		//+ " (rulename, ruletrigger,ruleaction)"
		//+ " VALUES ('" + ruleName + "', '" + ruleTrigger + "', '" + ruleAction + "')"
		//);
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(RULETRACKER_COLUMN_RULENAME, ruleName);
		contentValues.put(RULETRACKER_COLUMN_RULETRIGGER, ruleTrigger);
		contentValues.put(RULETRACKER_COLUMN_RULEACTION, ruleAction);
		database.insert(TABLE_NAME, null, contentValues);
	}
	
	public void updateRuleRecord(String mRowId,String ruleName,String ruleTrigger, String ruleAction) {
		//database.execSQL("INSERT INTO "+TABLE_NAME
		//+ " (rulename, ruletrigger,ruleaction)"
		//+ " VALUES ('" + ruleName + "', '" + ruleTrigger + "', '" + ruleAction + "')"
		//);
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(RULETRACKER_COLUMN_RULENAME, ruleName);
		contentValues.put(RULETRACKER_COLUMN_RULETRIGGER, ruleTrigger);
		contentValues.put(RULETRACKER_COLUMN_RULEACTION, ruleAction);
		database.update(TABLE_NAME, contentValues, RULETRACKER_COLUMN_ID + "=" + mRowId, null);
	}
	public Cursor getAllRuleRecords() {
		return database.rawQuery(
		"select * from " + TABLE_NAME,
		null
		);
		}
	
	private class RuleTrackerOpenHelper extends SQLiteOpenHelper{

		


		 RuleTrackerOpenHelper(Context context) {
			// TODO Auto-generated constructor stub
			super(context, DATABASE_NAME,null,DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(
					"CREATE TABLE " + TABLE_NAME + "( "
							+ RULETRACKER_COLUMN_ID + " INTEGER PRIMARY KEY, "
							+ RULETRACKER_COLUMN_RULENAME + " TEXT, "
							+ RULETRACKER_COLUMN_RULETRIGGER + " TEXT, "
							+ RULETRACKER_COLUMN_RULEACTION + " TEXT )"					
					);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
			onCreate(db);
		}
		
		
		

	}
}
