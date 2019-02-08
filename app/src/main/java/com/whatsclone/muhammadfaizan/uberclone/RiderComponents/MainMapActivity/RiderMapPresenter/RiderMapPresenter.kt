package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView.IRiderMainMapActivity
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView.RIderMainMapActivity

class RiderMapPresenter constructor(context: Context, riderMapView: RIderMainMapActivity) : IRiderMapPresenter {

    var context: Context = context
    var view: IRiderMainMapActivity = riderMapView
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onLocationSearchInitiated(locationName: String) {
       try{
           var geocoder: Geocoder = Geocoder(context)
           var list: List<Address> = geocoder.getFromLocationName(locationName, 1)
           if (list.isNotEmpty()) {
               view.onLocationResults(list[0])
           } else {
               return view.onLocationResults(null)
           }
       } catch (exc: Exception) {
           view.onLocationResults(null)
       }
    }

    @SuppressLint("MissingPermission")
    override fun getUserCurrentLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        try {
            var locationTask = fusedLocationProviderClient.lastLocation
            locationTask.addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    view.onGetUserCurrentLocation(task.result)
                } else {
                    Toast.makeText(context, "Location Error", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (exc:Exception) {
            view.onGetUserCurrentLocation(null)
        }
    }
}