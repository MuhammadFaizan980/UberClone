package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterPresenter

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterModel.IModelRegisterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterModel.ModelRegisterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterView.IRegisterViewRider

class PresenterRegisterRider(registerViewRider: IRegisterViewRider) : IPresenterRegisterRider {
    private var registerViewRider = registerViewRider
    lateinit var modelRegisterRider: IModelRegisterRider
    private var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Uber").child("Users")
    private var authRef: FirebaseAuth = FirebaseAuth.getInstance()

    override fun initValidation(email: String, password: String) {
        modelRegisterRider = ModelRegisterRider(email, password)
        if (modelRegisterRider.validateCredentials()) {
            registerViewRider.onValidationResults(true)
        } else {
            registerViewRider.onValidationResults(false)
        }
    }

    override fun initRegistration(email: String, password: String) {
        authRef.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                var map = HashMap<String, String>()
                map["email"] = email
                map["password"] = password

                dbRef.child(authRef.uid!!).setValue(map).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        registerViewRider.onFirebaseResults(null)
                    } else {
                        registerViewRider.onFirebaseResults(task.exception!!)
                    }
                }
            } else {
                registerViewRider.onFirebaseResults(task.exception!!)
            }
        }
    }

}