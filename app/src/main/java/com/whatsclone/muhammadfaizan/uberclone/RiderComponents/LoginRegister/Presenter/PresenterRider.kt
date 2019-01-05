package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Presenter

import com.google.firebase.auth.FirebaseAuth
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Model.IModelRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Model.ModelRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.View.ILoginViewRider

class PresenterRider(view: ILoginViewRider) : IPresenterRider {

    private var loginViewRider: ILoginViewRider = view
    private lateinit var modelRider: IModelRider

    override fun onLoginInitiated(email: String, password: String) {
        modelRider = ModelRider(email, password)
        loginViewRider.onLoginResults(modelRider.validateCredentials())
    }

    override fun authenticateUser(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                loginViewRider.onFirebaseResults(true)
            } else {
                loginViewRider.onFirebaseResults(false)
            }
        }
    }
}