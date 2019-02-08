package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView

import android.location.Address
import android.location.Location

interface IRiderMainMapActivity {
    fun onLocationResults(address: Address?)
    fun onGetUserCurrentLocation(location:Location?)
}