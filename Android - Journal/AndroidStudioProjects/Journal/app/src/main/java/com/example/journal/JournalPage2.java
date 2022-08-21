package com.example.journal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import static com.example.journal.MainActivity.Login;

public class JournalPage2 extends AppCompatActivity {

    // declare variable
    TextInputLayout page2InputText;
    TextInputEditText page2EditText;
    EditText titleEditText;

    // assign variables to their respective id
    // load both page and title
    // when next button get click call page 3 function
    // when back button get click call page 2 function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_2);

        setTitle("Page 2");

        page2InputText = (TextInputLayout) findViewById(R.id.page2TextInput);
        page2EditText = (TextInputEditText) findViewById(R.id.page2EditText);
        titleEditText = (EditText) findViewById(R.id.titleEditText);

        load_page();
        load_title();

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                next_Go_Page_3();
            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                back_Go_page_1();
            }
        });
    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater MI = getMenuInflater();
        MI.inflate(R.menu.journal, menu);
        return true;
    }

    // switch
    // .save - hide soft keyboard and call save function
    // .about call about function
    // .logout call logout function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.save: {
                // hide keyboard
                titleEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                page2EditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                save();
            }
                return true;
            case R.id.About:
                showAbout();
                return true;
            case R.id.Logout:
                showLoginPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // display log in page
    public void showLoginPage(){
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    // Save page
    // Upon click "save"
    // two option are given "Cancel" "Save"
    // "Cancel" will not save and keep text
    // "Save" will save text when user exit app
    public void save(){
        AlertDialog.Builder builder = new AlertDialog.Builder(JournalPage2.this);
        builder.setMessage("Save this page");
        builder.setCancelable(false);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                save_page();
                save_title();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    // display about page
    public void showAbout(){
        Intent startNewActivity = new Intent(this, AboutJournal.class);
        startActivity(startNewActivity);
    }

    // display page 1
    public void   back_Go_page_1(){
        Intent startNewActivity = new Intent(this, JournalPage1.class);
        startActivity(startNewActivity);
    }

    // display page 3
    public void next_Go_Page_3(){
        Intent startNewActivity = new Intent(this, JournalPage3.class);
        startActivity(startNewActivity);
    }

    // declare variable
    public static final String STORE_PAGE_2 = "page2";
    public static final String Page_2_info = "page2info";


    // save page to shared preferences
    public void save_page(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_2,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Page_2_info+Login, page2EditText.getText().toString());
        edit.apply();
    }

    // load page from shared preferences
    public void load_page(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_2, MODE_PRIVATE);
        page2EditText.setText(sp.getString(Page_2_info+Login,"").toString());
    }

    // declare variable
    public static final String STORE_PAGE_2_Title = "page_2_Title";
    public static final String Page_2_Title_info = "page_2_Title_info";

    // save title to shared preferences
    public void save_title(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_2_Title,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Page_2_Title_info+Login, titleEditText.getText().toString());
        edit.apply();
    }

    // load title from shared preferences
    public void load_title(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_2_Title, MODE_PRIVATE);
        titleEditText.setText(sp.getString(Page_2_Title_info+Login,"").toString());
    }

}