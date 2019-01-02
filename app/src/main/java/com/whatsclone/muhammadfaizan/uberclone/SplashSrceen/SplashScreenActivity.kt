package com.whatsclone.muhammadfaizan.uberclone.SplashSrceen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.ProgressBar
import com.whatsclone.muhammadfaizan.uberclone.R

class SplashScreenActivity : AppCompatActivity(), ActivityFinishInterface {

    lateinit var progressBar: ProgressBar
    lateinit var backgroundTask: BackgroundTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen)

        progressBar = findViewById(R.id.progressSplash)
        backgroundTask = BackgroundTask(progressBar, this@SplashScreenActivity, this@SplashScreenActivity)
        backgroundTask.execute()
    }

    override fun endSplashScreen() {
        this@SplashScreenActivity.finish()
    }
}
