package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupModel.IRiderProfileSetupModel
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupModel.RiderProfileSetupModel
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView.IRiderProfileSetup
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView.RiderProfileSetup
import java.io.ByteArrayOutputStream

class RiderProfileSetupPresenter constructor(context: Context, riderView: RiderProfileSetup, constraintLayout: ConstraintLayout) : IRiderProfileSetupPresenter {

    private var constraintLayout: ConstraintLayout = constraintLayout
    private var context: Context = context
    private var riderView: IRiderProfileSetup = riderView
    private lateinit var model: IRiderProfileSetupModel
    private var storageRef: StorageReference = FirebaseStorage.getInstance().getReference("/Uber_Profile_Images/${FirebaseAuth.getInstance().uid.toString()}.jpg")
    private var dbRef = FirebaseDatabase.getInstance().getReference("Uber").child("Users").child(FirebaseAuth.getInstance().uid.toString())

    override fun uploadImage(stream: ByteArrayOutputStream) {
        var uploadTask = storageRef.putBytes(stream.toByteArray())

        val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation storageRef.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                riderView.onUploadResult(null, task.result!!)
            } else {
                riderView.onUploadResult(task.exception!!, null)
            }
        }
    }

    override fun saveUserData(name: String, phone: String, uri: Uri) {

        var map: MutableMap<String, String> = HashMap<String, String>()
        map["name"] = name
        map["image_uri"] = uri.toString()
        map["phone"] = phone
        dbRef.updateChildren(map.toMap()).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                riderView.onDatabaseResults(null)
            } else {
                riderView.onDatabaseResults(task.exception!!)
            }
        }
    }

    override fun initValidation(email: String, phone: String) {
        model = RiderProfileSetupModel(email, phone)
        riderView.onValidationResults(model.validate())
    }

    override fun snackSuccess(message: String) {
        var snackbar: Snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
        var mView: View = snackbar.view
        mView.setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
        var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtView.setTextColor(Color.WHITE)
        snackbar.show()
    }

    override fun snackError(exc: Exception, dialogTitle: String, message: String) {
        var snackbar: Snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
        var mView: View = snackbar.view
        mView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtView.setTextColor(Color.WHITE)
        snackbar.setAction("DETAILS") {
            var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context, R.style.mDialog)
            alertDialog.setTitle(dialogTitle)
            alertDialog.setMessage(exc.message!!.toString())
            alertDialog.setPositiveButton("Close") { dialog, which ->
                dialog.cancel()
            }
            alertDialog.show()
        }
        snackbar.show()
    }

    override fun snackFailure(message: String) {
        var snackbar: Snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
        var mView: View = snackbar.view
        mView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtView.setTextColor(Color.WHITE)
        snackbar.show()
    }
}