package com.moonhaven.earlysamurai.camera

import android.Manifest
import android.content.ComponentCallbacks
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
    fun getCameraProviderAndStartCamera(preview: PreviewView, owner: LifecycleOwner){

        // Get a camera provider instance
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        // Do some camera provider magic
        cameraProviderFuture.addListener(Runnable{

            // Get the actual provider from the instance
            val camProvider: ProcessCameraProvider = cameraProviderFuture.get()

            //Set the set the provider here
            cameraProvider = camProvider

            // Let's start the camera
            startCamera(preview,owner)
        }, ContextCompat.getMainExecutor(context))
    }

    // Function to start the camera
    fun startCamera(preview: PreviewView,owner: LifecycleOwner){

        // Setup a preview and build it.
        val preview = Preview.Builder().build().also {
            // Also set the surface provider
            it.setSurfaceProvider(preview.surfaceProvider)
        }
        // Set the camera to front facing camera
        val cameraSelector =  CameraSelector.DEFAULT_FRONT_CAMERA

        try {
            // Make sure to unbind from the camera provider first
            cameraProvider.unbindAll()

            // Then bind the provider to make sure it is the right one.
            cameraProvider.bindToLifecycle(owner,cameraSelector,preview)
        } catch (e: Exception){
            Log.e(TAG, "Use case binding failed", e)
        }

    }

    // Function to stop the camera
    fun stopCamera(){
        cameraProvider.unbindAll()
    }

    companion object {
        private const val TAG = "CameraXBasic"
    }



}