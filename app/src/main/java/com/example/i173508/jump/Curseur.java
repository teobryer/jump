package com.example.i173508.jump;

import android.content.Context;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class Curseur {

    ProgressBar progressBar;
    Context context;
    public boolean translateRight;
    Timer timer;

    public Curseur(ProgressBar progressBar, Context context) {
        this.progressBar = progressBar;
        this.context = context;
        translateRight = true;
        timer = new Timer();
    }

    public void bougerCurseur() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (translateRight) {

                    progressBar.setProgress(progressBar.getProgress() + 2);

                    if (progressBar.getProgress() == progressBar.getMax()) {
                        translateRight = false;
                    }
                } else {

                    progressBar.setProgress(progressBar.getProgress() - 2);
                    if (progressBar.getProgress() == 0) {
                        translateRight = true;
                    }
                }


            }

        };

        timer.schedule(timerTask, 1000 * 1, 1 * 5);
    }

    public float calculeDistance() {
        return ((int)(60/595.0*Menu.width +progressBar.getProgress()*Menu.width*425/595.0/420));
    }

    public void stop() {
        timer.cancel();
    }
}
