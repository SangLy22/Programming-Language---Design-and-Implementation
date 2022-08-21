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

public class signUp extends AppCompatActivity {

    // declare variable
    Button createButton;
    Button backToLogInButton;
    EditText usernameEditText;
    EditText passwordEditText;
    String username;
    static String password;
    static String combine_user_pass;

    // assign variable to their respective id
    // set on click listener for both text box (username and password)
    // set on click listener for both button (log in and sign up)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        setTitle("Create Account by Sang Ly");

        createButton = (Button) findViewById(R.id.createButton);
        backToLogInButton = (Button) findViewById(R.id.backToLogInButton);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        usernameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        passwordEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate_username();
            }
        });
        backToLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_To_Log_In();
            }
        });
    }

    // declare variable
    private static String VALIDATE_USERNAME = "Username";
    private static String VALIDATE_USERNAME_INFO = "username_info";

    // verify if username is not null, or already taken
    // verify if password is not null
    // call username validation error message function if username or password is null
    // or username is already taken
    public void validate_username(){
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        String verify_username;

        SharedPreferences page1 = getSharedPreferences(VALIDATE_USERNAME, MODE_PRIVATE);
        verify_username = page1.getString(VALIDATE_USERNAME_INFO+username,"");

        if(username.equals("")){
            username_validation_fail_error_message("Username cannot be blank");
        }
        else if(password.equals("")){
            username_validation_fail_error_message("Password cannot be blank");
        }
        else if(verify_username.toLowerCase().equals( username.toLowerCase())){
            username_validation_fail_error_message("Username already taken");
        }
        else{
            SharedPreferences spPage1 = getSharedPreferences(VALIDATE_USERNAME,MODE_PRIVATE);
            SharedPreferences.Editor edPage1 = spPage1.edit();
            edPage1.putString(VALIDATE_USERNAME_INFO+username, username);
            edPage1.apply();

            create_Account();
        }
    }

    // declare variable
    private static String USERNAME_PASSWORD = "Journal_By_Sang_Ly";
    private static String USERNAME_PASSWORD_INFO = "username_password_info";

    // create account - share username and password to shared preferences
    // call account create success function
    public void create_Account() {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        combine_user_pass = username + password;

        SharedPreferences sp = getSharedPreferences(USERNAME_PASSWORD, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(USERNAME_PASSWORD_INFO + combine_user_pass, combine_user_pass);
        edit.apply();

        account_create_success();
    }

    // display alert box
    public void account_create_success(){
        AlertDialog.Builder builder = new AlertDialog.Builder(signUp.this);
        builder.setMessage("Account Created");
        builder.setCancelable(false);
        builder.setPositiveButton("Log in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                back_To_Log_In();
            }
        });
        builder.show();
    }

    // display log in page
    public void back_To_Log_In(){
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    // display alert box
    public void username_validation_fail_error_message(String error_message){
        AlertDialog.Builder builder = new AlertDialog.Builder(signUp.this);
        builder.setMessage(error_message);
        builder.setCancelable(false);
        builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clear_user_pass();
            }
        });
        builder.show();
    }

    // re-assign username and password edit text
    public void clear_user_pass(){
        usernameEditText.setText("");
        passwordEditText.setText("");
    }
}