package com.westjonathan.moblab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.westjonathan.moblab3.R;

public class MainActivity extends AppCompatActivity {
    // Declare android elements
    SeekBar seekBar;
    TextView q1;
    TextView q2;
    TextView q3;
    TextView q4;
    private Toast mainToast = null;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Shared Preferences
        sharedPreferences = getSharedPreferences("tv_clicks", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
////        Initialize variables
//        editor.putInt(""+R.id.q1_textview, sharedPreferences.getInt(""+R.id.q1_textview, 0));
//        editor.putInt(""+R.id.q1_textview, sharedPreferences.getInt(""+R.id.q1_textview, 0));
//        editor.putInt(""+R.id.q1_textview, sharedPreferences.getInt(""+R.id.q1_textview, 0));
//        editor.putInt(""+R.id.q1_textview, sharedPreferences.getInt(""+R.id.q1_textview, 0));
//        editor.apply();

        //SeekBar
        q1 = findViewById(R.id.q1_textview);
        q2 = findViewById(R.id.q2_textview);
        q3 = findViewById(R.id.q3_textview);
        q4 = findViewById(R.id.q4_textview);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                q1.setTextSize((float)((double)progress/100*30+4));
                q2.setTextSize((float)((double)progress/100*30+4));
                q3.setTextSize((float)((double)progress/100*30+4));
                q4.setTextSize((float)((double)progress/100*30+4));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });
    }

    public void tvPress(View view) {
        TextView tempTV = findViewById(view.getId());
//      Record number of clicks for view
        int tempClicks = sharedPreferences.getInt(""+view.getId(), 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(""+view.getId(), ++tempClicks);
        String toastText = "TextView in " + tempTV.getText() + " clicked " + tempClicks + " times.";
        editor.apply();

        if (mainToast != null)
            mainToast.cancel();//remove old toast if exists
        mainToast = Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT);
        mainToast.show();
    }
}