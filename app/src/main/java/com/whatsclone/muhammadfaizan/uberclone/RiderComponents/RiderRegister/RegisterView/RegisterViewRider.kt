package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterView

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.widget.*
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
    }

    private fun passListenersToPresenter() {
        btnRegister.setOnClickListener {
            var presenter: IPresenterRegisterRider = PresenterRegisterRider(this@RegisterViewRider, this@RegisterViewRider)
            presenter.initRegistration(edtEmail.text.toString(), edtPassword.text.toString())
        }
    }

    override fun onRegistrationResults(results: String) {
        Toast.makeText(this@RegisterViewRider, results, Toast.LENGTH_SHORT).show()
    }

    override fun onFirebaseResults(exc: Exception?) {
        //TODO: modify this function
    }

    override fun onBackPressed() {
        startActivity(Intent(this@RegisterViewRider, LoginViewRider::class.java))
        this@RegisterViewRider.finish()
    }
}
