package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Presenter

import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Model.IModelRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.Model.ModelRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.LoginRegister.View.ILoginViewRider

class PresenterRider(view: ILoginViewRider) : IPresenterRider {

    private var loginViewRider: ILoginViewRider = view
    private lateinit var modelRider: IModelRider

    override fun onLoginInitiated(email: String, password: String) {
        modelRider = ModelRider(email, password)
        loginViewRider.onLoginResults(modelRider.validateCredentials())
    }
}