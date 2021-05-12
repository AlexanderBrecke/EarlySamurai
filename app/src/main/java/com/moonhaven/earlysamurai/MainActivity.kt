package com.moonhaven.earlysamurai

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.moonhaven.earlysamurai.ui.logo.HeaderBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var headerBar:HeaderBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //Initialize the header bar and set correct logo
        headerBar = HeaderBar(findViewById(R.id.main_header_bar))
        setCorrectLogo("HOME")

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.itemIconTintList = null

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_explore, R.id.navigation_deals, R.id.navigation_profile))
        // Not using action bar, as we have our own action bar
//        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener {
            setCorrectLogo(it.title.toString())
            navController.navigate(it.itemId)
            true
        }
    }

    // Function to set the correct logo according to the title
    private fun setCorrectLogo(title:String){
        if(title == "HOME") headerBar.setRightLogoImage(R.drawable.logo_home)
        if(title == "EXPLORE") headerBar.setRightLogoImage(R.drawable.logo_explore)
        if(title == "DEALS") headerBar.setRightLogoImage(R.drawable.logo_deals)
        if(title == "PROFILE") headerBar.setRightLogoImage(R.drawable.logo_profile)
    }


}