package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter

import android.content.Context
import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupModel.IRiderProfileSetupModel
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupModel.RiderProfileSetupModel
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView.IRiderProfileSetup
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView.RiderProfileSetup
import java.io.ByteArrayOutputStream

class RiderProfileSetupPresenter constructor(context: Context, riderView: RiderProfileSetup) : IRiderProfileSetupPresenter {

    private var context: Context = context
    private var riderView: IRiderProfileSetup = riderView
    private lateinit var model: IRiderProfileSetupModel
    private var storageRef: StorageReference = FirebaseStorage.getInstance().getReference("/Profile_Pics/${FirebaseAuth.getInstance().uid.toString()}.jpg")

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

    override fun saveUserData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initValidation(email: String, phone: String) {
        model = RiderProfileSetupModel(email, phone)
        riderView.onValidationResults(model.validate())
    }
}