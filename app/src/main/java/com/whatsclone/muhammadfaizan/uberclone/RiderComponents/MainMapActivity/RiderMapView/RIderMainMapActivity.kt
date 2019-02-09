package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapView

import android.annotation.SuppressLint
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter.IRiderMapPresenter
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.MainMapActivity.RiderMapPresenter.RiderMapPresenter

class RIderMainMapActivity : AppCompatActivity(), OnMapReadyCallback, IRiderMainMapActivity {

    private lateinit var mMap: GoogleMap
    private lateinit var edtLocation: EditText
    private lateinit var imgSearch: ImageView
    private lateinit var btnConfirm: Button
    private lateinit var presenter: IRiderMapPresenter
    private lateinit var targetLocation: LatLng
    private lateinit var currentLocation: LatLng

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
            val locationName: String = edtLocation.text.toString()
            presenter.onLocationSearchInitiated(locationName)
        }
        btnConfirm.visibility = View.INVISIBLE
        btnConfirm.setOnClickListener {
            FirebaseDatabase.getInstance().getReference("Uber").child("Users").child(FirebaseAuth.getInstance().uid.toString())
                    .addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {}

                        override fun onDataChange(p0: DataSnapshot) {
                            var infoMap = p0.value as HashMap<String, String>
                            var map = HashMap<String, String>()

                        }
                    })
        }
    }

    private fun initMap() {
        var fragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragment.getMapAsync(this)
        presenter.getUserCurrentLocation()
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

    @SuppressLint("MissingPermission")
    override fun onGetUserCurrentLocation(location: Location?) {
        if (location != null) {
            currentLocation = LatLng(location.latitude, location.longitude)
            mMap.clear()
            Toast.makeText(this@RIderMainMapActivity, "Tap and hold the marker to Drag the it and drop it on the desired target location", Toast.LENGTH_LONG).show()
            mMap.isMyLocationEnabled = true
            var options = MarkerOptions().position(LatLng(location.latitude, location.longitude)).draggable(true)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location!!.latitude, location!!.longitude), 15f))
            mMap.addMarker(options)
            mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDragEnd(p0: Marker?) {
                    targetLocation = LatLng(p0!!.position.latitude, p0!!.position.longitude)
                    btnConfirm.visibility = View.VISIBLE
                }

                override fun onMarkerDragStart(p0: Marker?) {
                }

                override fun onMarkerDrag(p0: Marker?) {
                }
            })
        } else {
            Toast.makeText(this@RIderMainMapActivity, "Location Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTargetLocation(address: Address) {
        mMap.clear()
        btnConfirm.visibility = View.VISIBLE
        var markerOptions: MarkerOptions = MarkerOptions().position(LatLng(address.latitude, address.longitude)).snippet(address.locality).draggable(true)
        mMap.addMarker(markerOptions)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(address.latitude, address.longitude), 15f))
    }
}
