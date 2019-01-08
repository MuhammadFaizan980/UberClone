package com.whatsclone.muhammadfaizan.uberclone.RiderComponents.SetupProfile.ProfileSetupModel

import java.io.ByteArrayOutputStream

class RiderProfileSetupModel constructor(name: String, phone: String) : IRiderProfileSetupModel {
    private var name: String = name
    private var phone: String = phone

    override fun validate(): Boolean {
        return (name != "" && phone != "")
    }
}