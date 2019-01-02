package com.whatsclone.muhammadfaizan.uberclone.SplashSrceen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.whatsclone.muhammadfaizan.uberclone.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen)
    }
}
