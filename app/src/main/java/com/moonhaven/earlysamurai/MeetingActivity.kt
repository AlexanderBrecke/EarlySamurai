package com.moonhaven.earlysamurai

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.moonhaven.earlysamurai.ui.custom.CustomHeaderBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.moonhaven.earlysamurai.camera.CameraPreview
import kotlinx.android.synthetic.main.activity_meeting.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MeetingActivity:AppCompatActivity() {

    private lateinit var headerBar:CustomHeaderBar

    private lateinit var cameraPreview:CameraPreview
    private lateinit var cameraExecutor: ExecutorService

    private lateinit var showCameraButton:ImageView
    private lateinit var hideCameraButton: ImageView

    private lateinit var goodByeButton: Button

    private var hasPermissions:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_meeting)

        hasPermissions = allPermissionsGranted()

        supportActionBar?.hide()
        headerBar = CustomHeaderBar(findViewById(R.id.main_header_bar))

        showCameraButton = activate_camera_icon
        hideCameraButton = deactivate_camera_icon

        goodByeButton = goodbye

        setCorrectLogo()
        setClickListeners()

        cameraPreview = CameraPreview(this)
        cameraExecutor = Executors.newSingleThreadExecutor()


        if (allPermissionsGranted()) {
            cameraPreview.getCameraProviderAndBindPreview(current_user_camera_preview, this)
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
    }

    override fun onResume() {
        super.onResume()
        if(allPermissionsGranted()){
            cameraPreview.bindPreview(current_user_camera_preview,this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onBackPressed() {
        goToMainActivity()
    }

    private fun setClickListeners(){
        hideCameraButton.setOnClickListener {
            cameraPreview.stopCamera()
            current_user_camera_preview.visibility = View.GONE
            current_user_profile_picture.visibility = View.VISIBLE
        }

        showCameraButton.setOnClickListener{
            cameraPreview.bindPreview(current_user_camera_preview,this)
            current_user_profile_picture.visibility = View.GONE
            current_user_camera_preview.visibility = View.VISIBLE
        }

        goodByeButton.setOnClickListener {
            onBackPressed()
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
                cameraPreview.getCameraProviderAndBindPreview(current_user_camera_preview,this)
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun goToMainActivity(){
        shutDownCamera()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun shutDownCamera(){
        cameraPreview.stopCamera()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }


}