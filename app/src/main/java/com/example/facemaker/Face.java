//@Anabel Hilerio
package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.util.Random;

public class Face extends SurfaceView implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    Random skinNum = new Random();
    Random eyeNum = new Random();
    Random hairColorNum = new Random();
    Random styleNum = new Random();

    Paint skinTone = new Paint();
    Paint eyeTone = new Paint();
    Paint hairTone = new Paint();

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        randomize();

        setWillNotDraw(false);
    }

    protected void onDraw(Canvas canvas){
        drawFace(canvas);
        drawHair(canvas);
    }

    public void drawFace(Canvas canvas){
        skinTone.setColor(skinColor);
        canvas.drawCircle(500.0f, 500.0f, 200.0f, skinTone);

        eyeTone.setColor(eyeColor);
        canvas.drawCircle(430.0f, 450.0f, 25.0f, eyeTone);
        canvas.drawCircle(575.0f, 450.0f, 25.0f, eyeTone);
    }
    public void drawHair(Canvas canvas){
        hairTone.setColor(hairColor);
        if(hairStyle == 1){
            canvas.drawArc(330.0f,300.0f,670.0f,500.0f, 180, 180, true, hairTone);
        }
        if(hairStyle == 2){
            canvas.drawArc(330.0f,300.0f,670.0f,500.0f, 180, 180, true, hairTone);
            canvas.drawCircle(500, 250, 50, hairTone);
        }
        if(hairStyle == 3) {
            canvas.drawArc(330.0f, 300.0f, 670.0f, 500.0f, 180, 180, true, hairTone);
            canvas.drawCircle(400, 270, 47, hairTone);
            canvas.drawCircle(600, 270, 47, hairTone);
        }
    }

    protected void randomize(){
        skinColor = skinNum.nextInt();
        eyeColor = eyeNum.nextInt();
        hairColor = hairColorNum.nextInt();
        hairStyle = styleNum.nextInt(4);
    }

    /**
     * External Citation
     *  Date: 2 March 2021
     *  Problem: I needed a way to get random integers
     *
     *  Resource:
     *      https://developer.android.com/reference/java/util/Random#nextInt()
     *  Solution: I used method .nextInt()
     */

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        hairStyle = i;
        invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        RadioButton hair = ((MainActivity)getContext()).findViewById(R.id.radioButton);
        RadioButton eyes = ((MainActivity)getContext()).findViewById(R.id.radioButton2);
        RadioButton skin = ((MainActivity)getContext()).findViewById(R.id.radioButton3);
        /**
         * External Citation
         *  Date: 2 March 2021
         *  Problem: I needed a way to access the radio button and also the seekbars even though
         *  they aren't apart of the faceview in the activity_main.xml
         *
         *  Resource:
         *      https://medium.com/@banmarkovic/what-is-context-in-android-and-which-one-should-you-use-e1a8c6529652
         *  Solution: I used the getContext method that they suggested in this article
         */

        seekBar.setMax(255);
        seekBar.setProgress(i);
        if(seekBar.getId() == R.id.red){
            if(hair.isChecked()){
                hairColor = Color.rgb(i, Color.green(hairColor), Color.blue(hairColor));
            }
            if(eyes.isChecked()){
                eyeColor = Color.rgb(i, Color.green(eyeColor), Color.blue(eyeColor));
            }
            if(skin.isChecked()){
                skinColor = Color.rgb(i, Color.green(skinColor), Color.blue(skinColor));
            }
        }
        if(seekBar.getId() == R.id.blue){
            if(hair.isChecked()){
                hairColor = Color.rgb(Color.red(hairColor), Color.green(hairColor), i);
            }
            else if(eyes.isChecked()){
                eyeColor = Color.rgb(Color.red(eyeColor), Color.green(eyeColor), i);
            }
            else if(skin.isChecked()){
                skinColor = Color.rgb(Color.red(skinColor), Color.green(skinColor), i);
            }
        }
        if(seekBar.getId() == R.id.green){
            if(hair.isChecked()){
                hairColor = Color.rgb(Color.red(hairColor), i, Color.blue(hairColor));
            }
            else if(eyes.isChecked()){
                eyeColor = Color.rgb(Color.red(eyeColor), i, Color.blue(eyeColor));
            }
            else if(skin.isChecked()){
                skinColor = Color.rgb(Color.red(skinColor), i, Color.blue(skinColor));
            }
        }
        invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onClick(View view) {
        randomize();
        invalidate();
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        boolean checked = ((RadioButton) compoundButton).isChecked();
        SeekBar red = ((MainActivity)getContext()).findViewById(R.id.red);
        SeekBar blue = ((MainActivity)getContext()).findViewById(R.id.blue);
        SeekBar green = ((MainActivity)getContext()).findViewById(R.id.green);

        if(compoundButton.getId() == R.id.radioButton && checked){
            onProgressChanged(red, Color.red(hairColor), false);
            onProgressChanged(blue, Color.blue(hairColor), false);
            onProgressChanged(green, Color.green(hairColor), false);
        }
        if(compoundButton.getId() == R.id.radioButton2 && checked){
            onProgressChanged(red, Color.red(eyeColor), false);
            onProgressChanged(blue, Color.blue(eyeColor), false);
            onProgressChanged(green, Color.green(eyeColor), false);
        }
        if(compoundButton.getId() == R.id.radioButton3 && checked){
            onProgressChanged(red, Color.red(skinColor), false);
            onProgressChanged(blue, Color.blue(skinColor), false);
            onProgressChanged(green, Color.green(skinColor), false);
        }
    }
    /**
     * External Citation
     *  Date: 2 March 2021
     *  Problem: I needed a way to see if the radio button is checked and to see
     *  which radio button I am using by checking the id
     *
     *  Resource:
     *      https://developer.android.com/guide/topics/ui/controls/radiobutton
     *  Solution: I used followed the example that was given
     */
    /**
     * External Citation
     *  Date: 2 March 2021
     *  Problem: Way to get how much red, blue, or green there was in the variables
     *
     *  Resource:
     *      https://stackoverflow.com/questions/23323183/how-to-get-
     *      rgb-values-from-the-paint-object
     *  Solution: I followed the example that they gave
     */

}
