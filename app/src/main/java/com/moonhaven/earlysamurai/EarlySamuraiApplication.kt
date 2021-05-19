package com.moonhaven.earlysamurai

import android.app.Application

class EarlySamuraiApplication:Application() {

    companion object{
        lateinit var application:EarlySamuraiApplication
    }

    override fun onCreate() {
        super.onCreate()

        application = this
    }

}