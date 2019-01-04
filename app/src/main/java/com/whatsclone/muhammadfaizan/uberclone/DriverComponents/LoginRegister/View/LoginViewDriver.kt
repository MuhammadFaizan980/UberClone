package com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity.ActivityWelcome

class LoginViewDriver : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view_driver)
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginViewDriver, ActivityWelcome::class.java))
        this@LoginViewDriver.finish()
    }
}
