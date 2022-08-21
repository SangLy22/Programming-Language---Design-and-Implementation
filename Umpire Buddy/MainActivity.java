package com.example.umpire_buddy_lab_1_sang_ly;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ballBtn = (Button) findViewById(R.id.ballBtn);
        ballBtn.setOnClickListener(new View.OnClickListener() {
            private int ball_Count = 0;
            @Override
            public void onClick(View v) {
                TextView ballCountTextView =  (TextView) findViewById(R.id.ballCountTextView);
                ball_Count = ball_Count + 1;
                //ballCountTextView.setText(ball_Count+"");

                if(ball_Count == 4){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Walk");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ball_Count = 0;
                        }
                    });
                    builder.show();
                }
                else {
                    ballCountTextView.setText(ball_Count + "");
                }
            }
        });

        Button strikeBtn = (Button) findViewById(R.id.strikeBtn);
        strikeBtn.setOnClickListener(new View.OnClickListener() {
            private int strike_Count = 0;
            @Override
            public void onClick(View v) {
                TextView strikeCountTextView = findViewById(R.id.strikeCountTextView);
                strike_Count = strike_Count + 1;
                //strikeCountTextView.setText(strike_Count + "");

                if (strike_Count == 3) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Out");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            strike_Count = 0;
                        }
                    });
                    builder.show();
                }
                else {
                    strikeCountTextView.setText(strike_Count + "");
                }
            }
        });
    }
}
