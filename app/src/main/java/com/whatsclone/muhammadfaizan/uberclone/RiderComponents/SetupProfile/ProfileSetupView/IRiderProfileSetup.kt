package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView

import android.net.Uri
import java.net.URL

interface IRiderProfileSetup {
    fun onUploadResult(results: Exception?, url: Uri)
    fun onDatabaseResults(results: Exception?)
    fun onValidationResults(results: Boolean)
}