package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView

import android.net.Uri
import java.net.URL

interface IRiderProfileSetup {
    fun onUploadResult(exc: Exception?, uri: Uri?)
    fun onDatabaseResults(exc: Exception?)
    fun onValidationResults(results: Boolean)
}