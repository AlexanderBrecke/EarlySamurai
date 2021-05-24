package com.moonhaven.earlysamurai.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

class Notifications(private val notificationManager: NotificationManager) {

    fun createNotificationChannel(id:String, name:String, description:String){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(id,name,importance)

            channel.description = description
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100,200,300,400,500,400,300,200,400)
            notificationManager.createNotificationChannel(channel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification(context: Context, titleText:String, contentText:String){

        val notificationId = 101

        val channelId = "com.moonhaven.earlysamurai.foo"

        val notification = Notification.Builder(context,channelId)
            .setContentTitle(titleText)
            .setContentText(contentText)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setChannelId(channelId)
            .build()

        notificationManager.notify(notificationId, notification)

    }


}