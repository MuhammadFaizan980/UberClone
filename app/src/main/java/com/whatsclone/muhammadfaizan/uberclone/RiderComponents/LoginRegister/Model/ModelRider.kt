package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Model

class ModelRider constructor(email: String, password: String) : IModelRider {

    private var email_rider: String = email
    private var password_rider: String = password

    override fun validateCredentials(): Boolean {
        return email_rider != "" && password_rider != "" && email_rider.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}\$".toRegex()) && password_rider.length >= 6
    }
}
