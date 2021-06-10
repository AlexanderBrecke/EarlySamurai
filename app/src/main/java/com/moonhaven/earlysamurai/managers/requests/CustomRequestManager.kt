package com.moonhaven.earlysamurai.managers.requests

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.moonhaven.earlysamurai.EarlySamuraiApplication

// Custom request manager
// This should be able to check if all permissions are granted
// It should be able to ask for permissions
// And show a dialogue if permission is needed but has been denied
object CustomRequestManager {
    private const val REQUEST_CODE_PERMISSIONS = 10
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        val context = EarlySamuraiApplication.application.applicationContext
        ContextCompat.checkSelfPermission(
            context, it) == PackageManager.PERMISSION_GRANTED
    }

    // Function to request permission
    fun requestPermission(requestingActivity:Activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                requestingActivity.shouldShowRequestPermissionRationale(REQUIRED_PERMISSIONS[0]) -> showDialogue(requestingActivity)
                else -> sendPermissionRequest(requestingActivity)
            }
        }
    }

    // Function to actually send the request
    private fun sendPermissionRequest(requestingActivity: Activity){
        ActivityCompat.requestPermissions(requestingActivity,
            REQUIRED_PERMISSIONS,
            REQUEST_CODE_PERMISSIONS
        )
    }

    // Function to show a dialogue if permission is needed but has been denied
    private fun showDialogue(requestingActivity: Activity){
        val builder = AlertDialog.Builder(requestingActivity)
        builder.apply {
            setTitle("Permission required")
            setMessage("Permission to access your camera is required to to stream video")
            setPositiveButton("OK") {dialogue,which ->
                sendPermissionRequest(requestingActivity)
            }
        }
        val dialogue = builder.create()
        dialogue.show()
    }
}