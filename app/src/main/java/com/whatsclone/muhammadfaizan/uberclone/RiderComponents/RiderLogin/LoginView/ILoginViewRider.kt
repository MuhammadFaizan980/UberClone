package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderLogin.LoginView

interface ILoginViewRider {
    fun onLoginResults(results: Boolean)
    fun onFirebaseResults(results: Boolean, exc: Exception?)
}