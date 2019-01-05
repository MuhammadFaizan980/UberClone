package com.whatsclone.muhammadfaizan.uberclone.UserComponents.LoginRegister

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Presenter.IPresenterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Presenter.PresenterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.View.ILoginViewRider
import com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity.ActivityWelcome

class LoginViewRider : AppCompatActivity(), ILoginViewRider {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var presenterRider: IPresenterRider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view_rider)
        initViews()
        passListenerToPresenter()
    }

    private fun initViews(){
        edtEmail = findViewById(R.id.edt_email_login_rider)
        edtPassword  = findViewById(R.id.edt_password_login_rider)
        btnLogin = findViewById(R.id.btn_login_rider)
        presenterRider = PresenterRider(this@LoginViewRider)
    }

    private fun passListenerToPresenter(){
        var email : String = ""
        var password : String = ""
        btnLogin.setOnClickListener {
            email = edtEmail.text.toString()
            password = edtPassword.text.toString()
            presenterRider.onLoginInitiated(email, password)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginViewRider, ActivityWelcome::class.java))
        this@LoginViewRider.finish()
    }

    override fun onLoginResults(results: Boolean) {

    }
}
