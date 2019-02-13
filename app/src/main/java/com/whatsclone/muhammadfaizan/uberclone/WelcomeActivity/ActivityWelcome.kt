package com.whatsclone.muhammadfaizan.uberclone.WelcomeActivity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Button
import com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin.LoginView.LoginViewDriver
import com.whatsclone.muhammadfaizan.uberclone.R
import com.whatsclone.muhammadfaizan.uberclone.UserComponents.LoginRegister.LoginViewRider

class ActivityWelcome : AppCompatActivity() {

    private lateinit var btnDriverLogin: Button
    private lateinit var btnRiderLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)
        initViews()
        setListeners()
        checkPermissions()
    }

    private fun initViews() {
        btnDriverLogin = findViewById(R.id.btnLoginDriver)
        btnRiderLogin = findViewById(R.id.btnLoginRider)
    }

    private fun setListeners() {
        btnRiderLogin.setOnClickListener {
            startActivity(Intent(this@ActivityWelcome, LoginViewRider::class.java))
            this@ActivityWelcome.finish()
        }

        btnDriverLogin.setOnClickListener {
            startActivity(Intent(this@ActivityWelcome, LoginViewDriver::class.java))
            this@ActivityWelcome.finish()
        }
    }

    private fun checkPermissions() {
        var arr = arrayOfNulls<String>(2)
        arr[0] = android.Manifest.permission.ACCESS_FINE_LOCATION
        arr[1] = android.Manifest.permission.ACCESS_COARSE_LOCATION

        if (ContextCompat.checkSelfPermission(this@ActivityWelcome, arr[0]!!) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this@ActivityWelcome, arr[1]!!) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@ActivityWelcome, arr, 69)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 69 && grantResults.isNotEmpty()) {
            for (permission in grantResults) {
                if (permission == PackageManager.PERMISSION_DENIED) {
                    var dialog = AlertDialog.Builder(this@ActivityWelcome, R.style.mDialog)
                    dialog.setCancelable(false)
                    dialog.setTitle("Warning")
                    dialog.setMessage("You will not be able to share your location if location permissions are denied")
                    dialog.setPositiveButton("Close") { dialog, which ->
                        dialog.dismiss()
                        this@ActivityWelcome.finish()
                    }
                    dialog.show()
                    return
                }
            }
        }
    }
}
