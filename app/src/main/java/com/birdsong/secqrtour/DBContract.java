package com.birdsong.secqrtour;

import android.provider.BaseColumns;

/***
 * Class contains nested static classes that give a layout of the table information used in the
 * SQLite database. It also contains the SQL Transactions for DBHelper.onCreate() method when
 * application is first installed.
 */
public class DBContract {

    /**
     * Users class defines schema of the users table
     */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String KEY = "UID";
        public static final String IS_ENABLED = "isEnabled";
        public static final String USERNAME = "username";
        public static final String F_NAME = "fName";
        public static final String L_NAME = "lName";
        public static final String PASSWORD = "password";
        public static final String IS_ADMIN = "isAdmin";
    }

    /**
     * Tour class defines schema of the tours table
     */
    //TODO refine below tables when ready to implement i.e. get better idea of program structure
    public static class Tour implements  BaseColumns {
        public static final String TABLE_NAME = "Tour";
        public static final String KEY = "TID";
        public static final String S_TIME = "startTime";
        public static final String E_Time = "endTime";
    }

    /**
     * Point class defines schema of the point table
     */
    public static class Point implements  BaseColumns {
        public static final String TABLE_NAME = "point";
        public static final String KEY = "PID";
        public static final String QR_TAG = "QRTag";
    }

    /**
     * Event class defines schema of the event table
     */
    public static class Event implements BaseColumns {
        //enum Defines the type of events that guard can comment on during patrol
        public enum Type{
            incident, checkPoint
        }
        public static final String TABLE_NAME = "event";
        public static final String KEY = "EID";
        public static final String E_TYPE = "eventType";
        public static final String T_STAMP = "timeStamp";
        public static final String COMMENT = "comment";
    }

    /**
     * SQL_BUILDUSERTABLE defines the queries to be ran when DBHelper.onCreate() is called
     */
    public static class SQL_BUILDUSERTABLE{
        /**
         * Variables that define the default administrator account when application freshly
         * installed
         */
        public static final String ADMIN_NAME = "'qradmin'";
        public static final String ADMIN_FNAME = "'QR'";
        public static final String ADMIN_LNAME = "'Admin'";
        public static final String ADMIN_PASSWORD = "'password'";
        public static final String ADMIN_IS_ENABLED = "'true'";
        public static final String ADMIN_IS_ADMIN = "'true'";

        /**
         * Query that is ran to create user table and columns in the table
         */
        public static final String T_BUILDQUERY = "CREATE TABLE " + Users.TABLE_NAME + "("
                + Users.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Users.IS_ENABLED + " BOOLEAN NOT NULL, "
                + Users.USERNAME + " TEXT UNIQUE NOT NULL, "
                + Users.F_NAME + " TEXT NOT NULL, "
                + Users.L_NAME + " TEXT NOT NULL, "
                + Users.IS_ADMIN +" BOOLEAN NOT NULL, "
                + Users.PASSWORD + " TEXT NOT NULL)";

        /**
         * Query that is ran to populate default administration account into the user table
         * This is called right after the user table is created to allow a default account
         * so user can get past the door.
         */
        public static final String T_ENTRYQUERY = "INSERT INTO " + Users.TABLE_NAME + "("
                //Defines columns that will be populated
                + Users.IS_ENABLED + ", "
                + Users.USERNAME + ", "
                + Users.F_NAME + ", "
                + Users.L_NAME + ", "
                + Users.PASSWORD + ", "
                + Users.IS_ADMIN + ")"
                + " VALUES ( "
                //Defines what the columns will be populated with for default admin account
                + ADMIN_IS_ENABLED + ", "
                + ADMIN_NAME + ", "
                + ADMIN_FNAME + ", "
                + ADMIN_LNAME + ", "
                + ADMIN_PASSWORD + ", "
                + ADMIN_IS_ADMIN + ")";

    }

    /**
     * SQL_USERQUERIES defines queries for all transactions between UserManager and database
     */
    //TODO Make UserManager Actually Manage User, i.e. use to pass user between activities and fragments
    //TODO This will be done via intents for activities and I have no f@#$ing idea for fragments
    public static class SQL_USERQUERIES{
        /**
         * This query is for logging in. Pass username to function that uses this query and retrieve
         * the password then the password that user input is compared to database password.
         */
        public static final String SEARCH_PASSQUERY = "SELECT "
                + Users.USERNAME + ", "
                + Users.PASSWORD
                + " FROM "
                + Users.TABLE_NAME;
        public static final String GET_TABLE = "SELECT "
                + Users.IS_ENABLED + ", "
                + Users.USERNAME + ", "
                + Users.F_NAME + ", "
                + Users.L_NAME + ", "
                + Users.IS_ADMIN + ", "
                + Users.PASSWORD + " "
                + " FROM "
                + Users.TABLE_NAME;
    }


}

