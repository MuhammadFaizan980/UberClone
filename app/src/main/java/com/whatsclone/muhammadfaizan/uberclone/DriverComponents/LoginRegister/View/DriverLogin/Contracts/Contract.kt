package com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.Contracts

interface Contract {
    interface ILoginViewDriver {
        fun onLoginResults(result: Boolean)
    }

    interface ILoginModelDriver {
        fun validateCredentials(): Boolean
    }

    interface ILoginPresenterDriver {
        fun onLoginInitiated(email: String, pass: String)
    }
}