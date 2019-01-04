package com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Button
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.LoginViewDriver
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.UserComponents.LoginRegister.LoginViewRider

class ActivityWelcome : AppCompatActivity() {

    private lateinit var btnDriverLogin: Button
    private lateinit var btnRiderLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)
        initViews()
        setListeners()
    }

    private fun initViews() {
        btnDriverLogin = findViewById(R.id.btnLoginDriver)
        btnRiderLogin = findViewById(R.id.btnLoginRider)
    }

    private fun setListeners() {
        btnRiderLogin.setOnClickListener {
            startActivity(Intent(this@ActivityWelcome, LoginViewRider::class.java))
            this@ActivityWelcome.finish()
        }

        btnDriverLogin.setOnClickListener {
            startActivity(Intent(this@ActivityWelcome, LoginViewDriver::class.java))
            this@ActivityWelcome.finish()
        }
    }
}
