package com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.LoginModel

import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.Contracts.Contract

class LoginModelDriver constructor(email: String, pass: String) : Contract.ILoginModelDriver {
    var email: String = email
    var pass: String = pass

    override fun validateCredentials(): Boolean {
        return pass != null && email != null && pass.length >= 6 && email.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})\$".toRegex())
    }
}