package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginPresenter

import com.google.firebase.auth.FirebaseAuth
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginModel.ILoginModelRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginModel.LoginModelRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginView.ILoginViewRider

class PresenterLoginRider(view: ILoginViewRider) : IPresenterLoginRider {

    private var loginViewRider: ILoginViewRider = view
    private lateinit var loginModelRider: ILoginModelRider

    override fun onLoginInitiated(email: String, password: String) {
        loginModelRider = LoginModelRider(email, password)
        loginViewRider.onLoginResults(loginModelRider.validateCredentials())
    }

    override fun authenticateUser(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                loginViewRider.onFirebaseResults(true, null)
            } else {
                loginViewRider.onFirebaseResults(false, task.exception!!)
            }
        }
    }
}