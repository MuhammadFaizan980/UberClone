package com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.LoginPresenter

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.Contracts.Contract
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.LoginModel.LoginModelDriver
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.LoginView.LoginViewDriver

class LoginPresenterDriver(context: Context, view: LoginViewDriver) : Contract.ILoginPresenterDriver {
    private var context: Context = context
    private var view: Contract.ILoginViewDriver = view
    private lateinit var model: Contract.ILoginModelDriver

    override fun onLoginInitiated(email: String, pass: String) {
        model = LoginModelDriver(email, pass)
        view.onLoginResults(model.validateCredentials())
    }

    override fun loginUserToFirebase(email: String, pass: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                view.onFirebaseResults(null)
            } else {
                view.onFirebaseResults(task.exception!!)
            }
        }
    }
}