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
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter.IRiderProfileSetupPresenter
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter.RiderProfileSetupPresenter
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream

class RiderProfileSetup : AppCompatActivity(), IRiderProfileSetup {

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var edtUserName: EditText
    private lateinit var edtUserPhone: EditText
    private lateinit var btnSave: Button
    private lateinit var imgUser: CircleImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: IRiderProfileSetupPresenter
    private lateinit var bitmap: Bitmap
    private lateinit var stream: ByteArrayOutputStream
    private lateinit var name: String
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
        edtUserPhone = findViewById(R.id.edt_rider_set_phone)
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
            name = edtUserName.text.toString()
            phone = edtUserPhone.text.toString()
            presenter.initValidation(name, phone)
        }
    }

    override fun onValidationResults(results: Boolean) {
        if (results) {
            try {
                presenter.uploadImage(stream!!)
            } catch (exc: Exception) {
                hideProgress()
                snackFailure("Select an image first")
            }
        } else {
            hideProgress()
            snackFailure("Enter a valid name and phone number")
        }
    }


    override fun onUploadResult(exc: Exception?, uri: Uri?) {
        if (exc == null) {
            presenter.saveUserData(name, phone, uri!!)
        } else {
            hideProgress()
            snackError(exc!!, "Upload Error", "Image upload error")
        }
    }

    override fun onDatabaseResults(exc: Exception?) {
        if (exc == null) {
            hideProgress()
            snackSuccess("Profile saved successfully")
        } else {
            hideProgress()
            snackError(exc!!, "Database Error", "Data cannot be saved right now")
        }
    }


    private fun hideProgress() {
        btnSave.isEnabled = true
        progressBar.visibility = View.INVISIBLE
    }

    override fun onStart() {
        super.onStart()
        progressBar.visibility = View.INVISIBLE
    }

    private fun snackSuccess(message: String) {
        var snackbar: Snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
        var mView: View = snackbar.view
        mView.setBackgroundColor(ContextCompat.getColor(this@RiderProfileSetup, R.color.blue))
        var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtView.setTextColor(Color.WHITE)
        snackbar.show()
    }

    private fun snackError(exc: Exception, dialogTitle: String, message: String) {
        var snackbar: Snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
        var mView: View = snackbar.view
        mView.setBackgroundColor(ContextCompat.getColor(this@RiderProfileSetup, R.color.red))
        var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtView.setTextColor(Color.WHITE)
        snackbar.setAction("DETAILS") {
            var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@RiderProfileSetup, R.style.mDialog)
            alertDialog.setTitle(dialogTitle)
            alertDialog.setMessage(exc.message!!.toString())
            alertDialog.setPositiveButton("Close") { dialog, which ->
                dialog.cancel()
            }
            alertDialog.show()
        }
        snackbar.show()
    }

    private fun snackFailure(message: String) {
        var snackbar: Snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
        var mView: View = snackbar.view
        mView.setBackgroundColor(ContextCompat.getColor(this@RiderProfileSetup, R.color.red))
        var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtView.setTextColor(Color.WHITE)
        snackbar.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 69 && resultCode == RESULT_OK && data != null) {
            imgUser.setImageURI(data.data!!)
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data!!)
            stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, stream)
        }
    }
}
