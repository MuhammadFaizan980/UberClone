package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterView

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
import android.widget.ProgressBar
import android.widget.TextView
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterPresenter.IPresenterRegisterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterPresenter.PresenterRegisterRider
import com.whatsclone.muhammadfaizan.uberclone.UserComponents.LoginRegister.LoginViewRider

class RegisterViewRider : AppCompatActivity(), IRegisterViewRider {

    private lateinit var mainLayout: ConstraintLayout
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var txtLogin: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: IPresenterRegisterRider
    private lateinit var email: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_view_rider)
        initViews()
        passListenersToPresenter()
    }

    private fun initViews() {
        mainLayout = findViewById(R.id.register_container_rider)
        edtEmail = findViewById(R.id.edt_email_register_rider)
        edtPassword = findViewById(R.id.edt_password_register_rider)
        btnRegister = findViewById(R.id.btn_register_rider)
        txtLogin = findViewById(R.id.txt_register_rider)
        progressBar = findViewById(R.id.progress_register_rider)
        presenter = PresenterRegisterRider(this@RegisterViewRider)
    }

    private fun passListenersToPresenter() {
        btnRegister.setOnClickListener {
            btnRegister.isEnabled = false
            email = edtEmail.text.toString()
            password = edtPassword.text.toString()
            presenter.initValidation(email, password)
        }
    }

    override fun onValidationResults(results: Boolean) {
        if (results) {
            presenter.initRegistration(email, password)
        } else {
            hideProgress()
            var snackbar: Snackbar = Snackbar.make(mainLayout, "Enter a valid email address and password", Snackbar.LENGTH_SHORT)
            var mView: View = snackbar.view
            mView.setBackgroundColor(ContextCompat.getColor(this@RegisterViewRider, R.color.red))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackbar.show()
        }
    }

    override fun onFirebaseResults(exc: Exception?) {
        //TODO: modify this function
    }

    override fun onBackPressed() {
        startActivity(Intent(this@RegisterViewRider, LoginViewRider::class.java))
        this@RegisterViewRider.finish()
    }

    private fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
        btnRegister.isEnabled = true
    }

    override fun onStart() {
        super.onStart()
        hideProgress()
    }
}
