package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterView

interface IRegisterViewRider {
    fun onRegistrationResults(results: String)
    fun onFirebaseResults(exc: Exception?)
}