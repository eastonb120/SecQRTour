package com.birdsong.secqrtour;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;


/**
 * This is the first class that will be instantiated when application has started.
 * UserManager class manages logging in and out for all user groups. If login successful then
 * it will pass control to:
 * -PatrolManager for normal users
 * -AppManager for Administrators
 *
 */

public class UserManager {
    //Value null until getInstance() called.
    private static UserManager single_instance = null;
    private User activeUser = null;
    private Context context;
    //Constructor called by getInstance if single_instance is null. Otherwise, a reference to the
    //UserManager is returned.
    private UserManager(Context context){this.context = context;}
    public static UserManager getInstance(Context context){
        if (single_instance == null){
            //Call constructor
            single_instance = new UserManager(context);
        }
        //Return a reference of UserManager because object exists
        return single_instance;
    }
    public boolean loginActiveUser(DBHelper dbH, String username, String password){
        boolean loginSuccessful = false;
        Cursor results = dbH.getTable();
        results.moveToFirst();
        if(results.moveToFirst())
        {
            do{
                if(username.equals(results.getString(1))) {
                    if (password.equals(results.getString(5))) {
                        activeUser = new User();
                        activeUser.username = results.getString(1);
                        activeUser.isEnabled = Boolean.parseBoolean(results.getString(0));
                        activeUser.isAdmin = Boolean.parseBoolean(results.getString(4));
                        activeUser.fName = results.getString(2);
                        activeUser.lName = results.getString(3);
                        loginSuccessful = true;
                    }
                }

            }
            while (results.moveToNext());

        }
        results.close();
        return loginSuccessful;
    }

    private class User {
        private Boolean isEnabled = false;
        private String username = null;
        private String fName = null;
        private String lName = null;
        private boolean isAdmin = false;

        public Boolean getEnabled() {
            return isEnabled;
        }

        public void setEnabled(Boolean enabled) {
            isEnabled = enabled;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getfName() {
            return fName;
        }

        public void setfName(String fName) {
            this.fName = fName;
        }

        public String getlName() {
            return lName;
        }

        public void setlName(String lName) {
            this.lName = lName;
        }

        public boolean getIsAdmin() {
            return isAdmin;
        }

        public void setAdmin(boolean admin) {
            isAdmin = admin;
        }
    }

}
