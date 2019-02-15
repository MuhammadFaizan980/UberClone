package com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.LoginView

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.Contracts.Contract
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.LoginPresenter.LoginPresenterDriver
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.MessageClass
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity.ActivityWelcome

class LoginViewDriver : AppCompatActivity(), Contract.ILoginViewDriver {
    private lateinit var btnLogin: Button
    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var presenter: Contract.ILoginPresenterDriver
    private lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view_driver)
        initViews()
    }

    private fun initViews() {
        btnLogin = findViewById(R.id.btn_login_driver)
        edtEmail = findViewById(R.id.edt_login_email_driver)
        edtPass = findViewById(R.id.edt_login_password_driver)
        layout = findViewById(R.id.main_layout_driver_login)
        presenter = LoginPresenterDriver(this@LoginViewDriver, this@LoginViewDriver)

        btnLogin.setOnClickListener {
            presenter.onLoginInitiated(edtEmail.text.toString(), edtPass.text.toString())
        }
    }

    override fun onLoginResults(result: Boolean) {
        if (result) {
            presenter.loginUserToFirebase(edtEmail.text.toString(), edtPass.text.toString())
        } else {
            Toast.makeText(this@LoginViewDriver, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFirebaseResults(exc: Exception?) = if (exc != null) {
        MessageClass.showMessage(layout, this@LoginViewDriver, exc!!.message.toString(), true)
    } else {
        MessageClass.showMessage(layout, this@LoginViewDriver, exc!!.message.toString(), false)
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginViewDriver, ActivityWelcome::class.java))
        this@LoginViewDriver.finish()
    }
}
