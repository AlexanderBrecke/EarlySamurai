package com.moonhaven.earlysamurai

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.moonhaven.earlysamurai.ui.custom.CustomHeaderBar
import com.moonhaven.earlysamurai.managers.requests.CustomRequestManager
import kotlinx.android.synthetic.main.activity_meeting.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MeetingActivity:AppCompatActivity() {
    private val cameraProvider = EarlySamuraiApplication.customCameraLogic

    // Setup variables
    private lateinit var headerBar:CustomHeaderBar
    private lateinit var cameraExecutor: ExecutorService

    private lateinit var showCameraButton:ImageView
    private lateinit var hideCameraButton: ImageView

    private lateinit var goodByeButton: Button

    // Initialize variables and the camera view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting)

        supportActionBar?.hide()
        headerBar = CustomHeaderBar(findViewById(R.id.main_header_bar))

        showCameraButton = activate_camera_icon
        hideCameraButton = deactivate_camera_icon

        goodByeButton = goodbye

        cameraExecutor = Executors.newSingleThreadExecutor()

        setCorrectLogo()
        setClickListeners()

        showOrHideCameraPreview(false)
        if(CustomRequestManager.allPermissionsGranted()){
            showOrHideCameraPreview(true)
            cameraProvider.startCamera(current_user_camera_preview,this)
        } else {
            CustomRequestManager.requestPermission(this)
        }
    }

    // If we get results from permission
    // Granted, show camera preview and start the camera
    // Denied, tell the user permission was not granted and hide the camera preview
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        if(CustomRequestManager.allPermissionsGranted()){
            showOrHideCameraPreview(true)
            cameraProvider.startCamera(current_user_camera_preview,this)
        }
        else {
            Toast.makeText(this,"Permission not granted by the user.",Toast.LENGTH_SHORT).show()
            showOrHideCameraPreview(false)
        }
    }

    // Go to main activity if pressing back button
    override fun onBackPressed() {
        goToMainActivity()
    }

    // Make sure to shutdown camera on destroy
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    // Function to set click listeners
    private fun setClickListeners(){
        hideCameraButton.setOnClickListener {
            showOrHideCameraPreview(false)
        }
        showCameraButton.setOnClickListener{
            showOrHideCameraPreview(true)
        }
        goodByeButton.setOnClickListener {
            onBackPressed()
        }
    }

    // Function to show or hide the camera view
    // If we should show, make sure we have permission or ask for it if we don't
    // Then start the camera and show the preview as well as hide profile picture
    // Else stop the camera and hide the preview as well as show the profile picture
    private fun showOrHideCameraPreview(show:Boolean){
        if(show){
            if(!CustomRequestManager.allPermissionsGranted()){
                CustomRequestManager.requestPermission(this)
            } else {
                cameraProvider.startCamera(current_user_camera_preview,this)
                current_user_profile_picture.visibility = View.GONE
                current_user_camera_preview.visibility = View.VISIBLE
            }
        } else {
            cameraProvider.stopCamera()
            current_user_camera_preview.visibility = View.GONE
            current_user_profile_picture.visibility = View.VISIBLE
        }
    }

    // Function to shut down camera
    private fun shutDownCamera(){
        cameraProvider.stopCamera()
        cameraExecutor.shutdown()
    }

    // Function to go to main activity
    // Shut down camera and then start activity with intent
    private fun goToMainActivity(){
        shutDownCamera()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    // Function to show correct logo
    private fun setCorrectLogo(){
        headerBar.hideRightLogo()
        headerBar.showMiddleLogo()
    }

}