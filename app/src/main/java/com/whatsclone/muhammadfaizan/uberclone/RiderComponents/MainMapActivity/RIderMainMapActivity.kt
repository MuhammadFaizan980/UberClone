package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity

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

class RIderMainMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var edtLocation: EditText
    private lateinit var imgSearch: ImageView
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rider_main_map)
        initViews()
        initMap()
    }

    private fun initViews(){
        edtLocation = findViewById(R.id.edt_rider_location_search)
        imgSearch = findViewById(R.id.img_rider_location_search)
        btnConfirm = findViewById(R.id.btn_confirm_rider_location)

        imgSearch.setOnClickListener {
            Toast.makeText(this@RIderMainMapActivity, "Searching...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initMap(){
        var mgr: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mgr.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
