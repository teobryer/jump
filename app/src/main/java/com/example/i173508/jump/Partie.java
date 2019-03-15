package com.example.i173508.jump;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Partie {
    int score;
    int bestScore;
    TextView scoreAff;
    Context context;
    TextView bestScoreAff;
    TextView text;
    MainActivity activity;
    public final static String NB_PARTIES_TOTAL = "nbPartiesTotal";
    static int nbParties;


    public Partie(TextView text, TextView scoreAff, TextView bestScoreAff, Context context, MainActivity activity) {
        this.scoreAff = scoreAff;
        this.bestScoreAff = bestScoreAff;
        this.context = context;
        this.text = text;
        this.activity = activity;
        SharedPreferences prefs = context.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        score = prefs.getInt("score", 0); //0 is the default value
        bestScore = prefs.getInt("bestScore", 0); //0 is the default value
        nbParties = prefs.getInt("nbParties",0);
        Log.e("score", String.valueOf(score));
        scoreAff.setText(String.valueOf(score));
        bestScoreAff.setText(String.valueOf(bestScore));
    }


    public void majScore() {
        SharedPreferences prefs = context.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("score", score);
        editor.commit();
        if (score > bestScore) {
            editor.putInt("bestScore", score);
            editor.commit();
        }
    }

    public void defaite() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = activity.getLayoutInflater().inflate(R.layout.replay, null);
        Button replay = mView.findViewById(R.id.replay);
        SharedPreferences prefs = context.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("nbParties", nbParties+1);

        editor.commit();
        Log.e("test",String.valueOf(prefs.getInt("nbParties",0)));
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redemarre();
            }
        });
        Button menu = mView.findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getBaseContext(), Menu.class);

                context.startActivity(intent);
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    public void redemarre() {
        activity.recreate();
    }

    public void quitter() {
        Intent intent = new Intent(activity.getBaseContext(), Menu.class);
        SharedPreferences prefs = context.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("score", score);
        activity.finish();
        context.startActivity(intent);

    }
}
