package com.moonhaven.earlysamurai.ui.splash

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.moonhaven.earlysamurai.MeetingActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.notification.Notifications
import com.moonhaven.earlysamurai.ui.custom.CustomHeaderBar

class SplashActivity:AppCompatActivity() {

    // Setup values to use
    private val timeToLoad:Long = 2000
    private lateinit var headerBar: CustomHeaderBar
    private lateinit var notifications: Notifications

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hide the support action bar
        supportActionBar?.hide()

        // Set the header bar to our custom one and set the correct logo
        headerBar = CustomHeaderBar(findViewById(R.id.main_header_bar))
        setRightLogo()

        // Setup the notifications class
        notifications = Notifications(getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

        // Setup the notification channel
        notifications.createNotificationChannel("com.moonhaven.earlysamurai.foo", "EarlySamurai Meeting", "Meeting channel")

        // Send a notification that the meeting is starting
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notifications.sendNotification(this@SplashActivity, "Meeting starting", "Your meeting is starting.")
        }

        // Start the meeting activity after a short delay
        Handler().postDelayed(Runnable{
            intent = Intent(this, MeetingActivity::class.java)
            startActivity(intent)
        }, timeToLoad)

    }

    // Function to set the right logo
    private fun setRightLogo(){
        headerBar.hideMiddleLogo()
        headerBar.setRightLogoImage(R.drawable.logo_explore)
    }

}