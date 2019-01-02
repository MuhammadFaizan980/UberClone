package com.whatsclone.muhammadfaizan.uberclone.SplashSrceen;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.whatsclone.muhammadfaizan.uberclone.LoginRegister.LoginActivity;

public class BackgroundTask extends AsyncTask<Void, Integer, Integer> {

    private ProgressBar progressBar;
    private Context context;

    public BackgroundTask(ProgressBar progressBar, Context context) {
        this.progressBar = progressBar;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        for (int i = 1; i <= 100; i++) {
            try {
                wait(100);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        context.startActivity(new Intent(context.getApplicationContext(), LoginActivity.class));

    }
}
