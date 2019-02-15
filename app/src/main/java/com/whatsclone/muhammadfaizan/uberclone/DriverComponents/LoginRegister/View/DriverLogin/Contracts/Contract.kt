package com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.Contracts

interface Contract {
    interface ILoginViewDriver {
        fun onLoginResults(result: Boolean)
        fun onFirebaseResults(exc: Exception?)
    }

    interface ILoginModelDriver {
        fun validateCredentials(): Boolean
    }

    interface ILoginPresenterDriver {
        fun onLoginInitiated(email: String, pass: String)
        fun loginUserToFirebase(email: String, pass: String)
    }
}