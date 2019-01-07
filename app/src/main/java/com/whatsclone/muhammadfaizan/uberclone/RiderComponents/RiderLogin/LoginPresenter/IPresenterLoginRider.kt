package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginPresenter

interface IPresenterLoginRider {
    fun onLoginInitiated(email: String, password: String)
    fun authenticateUser(email: String, password: String)
}