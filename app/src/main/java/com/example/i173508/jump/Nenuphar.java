package com.example.i173508.jump;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.ImageView;

public class Nenuphar {
    ImageView nenupharMobile;
    Frog frog;
    static int placeNenu;
    Context context;
    Curseur curseur;

    public Nenuphar(Frog frog, ImageView nenupharMobile, Curseur curseur, Context context) {
        this.nenupharMobile = nenupharMobile;
        this.frog = frog;
        this.context = context;
        this.curseur = curseur;
    }


    public void placerNenuphar() {

        frog.imageFrog.setImageResource(R.drawable.frog);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) nenupharMobile.getLayoutParams();
        placeNenu = (int) ((Math.random() * ((420) - 30)) + 30);
        Log.e("nenu", Integer.toString(placeNenu));
        Log.e("ttt", String.valueOf(curseur.progressBar.getWidth()));


        params.setMarginStart((int) (placeNenu*Menu.width*425/595.0/420));

        nenupharMobile.setLayoutParams(params);
        curseur.bougerCurseur();
    }
}
