package com.whatsclone.muhammadfaizan.uberclone.SplashSrceen

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.ProgressBar

import com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity.ActivityWelcome

class BackgroundTask constructor(progressBar: ProgressBar, context: Context, splashScreen: ActivityFinishInterface) : AsyncTask<Void, Int, Int>() {

    private var progressBar: ProgressBar = progressBar
    private var context: Context = context
    private var activityFinisher: ActivityFinishInterface = splashScreen

    override fun doInBackground(vararg voids: Void): Int? {
        for (i in 1..100) {
            try {
                publishProgress(i)
                Thread.sleep(10)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressBar.progress = values[0]!!
    }

    override fun onPostExecute(integer: Int?) {
        context.startActivity(Intent(context.applicationContext, ActivityWelcome::class.java))
        activityFinisher.endSplashScreen()
    }
}
