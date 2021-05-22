package com.moonhaven.earlysamurai

import android.app.Application

// Logic to set a custom application so we can get application context anywhere
class EarlySamuraiApplication:Application() {

    companion object{
        lateinit var application:EarlySamuraiApplication
    }

    override fun onCreate() {
        super.onCreate()

        application = this
    }

}