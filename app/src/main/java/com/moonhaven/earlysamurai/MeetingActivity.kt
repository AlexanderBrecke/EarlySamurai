package com.moonhaven.earlysamurai

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import com.google.common.util.concurrent.ListenableFuture
import com.moonhaven.earlysamurai.ui.custom.CustomHeaderBar
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.moonhaven.earlysamurai.camera.CameraPreview
import kotlinx.android.synthetic.main.activity_meeting.*
import kotlinx.android.synthetic.main.activity_meeting.view.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MeetingActivity:AppCompatActivity() {

    private lateinit var headerBar:CustomHeaderBar

    private lateinit var cameraPreview:CameraPreview
    private lateinit var cameraExecutor: ExecutorService

    private lateinit var hideCameraButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_meeting)

        supportActionBar?.hide()

        headerBar = CustomHeaderBar(findViewById(R.id.main_header_bar))
        hideCameraButton = hide_camera_button

        setCorrectLogo()
        setClickListeners()

        cameraPreview = CameraPreview()
        cameraExecutor = Executors.newSingleThreadExecutor()

        if (allPermissionsGranted()) {
            cameraPreview.startCamera(this, current_user_camera_preview, this)
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun setClickListeners(){
        hideCameraButton.setOnClickListener {
            current_user_camera_preview.visibility = View.GONE
            current_user_profile_picture.visibility = View.VISIBLE
        }
    }

    private fun setCorrectLogo(){
        headerBar.hideRightLogo()
        headerBar.showMiddleLogo()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                cameraPreview.startCamera(this,current_user_camera_preview,this)
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }


}