package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter

import android.location.Address
import android.location.Location

interface IRiderMapPresenter {
    fun onLocationSearchInitiated(locationName: String)
    fun getUserCurrentLocation()
}