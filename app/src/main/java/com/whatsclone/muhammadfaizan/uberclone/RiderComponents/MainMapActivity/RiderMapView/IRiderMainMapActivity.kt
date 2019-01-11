package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView

import android.location.Address

interface IRiderMainMapActivity {
    fun onLocationResults(address: Address?)
}