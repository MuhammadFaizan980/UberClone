package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView

import android.location.Address
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter.IRiderMapPresenter
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter.RiderMapPresenter

class RIderMainMapActivity : AppCompatActivity(), OnMapReadyCallback, IRiderMainMapActivity {

    private lateinit var mMap: GoogleMap
    private lateinit var edtLocation: EditText
    private lateinit var imgSearch: ImageView
    private lateinit var btnConfirm: Button
    private lateinit var presenter: IRiderMapPresenter

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
        presenter = RiderMapPresenter(this, this@RIderMainMapActivity)

        imgSearch.setOnClickListener {
            var locationName: String = edtLocation.text.toString()
            presenter.onLocationSearchInitiated(locationName)
        }
    }

    private fun initMap() {
        var mgr: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mgr.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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
