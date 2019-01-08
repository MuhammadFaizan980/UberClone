package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter

import java.io.ByteArrayOutputStream

interface IRiderProfileSetupPresenter {
    fun uploadImage(stream: ByteArrayOutputStream)
    fun saveUserData()
    fun initValidation(email:String, phone:String)
}