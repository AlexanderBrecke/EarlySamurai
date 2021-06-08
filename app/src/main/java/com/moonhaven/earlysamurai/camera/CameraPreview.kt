package com.moonhaven.earlysamurai.camera

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.lang.Exception

class CameraPreview(val context: Context) {

    // Setup a camera provider
    private lateinit var cameraProvider:ProcessCameraProvider

    // Function to get the camera provider and start the camera
    // Get an instance of camera provider, set the camera provider from this instance
    // Bind the preview
    fun getCameraProviderAndBindPreview(preview: PreviewView, owner: LifecycleOwner){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener(Runnable{
            val camProvider: ProcessCameraProvider = cameraProviderFuture.get()
            cameraProvider = camProvider
            bindPreview(preview,owner)
        }, ContextCompat.getMainExecutor(context))
    }

    // Function to start the camera
    // Setup and build a preview, as well as set the surface provider
    // Set camera to select
    // Unbind then bind provider to make sure we have the right one
    fun bindPreview(preview: PreviewView, owner: LifecycleOwner){
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(preview.surfaceProvider)
        }
        val cameraSelector =  CameraSelector.DEFAULT_FRONT_CAMERA
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(owner,cameraSelector,preview)
        } catch (e: Exception){
            Log.e(TAG, "Use case binding failed", e)
        }
    }

    // Function to stop the camera
    fun stopCamera(){
        cameraProvider.unbindAll()
    }

    // Companion object for logging
    companion object {
        private const val TAG = "CameraXBasic"
    }

}