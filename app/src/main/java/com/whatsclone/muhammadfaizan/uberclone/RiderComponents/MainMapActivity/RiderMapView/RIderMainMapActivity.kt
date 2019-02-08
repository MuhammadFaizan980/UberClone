package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView

import android.annotation.SuppressLint
import android.location.Address
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter.IRiderMapPresenter
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter.RiderMapPresenter
import kotlinx.android.synthetic.main.activity_rider_main_map.*

class RIderMainMapActivity : AppCompatActivity(), OnMapReadyCallback, IRiderMainMapActivity {

    private lateinit var mMap: GoogleMap
    private lateinit var edtLocation: EditText
    private lateinit var imgSearch: ImageView
    private lateinit var btnConfirm: Button
    private lateinit var presenter: IRiderMapPresenter
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rider_main_map)
        initViews()
        initMap()
    }

    private fun initViews() {
        edtLocation = findViewById(R.id.edt_rider_location_search)
        imgSearch = findViewById(R.id.img_rider_location_search)
        btnConfirm = findViewById(R.id.btn_confirm_rider_location)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@RIderMainMapActivity)
        presenter = RiderMapPresenter(this, this@RIderMainMapActivity)

        imgSearch.setOnClickListener {
            val locationName: String = edtLocation.text.toString()
            presenter.onLocationSearchInitiated(locationName)
        }
    }

    @SuppressLint("MissingPermission")
    private fun initMap() {

        var fragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragment.getMapAsync(this)

        var locationTask = fusedLocationProviderClient.lastLocation
        locationTask.addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                mMap.isMyLocationEnabled = true
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(task.result!!.latitude, task.result!!.longitude), 15f))
            } else {
                Toast.makeText(this@RIderMainMapActivity, "Location Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onLocationResults(address: Address?) {
        if (address != null) {
            setTargetLocation(address!!)
        } else {
            Toast.makeText(this@RIderMainMapActivity, "Search results error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTargetLocation(address: Address) {
        var markerOptions: MarkerOptions = MarkerOptions().position(LatLng(address.latitude, address.longitude)).snippet(address.locality)
        mMap.addMarker(markerOptions)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(address.latitude, address.longitude), 15f))
    }
}
