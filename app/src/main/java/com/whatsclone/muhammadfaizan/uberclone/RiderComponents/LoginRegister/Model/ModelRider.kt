package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Model

class ModelRider constructor(email: String, password: String) : IModelRider {

    private var email_rider: String = email
    private var password_rider: String = password

    override fun validateCredentials(): Boolean {
        return email_rider != "" && password_rider != "" && email_rider.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$".toRegex()) && password_rider.length >= 6
    }
}
