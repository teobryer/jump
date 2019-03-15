package com.example.i173508.jump;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class Menu extends AppCompatActivity {
    Button info;
    Button play;
    int bestScore;
    TextView bestScoreAff;
    TextView text;
    TextView text2;
    ConstraintLayout constraintLayout;
    TextView nbParties;

    static int width;
    static int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(1000);
        setContentView(R.layout.activity_menu);
        constraintLayout = findViewById(R.id.layout);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/police.ttf");

        Log.e("width1", String.valueOf(width));
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        nbParties= findViewById(R.id.nbParties);
        nbParties.setText(String.valueOf(prefs.getInt("nbParties",0)) );
        Log.e("test",String.valueOf(prefs.getInt("nbParties",0)));
        nbParties.setTypeface(font);
        bestScoreAff = findViewById(R.id.bestScore1);
        text = findViewById(R.id.textView);
        text2 =findViewById(R.id.textView2);
        text2.setTypeface(font);
        bestScore = prefs.getInt("bestScore", 0); //0 is the default value
        text.setTypeface(font);
        bestScoreAff.setTypeface(font);
        bestScoreAff.setText(String.valueOf(bestScore));
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AProposActivity.class);

                startActivity(intent);
            }
        });
        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);

                startActivity(intent);
            }
        });
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = constraintLayout.getHeight();
        height= constraintLayout.getWidth();
    }
    @Override
    public void onBackPressed() {
        finish();


    }
}
