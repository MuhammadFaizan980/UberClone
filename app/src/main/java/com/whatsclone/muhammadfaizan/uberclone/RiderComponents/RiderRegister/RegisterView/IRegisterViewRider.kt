package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterView

interface IRegisterViewRider {
    fun onValidationResults(results: Boolean)
    fun onFirebaseResults(exc: Exception?)
}