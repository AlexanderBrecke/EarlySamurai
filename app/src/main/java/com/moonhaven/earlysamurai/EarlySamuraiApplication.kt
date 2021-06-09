package com.moonhaven.earlysamurai

import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import com.moonhaven.earlysamurai.camera.CustomCameraLogic


// Logic to set a custom application so we can get application context anywhere
// Added camera logic to have access to camera provider everywhere
class EarlySamuraiApplication:Application(), CameraXConfig.Provider {

    companion object{
        lateinit var application:EarlySamuraiApplication
        lateinit var customCameraLogic:CustomCameraLogic
    }

    override fun onCreate() {
        super.onCreate()

        application = this
        customCameraLogic = CustomCameraLogic(application.applicationContext)
        customCameraLogic.getCameraProvider()
    }

    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }

}