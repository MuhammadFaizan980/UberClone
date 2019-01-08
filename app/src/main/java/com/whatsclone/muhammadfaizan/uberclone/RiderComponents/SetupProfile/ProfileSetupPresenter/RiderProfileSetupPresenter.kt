package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupPresenter

import android.content.Context
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView.IRiderProfileSetup
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupView.RiderProfileSetup

class RiderProfileSetupPresenter constructor(context: Context, riderView: RiderProfileSetup) : IRiderProfileSetupPresenter {

    private var context: Context = context
    private var riderView: IRiderProfileSetup = riderView

    override fun uploadImage(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUserData(): Exception {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}