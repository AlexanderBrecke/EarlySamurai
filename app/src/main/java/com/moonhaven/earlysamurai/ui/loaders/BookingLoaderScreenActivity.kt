package com.moonhaven.earlysamurai.ui.loaders

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.moonhaven.earlysamurai.MeetingActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.notification.Notifications
import com.moonhaven.earlysamurai.ui.custom.CustomHeaderBar

class BookingLoaderScreenActivity:AppCompatActivity() {

    private val timeToLoad:Long = 2000
    private lateinit var headerBar: CustomHeaderBar

    // Send notification and go to meeting activity after a short delay
    // Make sure correct logo is showing
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        headerBar = CustomHeaderBar(findViewById(R.id.main_header_bar))
        setRightLogo()

        Notifications.sendNotification("Meeting", "Your meeting is starting")

        Handler().postDelayed({
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