package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterView

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.UserComponents.LoginRegister.LoginViewRider

class RegisterViewRider : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_view_rider)
    }

    override fun onBackPressed() {
        startActivity(Intent(this@RegisterViewRider, LoginViewRider::class.java))
        this@RegisterViewRider.finish()
    }
}
