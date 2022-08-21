package com.example.journal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import static com.example.journal.MainActivity.Login;

public class JournalPage1 extends AppCompatActivity {

    // declare variable
    TextInputLayout page1InputText;
    TextInputEditText page1EditText;
    EditText titleEditText;

    // assign variables to their respective id
    // load both page and title
    // jump to journal page when back button get click
    // jump to page 2 when next button click
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_1);

        setTitle("Page 1");

        page1InputText = (TextInputLayout) findViewById(R.id.page1TextInput);
        page1EditText = (TextInputEditText) findViewById(R.id.page1EditText);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        load_page();
        load_title();

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                return_To_Main();
            }
        });

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                next_Go_Page_2();
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
                page1EditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
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

    // display the log in page
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
        AlertDialog.Builder builder = new AlertDialog.Builder(JournalPage1.this);
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

    // display journal page
    public void return_To_Main(){
        Intent startNewActivity = new Intent(this, Journal.class);
        startActivity(startNewActivity);
    }

    // display page 2
    public void next_Go_Page_2() {
        Intent startNewActivity = new Intent(this, JournalPage2.class);
        startActivity(startNewActivity);
    }

    // declare variable
    public static final String STORE_PAGE_1 = "page1";
    public static final String Page_1_info = "page1info";

    // save page to shared preferences
    public void save_page(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_1,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Page_1_info+Login, page1EditText.getText().toString());
        edit.apply();

    }

    // load page from shared preference
    public void load_page(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_1, MODE_PRIVATE);
        page1EditText.setText(sp.getString(Page_1_info+Login,"").toString());
    }

    // declare variable
    public static final String STORE_PAGE_Title = "page_1_Title";
    public static final String Page_Title_info = "page_1_Title_info";

    // save title to shared preference
    public void save_title(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_Title,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Page_Title_info+Login, titleEditText.getText().toString());
        edit.apply();
    }

    // load title from shared preference
    public void load_title(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_Title, MODE_PRIVATE);
        titleEditText.setText(sp.getString(Page_Title_info+Login,"").toString());
    }
}
