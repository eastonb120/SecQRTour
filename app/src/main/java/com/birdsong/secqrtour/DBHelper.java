package com.birdsong.secqrtour;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DBHelper is the implementation of the SQLite database. All queries that need to be called from
 * a function should be in here.
 */

public class DBHelper extends SQLiteOpenHelper {
    //Initial database version 1
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SecQRTour.db";
    SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called on a fresh install. So, if changes are made to any functions and need to be
     * tested then a reinstall is required.
     *@param db this parameter is passed automatically. Dev doesn't call this function.
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //
        db.execSQL(DBContract.SQL_BUILDUSERTABLE.T_BUILDQUERY);
        db.execSQL(DBContract.SQL_BUILDUSERTABLE.T_ENTRYQUERY);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         * Leaving this as a stub. I foresee no need to expand on function yet until we release
         * another version if that happens... lol
         */
    }

    /**
     * @param username the username to be queried
     * @return the password of the user that was queried
     */
    public String searchPassword(String username){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(DBContract.SQL_USERQUERIES.SEARCH_PASSQUERY, null);
        String entryUserName;
        String entryPassword = "User not found.";
        if(cursor.moveToFirst())
        {
            do{
                entryUserName = cursor.getString(0);
                if(entryUserName.equals(username)){
                    entryPassword = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        db.close();
        return entryPassword;
    }

    public Cursor getTable(){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(DBContract.SQL_USERQUERIES.GET_TABLE, null);
        return cursor;
    }

}
