package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterModel

class ModelRegisterRider constructor(email: String, password: String) : IModelRegisterRider {

    private var email: String = email
    private var password: String = password

    override fun validateCredentials(): Boolean {
        return (email != null && password != null && email.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$".toRegex()) && password.length >= 6)
    }
}