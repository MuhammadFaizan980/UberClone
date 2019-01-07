package com.whatsclone.muhammadfaizan.uberclone.UserComponents.LoginRegister

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginPresenter.IPresenterLoginRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginPresenter.PresenterLoginRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginView.ILoginViewRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterView.RegisterViewRider
import com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity.ActivityWelcome

class LoginViewRider : AppCompatActivity(), ILoginViewRider {

    private lateinit var container: ConstraintLayout
    private lateinit var txtSignUp: TextView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var progressbar: ProgressBar
    private lateinit var presenterLoginRider: IPresenterLoginRider
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view_rider)
        initViews()
        passListenerToPresenter()
        goToRegisterActivity()
    }

    private fun initViews() {
        txtSignUp = findViewById(R.id.txt_signin_rider)
        container = findViewById(R.id.login_container_rider)
        edtEmail = findViewById(R.id.edt_email_login_rider)
        edtPassword = findViewById(R.id.edt_password_login_rider)
        btnLogin = findViewById(R.id.btn_login_rider)
        progressbar = findViewById(R.id.progress_login_rider)
        presenterLoginRider = PresenterLoginRider(this@LoginViewRider)
    }

    private fun passListenerToPresenter() {
        btnLogin.setOnClickListener {
            btnLogin.isEnabled = false
            progressbar.visibility = View.VISIBLE
            email = edtEmail.text.toString()
            password = edtPassword.text.toString()
            presenterLoginRider.onLoginInitiated(email, password)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginViewRider, ActivityWelcome::class.java))
        this@LoginViewRider.finish()
    }

    @SuppressLint("ResourceAsColor")
    override fun onLoginResults(results: Boolean) {
        if (results) {
            presenterLoginRider.authenticateUser(email, password)
        } else {
            hideProgressBar()
            var snackBar: Snackbar = Snackbar.make(container, "Enter a valid email address and password", Snackbar.LENGTH_SHORT)
            var mView: View = snackBar.view
            mView.setBackgroundColor(ContextCompat.getColor(this@LoginViewRider, R.color.red))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackBar.show()
        }
    }

    override fun onFirebaseResults(results: Boolean, exc: Exception?) {
        if (results) {
            hideProgressBar()
            var snackBar: Snackbar = Snackbar.make(container, "Log in success", Snackbar.LENGTH_SHORT)
            var mView: View = snackBar.view
            mView.setBackgroundColor(ContextCompat.getColor(this@LoginViewRider, R.color.blue))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackBar.show()
        } else {
            hideProgressBar()
            var snackBar: Snackbar = Snackbar.make(container, "Log in error", Snackbar.LENGTH_LONG)
            snackBar.setAction("DETAILS") {
                var dialog: AlertDialog.Builder = AlertDialog.Builder(this@LoginViewRider, R.style.mDialog)
                dialog.setTitle("Login Error")
                dialog.setCancelable(false)
                dialog.setMessage(exc!!.message!!.toString())
                dialog.setPositiveButton("Close") { alertDialog, which ->
                    alertDialog.cancel()
                }
                dialog.show()
            }
            var mView: View = snackBar.view
            mView.setBackgroundColor(ContextCompat.getColor(this@LoginViewRider, R.color.red))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackBar.show()
        }
    }

    override fun onStart() {
        super.onStart()
        progressbar.visibility = View.INVISIBLE
    }

    private fun hideProgressBar() {
        this.progressbar.visibility = View.INVISIBLE
        btnLogin.isEnabled = true
    }

    private fun goToRegisterActivity() {
        txtSignUp.setOnClickListener {
            startActivity(Intent(this@LoginViewRider, RegisterViewRider::class.java))
            this@LoginViewRider.finish()
        }
    }
}
