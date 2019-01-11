package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView.IRiderMainMapActivity
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView.RIderMainMapActivity

class RiderMapPresenter constructor(context: Context, riderMapView: RIderMainMapActivity) : IRiderMapPresenter {

    var context: Context = context
    var view: IRiderMainMapActivity = riderMapView

    override fun onLocationSearchInitiated(locationName: String) {
        var geocoder: Geocoder = Geocoder(context)
        var list: List<Address> = geocoder.getFromLocationName(locationName, 1)
        if (list.isNotEmpty()) {
            view.onLocationResults(list[0])
        } else {
            return view.onLocationResults(null)
        }
    }
}