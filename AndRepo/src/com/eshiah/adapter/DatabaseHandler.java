package com.eshiah.adapter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "rulesManager";
 
    // Contacts table name
    private static final String TABLE_RULES = "Rules";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "rule_id";
    private static final String KEY_NAME = "rule_name";
    private static final String KEY_CATEGORY = "rule_category";
    private static final String KEY_INTERVAL = "rule_interval";
    private static final String KEY_FROM= "date_from";
    private static final String KEY_TO= "date_to";
    //always write to the db in seconds - convert all min/hrs to seconds and store.
    private static final String PERIOD="period";
    private static final String DIRECTION="direction";
    private static final String TRIG_ACTION="trigger_action";
    
    
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_RULES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_CATEGORY + " TEXT," + KEY_INTERVAL + "BOOLEAN," + KEY_FROM + "DATE" + KEY_TO + "DATE" + PERIOD + "INTEGER" +
                DIRECTION + "TEXT"+ "TRIG_ACTION" + "TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RULES);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new rule
    void addContact(RulesVO rule) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, rule.getRuleName()); // Contact Name
        values.put(KEY_CATEGORY, rule.getRuleCategory()); // Contact Phone
        values.put(KEY_INTERVAL,rule.isInterval() ); // Contact Phone
        values.put(KEY_FROM,rule.getDateFrom().toString());
        values.put(KEY_TO,rule.getDateTo().toString() );
        values.put(PERIOD,rule.getPeriod() );
        values.put(DIRECTION,rule.getDirection() );
        values.put(TRIG_ACTION,rule.getTrigAction() );
        // Inserting Row
        db.insert(TABLE_RULES, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    RulesVO getRule(int id) throws NumberFormatException, ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        DateFormat  fromUser = new SimpleDateFormat("dd/MM/yyyy");
        Cursor cursor = db.query(TABLE_RULES, new String[] { KEY_ID,
                KEY_NAME, KEY_CATEGORY,KEY_INTERVAL,KEY_FROM,KEY_TO,PERIOD,DIRECTION,TRIG_ACTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        RulesVO rule = new RulesVO(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),Boolean.parseBoolean(cursor.getString(3)),fromUser.parse(cursor.getString(4)),fromUser.parse(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)),cursor.getString(7),cursor.getString(8));
       Date dt= fromUser.parse(cursor.getString(4));
        // return contact
        return rule;
    }
 
    // Getting All Contacts
    public List<RulesVO> getAllContacts() throws ParseException {
        List<RulesVO> rulesList = new ArrayList<RulesVO>();
        DateFormat  fromUser = new SimpleDateFormat("dd/MM/yyyy");
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RULES;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RulesVO ruleVO = new RulesVO();
                ruleVO.setRuleId(Integer.parseInt(cursor.getString(0)));
                ruleVO.setRuleName(cursor.getString(1));
                ruleVO.setRuleCategory(cursor.getString(2));
                ruleVO.setInterval(Boolean.parseBoolean(cursor.getString(3)));
                ruleVO.setDateFrom(fromUser.parse(cursor.getString(4)));
                ruleVO.setDateTo(fromUser.parse(cursor.getString(5)));
                ruleVO.setPeriod(Integer.parseInt(cursor.getString(6)));
                ruleVO.setDirection(cursor.getString(7));
                ruleVO.setTrigAction(cursor.getString(8));
                // Adding contact to list
                rulesList.add(ruleVO);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return rulesList;
    }
 
    // Updating single contact
    public int updateContact(RulesVO ruleVO) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, ruleVO.getRuleName()); // Contact Name
        values.put(KEY_CATEGORY, ruleVO.getRuleCategory()); // Contact Phone
        values.put(KEY_INTERVAL,ruleVO.isInterval() ); // Contact Phone
        values.put(KEY_FROM,ruleVO.getDateFrom().toString());
        values.put(KEY_TO,ruleVO.getDateTo().toString() );
        values.put(PERIOD,ruleVO.getPeriod() );
        values.put(DIRECTION,ruleVO.getDirection() );
        values.put(TRIG_ACTION,ruleVO.getTrigAction() );
 
        // updating row
        return db.update(TABLE_RULES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(ruleVO.getRuleId()) });
    }
 
    // Deleting single contact
    public void deleteContact(RulesVO ruleVO) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RULES, KEY_ID + " = ?",
                new String[] { String.valueOf(ruleVO.getRuleId()) });
        db.close();
    }
 
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_RULES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}
