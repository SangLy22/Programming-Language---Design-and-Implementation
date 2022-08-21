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

public class JournalPage4 extends AppCompatActivity {

    // declare variable
    TextInputLayout page4InputText;
    TextInputEditText page4EditText;
    EditText titleEditText;

    // assign variables to their respective id
    // load both page and title
    // call page 5 function when next button get click
    // call page 3 function when back button get click
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_4);

        setTitle("Page 4");

        page4InputText = (TextInputLayout) findViewById(R.id.page4TextInput);
        page4EditText = (TextInputEditText) findViewById(R.id.page4EditText);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        load_page();
        load_title();

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                next_Go_Page_5();
            }
        });
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                back_Go_page_3();
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
    // .logout call log out function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.save: {
                // hide keyboard
                titleEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                page4EditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
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

    // show log in page
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
        AlertDialog.Builder builder = new AlertDialog.Builder(JournalPage4.this);
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

    // display page 3
    public void   back_Go_page_3(){
        Intent startNewActivity = new Intent(this, JournalPage3.class);
        startActivity(startNewActivity);
    }

    // show page 5
    public void next_Go_Page_5() {
        Intent startNewActivity = new Intent(this, JournalPage5.class);
        startActivity(startNewActivity);
    }

    // declare variable
    public static final String STORE_PAGE_4 = "page4";
    public static final String Page_4_info = "page4info";

    // save page to shared preferences
    public void save_page(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_4,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Page_4_info+Login, page4EditText.getText().toString());
        edit.apply();

    }

    // load shared preferences
    public void load_page(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_4, MODE_PRIVATE);
        page4EditText.setText(sp.getString(Page_4_info+Login,"").toString());
    }

    // declare variable
    public static final String STORE_PAGE_4_Title = "page_4_Title";
    public static final String Page_4_Title_info = "page_4_Title_info";

    // save title to shared preferences
    public void save_title(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_4_Title,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Page_4_Title_info+Login, titleEditText.getText().toString());
        edit.apply();
    }

    // load title from shared preferences
    public void load_title(){
        SharedPreferences sp = getSharedPreferences(STORE_PAGE_4_Title, MODE_PRIVATE);
        titleEditText.setText(sp.getString(Page_4_Title_info+Login,"").toString());
    }
}
