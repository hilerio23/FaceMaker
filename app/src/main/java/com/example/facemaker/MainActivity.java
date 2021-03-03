//@Anabel Hilerio
package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Face faceView = findViewById(R.id.faceView);
        Spinner hairSpinner = (Spinner) findViewById(R.id.spinner);
        Button random  = (Button)findViewById(R.id.button2);
        SeekBar red = (SeekBar)findViewById(R.id.red);
        SeekBar blue = (SeekBar)findViewById(R.id.blue);
        SeekBar green = (SeekBar)findViewById(R.id.green);
        RadioButton hair = (RadioButton)findViewById(R.id.radioButton);
        RadioButton eyes = (RadioButton)findViewById(R.id.radioButton2);
        RadioButton skin = (RadioButton)findViewById(R.id.radioButton3);

        hairSpinner.setOnItemSelectedListener(faceView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hair_styles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /**
         * External Citation
         *  Date: 2 March 2021
         *  Problem: I needed to figure out how to populate the the spinner
         *  and also how to get to drop dow
         *
         *  Resource:
         *      https://developer.android.com/guide/topics/ui/controls/spinner
         *  Solution: I used the example they gave as a guideline
         */
        hairSpinner.setAdapter(adapter);

        red.setOnSeekBarChangeListener(faceView);
        blue.setOnSeekBarChangeListener(faceView);
        green.setOnSeekBarChangeListener(faceView);
        random.setOnClickListener(faceView);
        hair.setOnCheckedChangeListener(faceView);
        eyes.setOnCheckedChangeListener(faceView);
        skin.setOnCheckedChangeListener(faceView);

    }
}