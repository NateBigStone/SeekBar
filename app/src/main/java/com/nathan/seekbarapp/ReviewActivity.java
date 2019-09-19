package com.nathan.seekbarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReviewActivity extends AppCompatActivity {

    Button mYesButton;
    Button mNoButton;

    public static final String EXTRA_ENJOY_APP = "com.nathan.seekbarapp.enjoy_app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        mYesButton = findViewById(R.id.yesButton);
        mNoButton = findViewById(R.id.noButton);

        mYesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();

                returnIntent.putExtra(EXTRA_ENJOY_APP, true);

                setResult(RESULT_OK, returnIntent);

                finish();
            }
        });

        mNoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();

                returnIntent.putExtra(EXTRA_ENJOY_APP, false);

                setResult(RESULT_OK, returnIntent);

                finish();
            }
        });




    }
}
