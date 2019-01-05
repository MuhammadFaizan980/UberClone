package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Model

class ModelRider constructor(email: String, password: String) : IModelRider {

    private var email_rider: String = email
    private var password_rider: String = password

    override fun validateCredentials(): Boolean {
        return !email_rider.equals("") && !password_rider.equals("") && password_rider.length >= 6
    }
}