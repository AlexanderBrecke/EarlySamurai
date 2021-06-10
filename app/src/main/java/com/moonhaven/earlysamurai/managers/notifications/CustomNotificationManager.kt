package com.moonhaven.earlysamurai.managers.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.moonhaven.earlysamurai.EarlySamuraiApplication
import com.moonhaven.earlysamurai.mainNotificationChannelId

object CustomNotificationManager {
    private var notificationManager: NotificationManager


    init {
        val context = EarlySamuraiApplication.application.applicationContext
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createMainNotificationChannel()
    }

    // Function to create main notification channel
    private fun createMainNotificationChannel(){
        val name = "Main"
        val description = "Main notification channel"
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =  NotificationChannel(mainNotificationChannelId,name,importance)
            channel.description = description
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100,200,300,400,500,400,300,200,400)
            notificationManager.createNotificationChannel(channel)

        }
    }

    // Function to send notification
    fun sendNotification(titleText:String, contentText:String, notificationId:Int = 101, notificationChannelId: String = mainNotificationChannelId){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val context = EarlySamuraiApplication.application.applicationContext

            val notification = Notification.Builder(context, notificationChannelId)
                .setContentTitle(titleText)
                .setContentText(contentText)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(notificationChannelId)
                .build()
            notificationManager.notify(notificationId, notification)
        }

    }


}