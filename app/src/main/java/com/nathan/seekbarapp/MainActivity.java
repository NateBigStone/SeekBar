package com.nathan.seekbarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar mSeekBar;
    TextView mSeekBarValueLabel;
    Button mShowSquareButton;
    Button mReviewButton;

    public static final String EXTRA_SQUARE_SIZE = "com.nathan.seekbarapp.square_size";

    private static final int SQUARE_REQUEST_CODE = 0;
    private static final int REVIEW_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = findViewById(R.id.seekBar);
        mSeekBarValueLabel = findViewById(R.id.seekBarValueLabel);
        mShowSquareButton = findViewById(R.id.show_square_button);
        mReviewButton = findViewById(R.id.review_button);

        setProgressMessage();

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setProgressMessage();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        mShowSquareButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent showSquareIntent = new Intent(MainActivity.this, SquareActivity.class);
                showSquareIntent.putExtra(EXTRA_SQUARE_SIZE, mSeekBar.getProgress());
                startActivityForResult(showSquareIntent, SQUARE_REQUEST_CODE);
            }
        });

        mReviewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent reviewIntent = new Intent(MainActivity.this, ReviewActivity.class);
                startActivityForResult(reviewIntent,REVIEW_REQUEST_CODE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SQUARE_REQUEST_CODE && resultCode == RESULT_OK){
            boolean didTapSquare = data.getBooleanExtra(SquareActivity.EXTRA_TAP_INSIDE_SQUARE, false);

            if(didTapSquare) {
                Toast.makeText(this, "You tapped the square!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Sorry, you missed the square", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == REVIEW_REQUEST_CODE && resultCode == RESULT_OK){
            boolean didEnjoyApp = data.getBooleanExtra(ReviewActivity.EXTRA_ENJOY_APP, false);

            if(didEnjoyApp) {
                Toast.makeText(this, "Thank You!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, ":(", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == SQUARE_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You pressed the back button", Toast.LENGTH_LONG).show();
        }
    }

    private void setProgressMessage() {
        int seekbarProgress = mSeekBar.getProgress();
        String progressMessage = getString(R.string.seekbar_progress_message, seekbarProgress);
        mSeekBarValueLabel.setText(progressMessage);
    }
}
