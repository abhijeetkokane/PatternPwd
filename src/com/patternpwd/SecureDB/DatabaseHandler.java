package com.patternpwd.SecureDB;

import java.util.ArrayList;
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
	private static final String DATABASE_NAME = "SecurePatterns";

	//table name
	private static final String TABLE_REGISTRATION = "registration";
	
	private static final String KEY_EMAIL = "emailId";
	private static final String PATTERN = "patterns";
	private static final String FULLNAME = "fullname";
	private static final String MOBNO = "mobno";
	//private static final String ADDRESS = "address";
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Create Message Table
		String CREATE_REGISTRATION_TABLE = "CREATE TABLE " + TABLE_REGISTRATION + "("
				+ KEY_EMAIL + " TEXT PPRIMARY KEY,"
				+ PATTERN + " TEXT,"
				+ FULLNAME + " TEXT,"
				+ MOBNO + " TEXT" + ")";
		
		db.execSQL(CREATE_REGISTRATION_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
		
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

//////////////////////////////Add New Contact//////////////////////////////////////////
	public void addUser(UserRegister user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put(KEY_EMAIL, user.getEmail()); // User Email
		values.put(PATTERN, user.getPattern()); // User Pattern
		values.put(FULLNAME, user.getFName()); // User Name
		values.put(MOBNO, user.getMobNo()); // User Mob No
		//values.put(ADDRESS, user.getAddress()); // User Address

		// Inserting Row
		db.insert(TABLE_REGISTRATION, null, values);
		db.close(); // Closing database connection
	}

//////////////////////////////Updating Single contact//////////////////////////////////////////

	public int updateUserPattern(UserRegister user) {
	SQLiteDatabase db = this.getWritableDatabase();
	
	ContentValues values = new ContentValues();
	values.put(KEY_EMAIL, user.getEmail()); // user email
	values.put(PATTERN, user.getPattern()); // user new pattern
	values.put(FULLNAME, user.getFName()); // user name
	values.put(MOBNO, user.getMobNo()); // user mob no
	
	
	// updating row
	int record_update =db.update(TABLE_REGISTRATION, values, KEY_EMAIL + " = ?",
			new String[] { String.valueOf(user.getEmail()) });
	
	if(db.isOpen()){
		db.close();	
	}
	
	return record_update;
	}
	
	
//////////////////////////////Getting single contact//////////////////////////////////////////
	public UserRegister getUser(String emailid) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_REGISTRATION, new String[] { KEY_EMAIL,PATTERN,FULLNAME,MOBNO }
		, KEY_EMAIL + "=?",
				new String[] { String.valueOf(emailid) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		UserRegister contact = new UserRegister(cursor.getString(0),
				cursor.getString(1),cursor.getString(2),cursor.getString(3));
		
		cursor.close();
		db.close();
		return contact;
	}
	
//////////////////////////////Getting All contact//////////////////////////////////////////
	
	public List<UserRegister> getAllUsers() {
		List<UserRegister> contactList = new ArrayList<UserRegister>();
		// Select All Query
		SQLiteDatabase db = this.getWritableDatabase();
		
		String selectQuery = "SELECT  * FROM " + TABLE_REGISTRATION;
		
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				UserRegister user = new UserRegister();
				
				user.setEmail(cursor.getString(0));
				user.setPattern(cursor.getString(1));
				user.setFName(cursor.getString(2));
				user.setMobNo(cursor.getString(3));
				//user.setAddress(cursor.getString(4));
				
				// Adding contact to list
				contactList.add(user);
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();
		// return contact list
		return contactList;
	}

//////////////////////////////Deleting Single contact//////////////////////////////////////////
	public void deleteUser(UserRegister user) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_REGISTRATION, KEY_EMAIL + " = ?",
				new String[] { String.valueOf(user.getEmail()) });
		db.close();
	}
	
	int count;

//////////////////////////////Get DB Count//////////////////////////////////////////
	public int getDBCount() {
		SQLiteDatabase db = this.getReadableDatabase();
		String countQuery = "SELECT  * FROM " + TABLE_REGISTRATION;
		Cursor cursor = db.rawQuery(countQuery, null);
		count = cursor.getCount();
		cursor.close();
		db.close();
		return count;
	}
}