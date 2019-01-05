package com.whatsclone.muhammadfaizan.uberclone.UserComponents.LoginRegister

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.View.ILoginViewRider
import com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity.ActivityWelcome

class LoginViewRider : AppCompatActivity(), ILoginViewRider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view_rider)
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginViewRider, ActivityWelcome::class.java))
        this@LoginViewRider.finish()
    }

    override fun onLoginResults(results: Boolean) {

    }
}
