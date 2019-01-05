package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Presenter

interface IPresenterRider {
    fun onLoginInitiated(email: String, password: String)
    fun authenticateUser(email: String, password: String)
}