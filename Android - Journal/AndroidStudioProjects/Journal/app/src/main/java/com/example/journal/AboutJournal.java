package com.example.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// declare text view and button variable
// link the variable to the their respective id in journal.xml
// set both text view to about and title respectively on the toolbar (menu)
// open Journal when button (Main Menu) get clicked
public class AboutJournal extends AppCompatActivity {
    TextView aboutTextView;
    TextView titleTextView;
    Button mmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_journal);
        setTitle("About");
        String about = "Journal is the application that will replace notebooks in the future." +
                "Journal allow you to save the writing and import picture on the go";
        String title = "Journal by Sang Ly";

        aboutTextView = (TextView) findViewById(R.id.aboutTextView);
        titleTextView = (TextView) findViewById(R.id.titleTextView);

        aboutTextView.setText(about);
        titleTextView.setText(title);

        mmButton = (Button) findViewById(R.id.mmButton);
        mmButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                go_Main_Menu();
            }
        });
    }

    public void go_Main_Menu(){
        Intent startNewActivity = new Intent(this, Journal.class);
        startActivity(startNewActivity);
    }
}
