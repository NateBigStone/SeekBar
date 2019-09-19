package com.nathan.seekbarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SquareActivity extends AppCompatActivity {

    ImageView mSquare;

    public static final String EXTRA_TAP_INSIDE_SQUARE = "com.nathan.seekbarapp.tap_inside_square";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);

        int squareSize = getIntent().getIntExtra(MainActivity.EXTRA_SQUARE_SIZE, 100);
        if (squareSize == 0){
            squareSize = 3;
        }

        mSquare = findViewById(R.id.square_shape);

        ViewGroup.LayoutParams params = mSquare.getLayoutParams();
        params.height = squareSize;
        params.width = squareSize;


        mSquare.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent returnIntent = new Intent();

                returnIntent.putExtra(EXTRA_TAP_INSIDE_SQUARE, true);

                setResult(RESULT_OK, returnIntent);

                finish();
            }

        });

        View mainView = findViewById(android.R.id.content);
        mainView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EXTRA_TAP_INSIDE_SQUARE, false);
                setResult(RESULT_OK, returnIntent);
                finish();
            }

        });
    }
}
