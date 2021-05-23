package com.moonhaven.earlysamurai

import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig


// Logic to set a custom application so we can get application context anywhere
class EarlySamuraiApplication:Application(), CameraXConfig.Provider {

    companion object{
        lateinit var application:EarlySamuraiApplication
    }

    override fun onCreate() {
        super.onCreate()

        application = this
    }

    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }

}