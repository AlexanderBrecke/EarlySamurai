package com.moonhaven.earlysamurai.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

class Notifications(private val notificationManager: NotificationManager) {

    // Function to create a notification channel
    fun createNotificationChannel(id:String, name:String, description:String){

        // Check if the build version is correct
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Set the importance of the notification and the channel
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(id,name,importance)

            // Set some extra things for the channel
            channel.description = description
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100,200,300,400,500,400,300,200,400)
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Function to send notification
    // Need to require correct build version
    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification(context: Context, titleText:String, contentText:String){

        // Set a notification id
        val notificationId = 101

        // Choose an id for the channel
        val channelId = "com.moonhaven.earlysamurai.foo"

        // Create the notification
        val notification = Notification.Builder(context,channelId)
            .setContentTitle(titleText)
            .setContentText(contentText)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setChannelId(channelId)
            .build()

        // Send the notification
        notificationManager.notify(notificationId, notification)

    }


}