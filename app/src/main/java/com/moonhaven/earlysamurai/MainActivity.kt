package com.moonhaven.earlysamurai

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.moonhaven.earlysamurai.database.AppDatabase
import com.moonhaven.earlysamurai.mockdata.MockData
import com.moonhaven.earlysamurai.ui.custom.CustomHeaderBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var headerBar: CustomHeaderBar

    private lateinit var database:AppDatabase
    private lateinit var mockData:MockData

    // Initialize bottom nav bar
    // Set tint of nav bar to null because we use custom icons
    // Setup nav controller
    // Hide action bar and use custom header bar
    // Set correct logo
    // Initialize database and mock data class
    // Populate database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        supportActionBar?.hide()
        headerBar = CustomHeaderBar(findViewById(R.id.main_header_bar))
        setCorrectLogo("HOME")
        setRegularHeaderBackArrowFunction()

        database = AppDatabase.getDatabase(this@MainActivity)

        mockData = MockData()
        mockData.populateDatabase(database,true)

    }

    // --- Functions to manipulate the header bar ---

    fun setCorrectLogo(title:String){
        hideBackArrowInHeaderBar()
        if(title == "HOME") headerBar.setRightLogoImage(R.drawable.logo_home)
        if(title == "EXPLORE") headerBar.setRightLogoImage(R.drawable.logo_explore)
        if(title == "DEALS") headerBar.setRightLogoImage(R.drawable.logo_deals)
        if(title == "PROFILE") headerBar.setRightLogoImage(R.drawable.logo_profile)
        if(title == "big_logo"){
            headerBar.hideRightLogo()
            headerBar.showMiddleLogo()
        }
    }

    private fun hideBackArrowInHeaderBar(){
        headerBar.hideBackArrow()
    }

    fun showBackArrowInHeaderBar(){
        headerBar.showBackArrow()
    }

    fun setRegularHeaderBackArrowFunction(){
        headerBar.runWhenBackPressed {
            onBackPressed()
        }
    }

    fun setOtherBackArrowFunction(onClick: () -> Unit){
        headerBar.runWhenBackPressed {
            onClick()
        }
    }

    // --- ---

    // Function to start a new activity
    fun startNewActivity(activityToStart:AppCompatActivity){
        val intent = Intent(this, activityToStart::class.java)
        startActivity(intent)
    }

}