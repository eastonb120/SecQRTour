package com.birdsong.secqrtour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This is what the logic is after the user logs in. Two sets of permissions need to accounted for
 * user and admin. They will use the same interface such as a fragment the difference will rely on a
 * table in the database which details what they can and cannot do. Users table has IS_ADMIN column
 * we need to leverage that.
 */
//TODO Create a table that maps the user permissions to the tasks they can perform.
public class loggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
    }
}
