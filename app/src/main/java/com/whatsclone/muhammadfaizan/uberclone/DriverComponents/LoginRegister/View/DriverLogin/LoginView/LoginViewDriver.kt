package com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.LoginView

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.Contracts.Contract
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity.ActivityWelcome

class LoginViewDriver : AppCompatActivity(), Contract.ILoginViewDriver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view_driver)
    }

    override fun onLoginResults(result: Boolean) {
        if (result) {
            Toast.makeText(this@LoginViewDriver, "Valid credentials", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@LoginViewDriver, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginViewDriver, ActivityWelcome::class.java))
        this@LoginViewDriver.finish()
    }
}
