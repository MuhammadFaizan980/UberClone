package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.View

interface ILoginViewRider {
    fun onLoginResults(results: Boolean)
    fun onFirebaseResults(results: Boolean, exc : Exception)
}