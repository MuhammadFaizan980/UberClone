package com.whatsclone.muhammadfaizan.uberclone.UserComponents.LoginRegister

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Presenter.IPresenterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Presenter.PresenterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.View.ILoginViewRider
import com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity.ActivityWelcome

class LoginViewRider : AppCompatActivity(), ILoginViewRider {

    private lateinit var container: ConstraintLayout
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var presenterRider: IPresenterRider
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view_rider)
        initViews()
        passListenerToPresenter()
    }

    private fun initViews() {
        container = findViewById(R.id.login_container_rider)
        edtEmail = findViewById(R.id.edt_email_login_rider)
        edtPassword = findViewById(R.id.edt_password_login_rider)
        btnLogin = findViewById(R.id.btn_login_rider)
        presenterRider = PresenterRider(this@LoginViewRider)
    }

    private fun passListenerToPresenter() {
        btnLogin.setOnClickListener {
            btnLogin.isEnabled = false
            email = edtEmail.text.toString()
            password = edtPassword.text.toString()
            presenterRider.onLoginInitiated(email, password)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginViewRider, ActivityWelcome::class.java))
        this@LoginViewRider.finish()
    }

    @SuppressLint("ResourceAsColor")
    override fun onLoginResults(results: Boolean) {
        if (results) {
            presenterRider.authenticateUser(email, password)
        } else {
            var snackBar: Snackbar = Snackbar.make(container, "Enter a valid email address and password", Snackbar.LENGTH_SHORT)
            var mView: View = snackBar.view
            mView.setBackgroundColor(ContextCompat.getColor(this@LoginViewRider, R.color.red))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackBar.show()
            btnLogin.isEnabled = true
        }
    }

    override fun onFirebaseResults(results: Boolean, exc: Exception?) {
        if (results) {
            btnLogin.isEnabled = true
            var snackBar: Snackbar = Snackbar.make(container, "Log in success", Snackbar.LENGTH_SHORT)
            var mView: View = snackBar.view
            mView.setBackgroundColor(ContextCompat.getColor(this@LoginViewRider, R.color.blue))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackBar.show()
        } else {
            btnLogin.isEnabled = true
            var snackBar: Snackbar = Snackbar.make(container, "Log in error", Snackbar.LENGTH_LONG)
            snackBar.setAction("DETAILS", View.OnClickListener {
                //TODO: show error dialog with details
            })
            var mView: View = snackBar.view
            mView.setBackgroundColor(ContextCompat.getColor(this@LoginViewRider, R.color.red))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackBar.show()
        }
    }
}
