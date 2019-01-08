package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter.IRiderProfileSetupPresenter
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter.RiderProfileSetupPresenter
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream

class RiderProfileSetup : AppCompatActivity(), IRiderProfileSetup {

    private lateinit var edtUserName: EditText
    private lateinit var edtuserPhone: EditText
    private lateinit var btnSave: Button
    private lateinit var imgUser: CircleImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var profileSetupPresenter: IRiderProfileSetupPresenter
    lateinit var bitmap: Bitmap
    lateinit var stream: ByteArrayOutputStream

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rider_profile_setup)
        initViews()
        getImage()
        passListeners()
    }

    private fun initViews() {
        edtUserName = findViewById(R.id.edt_rider_set_name)
        edtuserPhone = findViewById(R.id.edt_rider_set_phone)
        btnSave = findViewById(R.id.btn_save_rider_profile)
        imgUser = findViewById(R.id.img_rider_set_image)
        progressBar = findViewById(R.id.progress_rider_profile_setup)
        profileSetupPresenter = RiderProfileSetupPresenter(this, this@RiderProfileSetup)
    }

    private fun getImage() {
        imgUser.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 60)
        }
    }

    private fun passListeners() {

    }

    override fun uploadResult(results: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finalResults(results: Exception) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun hideProgress() {
        btnSave.isEnabled = true
        progressBar.visibility = View.INVISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 69 && resultCode == Activity.RESULT_OK && data != null) {
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
            stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream)
        }
    }
}
