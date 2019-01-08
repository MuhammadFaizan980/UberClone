package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter.IRiderProfileSetupPresenter
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter.RiderProfileSetupPresenter
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream

class RiderProfileSetup : AppCompatActivity(), IRiderProfileSetup {

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var edtUserName: EditText
    private lateinit var edtuserPhone: EditText
    private lateinit var btnSave: Button
    private lateinit var imgUser: CircleImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: IRiderProfileSetupPresenter
    private lateinit var bitmap: Bitmap
    private lateinit var stream: ByteArrayOutputStream
    private lateinit var email: String
    private lateinit var phone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rider_profile_setup)
        initViews()
        passListeners()
        getImage()
    }

    private fun initViews() {
        constraintLayout = findViewById(R.id.root_layout_rider_profile_setup)
        edtUserName = findViewById(R.id.edt_rider_set_name)
        edtuserPhone = findViewById(R.id.edt_rider_set_phone)
        btnSave = findViewById(R.id.btn_save_rider_profile)
        imgUser = findViewById(R.id.img_rider_set_image)
        progressBar = findViewById(R.id.progress_rider_profile_setup)
        presenter = RiderProfileSetupPresenter(this, this@RiderProfileSetup)
    }

    private fun getImage() {
        imgUser.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 69)
        }
    }

    private fun passListeners() {
        btnSave.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            btnSave.isEnabled = false
            email = edtUserName.text.toString()
            phone = edtuserPhone.text.toString()
            presenter.initValidation(email, phone)
        }
    }

    override fun onValidationResults(results: Boolean) {
        if (results) {
            try {
                presenter.uploadImage(stream!!)
            } catch (exc: Exception){
                hideProgress()
                var snackbar: Snackbar = Snackbar.make(constraintLayout, "Select an image first", Snackbar.LENGTH_LONG)
                var mView: View = snackbar.view
                mView.setBackgroundColor(ContextCompat.getColor(this@RiderProfileSetup, R.color.red))
                var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
                txtView.setTextColor(Color.WHITE)
                snackbar.show()
            }
        } else {
            hideProgress()
            var snackbar: Snackbar = Snackbar.make(constraintLayout, "Enter a valid email address and password", Snackbar.LENGTH_LONG)
            var mView: View = snackbar.view
            mView.setBackgroundColor(ContextCompat.getColor(this@RiderProfileSetup, R.color.red))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackbar.show()
        }
    }


    override fun onUploadResult(exc: Exception?, uri: Uri?) {
        if (exc == null) {
            hideProgress()
            Toast.makeText(this, uri!!.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDatabaseResults(exc: Exception?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun hideProgress() {
        btnSave.isEnabled = true
        progressBar.visibility = View.INVISIBLE
    }

    override fun onStart() {
        super.onStart()
        progressBar.visibility = View.INVISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 69 && resultCode == RESULT_OK && data != null) {
            imgUser.setImageURI(data.data!!)
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
            stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        }
    }
}
