package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterPresenter

import android.content.Context
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterModel.IModelRegisterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterModel.ModelRegisterRider
import com.whatsclone.muhammadfaizan.uberclone.RiderComponents.RiderRegister.RegisterView.IRegisterViewRider

class PresenterRegisterRider(context: Context, registerViewRider: IRegisterViewRider) : IPresenterRegisterRider {
    private var context = context
    private var registerViewRider = registerViewRider
    lateinit var modelRegisterRider: IModelRegisterRider

    override fun initRegistration(email: String, password: String) {
        modelRegisterRider = ModelRegisterRider(email, password)
        if (modelRegisterRider.validateCredentials()) {
            registerViewRider.onRegistrationResults("success")
        } else {
            registerViewRider.onRegistrationResults("error")
        }
    }
}