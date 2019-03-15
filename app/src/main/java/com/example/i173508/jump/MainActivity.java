package com.example.i173508.jump;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    GestureDetectorCompat gestureDetector;

    ProgressBar progressBar;

    ImageView frog;
    ImageView nenupharMobile;
    ImageView nenupharFixe;
    ImageView eau;
    ConstraintLayout constraintLayout;

    TextView scoreAff;
    TextView bestScoreAff;
    TextView text;

    Frog maGrenouille;
    Nenuphar monNenuphareMobile;
    Curseur monCurseur;
    Partie partie;
    int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint);

        nenupharFixe = findViewById(R.id.nenupharFixe);
        eau = findViewById(R.id.eau);


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/police.ttf");

        scoreAff = findViewById(R.id.score);
        bestScoreAff = findViewById(R.id.bestScore);
        text = findViewById(R.id.textView3);

        scoreAff.setTypeface(font);
        bestScoreAff.setTypeface(font);
        text.setTypeface(font);
        partie = new Partie(text, scoreAff, bestScoreAff, this, this);


        progressBar = findViewById(R.id.progressBar2);


        progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        monCurseur = new Curseur(progressBar, this);


        frog = findViewById(R.id.frog);
        maGrenouille = new Frog(frog, monCurseur, partie, this);


        nenupharMobile = findViewById(R.id.nenuphar);
        ///ConstraintLayout.LayoutParams paramsNenMobile = (ConstraintLayout.LayoutParams) nenupharMobile.getLayoutParams();
        /// paramsNenMobile.setMarginStart((int)(18/595*constraintLayout.getWidth()));
        //nenupharMobile.setLayoutParams(params);
        monNenuphareMobile = new Nenuphar(maGrenouille, nenupharMobile, monCurseur, this);


        gestureDetector = new GestureDetectorCompat(this, this);


        monNenuphareMobile.placerNenuphar();

        width = Menu.width;
        double marginBar = 112/595.0*width;
        double widthBar = 425.0/595.0*width;
        double marginNenuFixe = 18.0/595.0*width;
        double marginFrog = 12.0/595.0*width;
        Log.e("width", String.valueOf(constraintLayout.getWidth()));

        Log.e("width", String.valueOf(width));

        ConstraintLayout.LayoutParams paramsProgressBar = (ConstraintLayout.LayoutParams) progressBar.getLayoutParams();
        paramsProgressBar.setMarginStart((int) marginBar);
        paramsProgressBar.setMargins(paramsProgressBar.leftMargin,paramsProgressBar.topMargin,paramsProgressBar.rightMargin,(int)(16/410.0*Menu.height));
        progressBar.getLayoutParams().width = (int) widthBar;
        progressBar.setLayoutParams(paramsProgressBar);

        ConstraintLayout.LayoutParams paramsNenuFixe = (ConstraintLayout.LayoutParams) nenupharFixe.getLayoutParams();
        paramsNenuFixe.setMarginStart((int)(marginNenuFixe));
        paramsNenuFixe.setMargins(paramsNenuFixe.leftMargin,paramsNenuFixe.topMargin,paramsNenuFixe.rightMargin,(int)(8/410.0*Menu.height));
        nenupharFixe.setLayoutParams(paramsNenuFixe);
        nenupharFixe.getLayoutParams().height= (int)(39/410.0*Menu.height);
        nenupharFixe.getLayoutParams().width=(int)(63/595.0*Menu.width);


        ConstraintLayout.LayoutParams paramsEau = (ConstraintLayout.LayoutParams) eau.getLayoutParams();
        paramsEau.setMargins(paramsProgressBar.leftMargin,paramsProgressBar.topMargin,paramsProgressBar.rightMargin,(int)(8/410.0*Menu.height));
        eau.getLayoutParams().height= (int)(19/410.0*Menu.height);
        paramsEau.setMarginStart(0);
        eau.setLayoutParams(paramsEau);


        ConstraintLayout.LayoutParams paramsNenuMobile = (ConstraintLayout.LayoutParams) nenupharMobile.getLayoutParams();
       paramsNenuMobile.setMargins(paramsNenuMobile.leftMargin,paramsNenuMobile.topMargin,paramsNenuMobile.rightMargin,(int)(8/410.0*Menu.height));
        nenupharMobile.setLayoutParams(paramsNenuMobile);
        nenupharMobile.getLayoutParams().height= (int)(39/410.0*Menu.height);
        nenupharMobile.getLayoutParams().width=(int)(63/595.0*Menu.width);


        frog.getLayoutParams().height = (int)(85/410.0*Menu.height);
        frog.getLayoutParams().width = (int)(138/595.0*Menu.width);
        ConstraintLayout.LayoutParams paramsFrog = (ConstraintLayout.LayoutParams) frog.getLayoutParams();
        paramsFrog.setMarginStart((int)(marginFrog));
        paramsFrog.setMargins(paramsFrog.leftMargin,paramsFrog.topMargin,paramsFrog.rightMargin,(int)(34/410.0*Menu.height));
        frog.setLayoutParams(paramsFrog);



    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Menu.width = constraintLayout.getWidth();


        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        partie.quitter();


    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {


        monCurseur.stop();
        maGrenouille.jump(monCurseur.calculeDistance());

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}


