package com.whatsclone.muhammadfaizan.uberclone.DriverComponents.LoginRegister.View.DriverLogin

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.whatsclone.muhammadfaizan.uberclone.R

class MessageClass {

    companion object {
        fun showMessage(view: ConstraintLayout, context: Context, message: String, isError: Boolean) = if (isError) {
            var snackBar: Snackbar = Snackbar.make(view, "Log in error", Snackbar.LENGTH_LONG)
            snackBar.setAction("DETAILS") {
                var dialog = AlertDialog.Builder(context)
                dialog.setTitle("Log in Error")
                dialog.setCancelable(false)
                dialog.setMessage(message)
                dialog.setPositiveButton("Close") { dialogWindow, which ->
                    dialogWindow.cancel()
                }
                dialog.show()
            }
            var mView: View = snackBar.view
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
            var txtView: TextView = mView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            txtView.setTextColor(Color.WHITE)
            snackBar.show()

        } else {
            Snackbar.make(view, "Login success!", Snackbar.LENGTH_SHORT).show()
        }

    }

}