package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterPresenter

interface IPresenterRegisterRider {
    fun initValidation(email: String, password: String)
    fun initRegistration(email: String, password: String)
}