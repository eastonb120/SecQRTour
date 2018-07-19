package com.birdsong.secqrtour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * Declare variables that are used in the activity
     */

    private String username = null;
    private String password = null;

    /**
     * Declare the view objects that are used in the activity
     */

    private TextView tvUsername;
    private TextView tvPassword;
    private Button btnLogin;

    /**
     * Instantiate the objects that are needed to connect to database and manage the login process
     */

    DBHelper helper = new DBHelper(this);
    UserManager uMan = UserManager.getInstance(this);

    /**
     * The onClickListener that is associated to the login button
     */

//    private View.OnClickListener loginButtonListener = new View.OnClickListener(){
//
//        @Override
//        public void onClick(View v){
//            //Grab the information from the textviews
//            username = tvUsername.getText().toString();
//            password = tvPassword.getText().toString();
//            //Pass the string from the username field to the searchPassword and compare to password
//            //field
//            if(password.equals(helper.searchPassword(username))){
//                //TODO make this go to another activity
//                Toast.makeText(MainActivity.this, "User found!", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    };
    private View.OnClickListener loginButtonListener = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            //Grab the information from the textviews
            username = tvUsername.getText().toString();
            password = tvPassword.getText().toString();
            //Pass the string from the username field to the searchPassword and compare to password
            //field
            boolean loginSuccessful = uMan.loginActiveUser(helper,username,password);
            if(loginSuccessful){
                //TODO make this go to another activity
                Toast.makeText(MainActivity.this, "Going to task activity!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Wrong username and/or password!", Toast.LENGTH_SHORT).show();
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Assign activity elements to corresponding objects
         */

        tvUsername = (TextView)findViewById(R.id.username_LoginActivity);
        tvPassword = (TextView)findViewById(R.id.password_LoginActivity);
        btnLogin = (Button)findViewById(R.id.button_LoginActivity);

        /**
         * Assign the onClickListener the login button which will call helper.searchPassword()
         */

        btnLogin.setOnClickListener(loginButtonListener);






    }
}
