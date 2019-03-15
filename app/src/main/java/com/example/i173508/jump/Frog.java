package com.example.i173508.jump;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Frog {
    Context context;
    ImageView imageFrog;
    Curseur curseur;
    Partie partie;

    public Frog(ImageView frogImage, Curseur curseur, Partie partie, Context context) {
        this.context = context;
        this.imageFrog = frogImage;
        this.curseur = curseur;
        this.partie = partie;

    }


    public void jump(final float distance) {
        imageFrog.setImageResource(R.drawable.jump1);
        Animation animation = new TranslateAnimation(0, distance / 3, 0, -100);
        animation.setDuration(100);
        animation.setFillAfter(true);
        imageFrog.startAnimation(animation);


        imageFrog.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageFrog.setImageResource(R.drawable.jump2);
                Animation animation2 = new TranslateAnimation(distance / 3, 2 * (distance / 3), -100, -200);
                animation2.setDuration(100);
                animation2.setFillAfter(true);
                imageFrog.startAnimation(animation2);


                imageFrog.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Animation animation3 = new TranslateAnimation(2 * (distance / 3), 2 * (distance / 3) + 20, -200, -200);
                        animation3.setDuration(20);
                        animation3.setFillAfter(true);
                        imageFrog.startAnimation(animation3);

                        imageFrog.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imageFrog.setImageResource(R.drawable.jump3);
                                Animation animation3 = new TranslateAnimation(2 * (distance / 3) + 20, distance, -200, 0);
                                animation3.setDuration(100);
                                animation3.setFillAfter(true);
                                imageFrog.startAnimation(animation3);
                                imageFrog.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        int result = curseur.progressBar.getProgress() - Nenuphar.placeNenu;
                                        Log.e("result", String.valueOf(result));
                                        if (result >= -13 && result <= +13) {
                                            imageFrog.setImageResource(R.drawable.frog);

                                            Log.d("yes ", "win=1");
                                            partie.score = partie.score + 1;
                                            partie.majScore();


                                            imageFrog.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    partie.redemarre();
                                                }
                                            }, 100);
                                        } else {
                                            imageFrog.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    imageFrog.setImageResource(R.drawable.eau);
                                                    final Animation animation4 = new TranslateAnimation(distance - 20, distance - 20, +80, -30);
                                                    animation4.setDuration(100);
                                                    animation4.setFillAfter(true);
                                                    imageFrog.startAnimation(animation4);
                                                    partie.score = 0;
                                                    partie.majScore();


                                                    imageFrog.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Log.e("test", "tirghj");
                                                            imageFrog.setImageResource(0);
                                                            imageFrog.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    partie.defaite();
                                                                }
                                                            }, 100);

                                                        }
                                                    }, 70);


                                                }
                                            }, 200);
                                        }
                                    }
                                }, 100);

                            }
                        }, 100);
                    }
                }, 100);


            }
        }, 100);


    }
}
