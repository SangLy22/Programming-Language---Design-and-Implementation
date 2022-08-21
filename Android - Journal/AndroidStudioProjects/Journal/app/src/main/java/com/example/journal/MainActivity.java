package com.example.journal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // declare variable
    Button signUpButton;
    Button loginButton;
    EditText usernameEditText;
    EditText passwordEditText;
    String login_username;
    String login_password;
    String verify_user_pass;
    static String Login;

    // assign variables to their respective id
    // set on click listener for both text box
    // set on click listen for both button
    // when log in button get click, call load account function
    // when sign up button get click, call sign up page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Journal by Sang Ly");

        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_sign_up_page();
            }
        });

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        usernameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_account();
            }
        });
    }

    // open sign up page
    public void open_sign_up_page(){
        Intent startNewActivity = new Intent(this, signUp.class);
        startActivity(startNewActivity);
    }

    // declare variable
    private static String USERNAME_PASSWORD = "Journal_By_Sang_Ly";
    private static String USERNAME_PASSWORD_INFO = "username_password_info";

    // verify if user input (username and password) matched with database user log in account
    // if match not found, call log in error message function
    // if match found, call access journal function
    public void load_account(){
        login_username = usernameEditText.getText().toString();
        login_password = passwordEditText.getText().toString();
        Login = login_username  + login_password;

        SharedPreferences sp = getSharedPreferences(USERNAME_PASSWORD, MODE_PRIVATE);
        verify_user_pass = sp.getString(USERNAME_PASSWORD_INFO+Login,"");

        if(verify_user_pass.toLowerCase().equals( Login.toLowerCase()) && Login.toLowerCase() !=  ""){
            access_journal();
        }
        else{
            Login_Error_Message();
        }
    }

    // display journal page
    public void access_journal(){
        Intent startNewActivity = new Intent(this, Journal.class);
        startActivity(startNewActivity);
    }

    // display error message alert box
    public void Login_Error_Message(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Incorrect Username and Password");
        builder.setCancelable(false);
        builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clear_user_pass();
            }
        });
        builder.show();
    }

    // re-assign username and password text box
    public void clear_user_pass(){
        usernameEditText.setText("");
        passwordEditText.setText("");
    }

}
