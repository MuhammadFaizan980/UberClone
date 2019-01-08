package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.whatsclone.muhammadfaizan.uberclone.R
import de.hdodenhof.circleimageview.CircleImageView

class RiderProfileSetup : AppCompatActivity() {

    private lateinit var edtUserName: EditText
    private lateinit var edtuserPhone: EditText
    private lateinit var btnSave: Button
    private lateinit var imgUser: CircleImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rider_profile_setup)
    }
}
