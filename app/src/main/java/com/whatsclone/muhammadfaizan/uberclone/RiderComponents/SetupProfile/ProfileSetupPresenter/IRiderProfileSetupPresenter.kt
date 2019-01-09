package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter

import android.net.Uri
import java.io.ByteArrayOutputStream

interface IRiderProfileSetupPresenter {
    fun uploadImage(stream: ByteArrayOutputStream)
    fun saveUserData(name: String, phone: String, uri: Uri)
    fun initValidation(name: String, phone: String)
}