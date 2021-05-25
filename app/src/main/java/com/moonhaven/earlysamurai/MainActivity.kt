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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // set the tint of the nav view to null, as we use custom icons for the navigation menu
        navView.itemIconTintList = null

        // setup nav controller
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        // Hide the action bar as we use custom logo bar
        supportActionBar?.hide()

        //Initialize the header bar and set correct logo and the function for back arrow
        headerBar = CustomHeaderBar(findViewById(R.id.main_header_bar))
        setCorrectLogo("HOME")
        setRegularHeaderBackArrowFunction()

        //Initialize the database and mock data class, then populate database
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

    fun showBackArrowInHeaderBar(){
        headerBar.showBackArrow()
    }

    fun hideBackArrowInHeaderBar(){
        headerBar.hideBackArrow()
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

    fun startNewActivity(activityToStart:AppCompatActivity){
        val intent = Intent(this, activityToStart::class.java)
        startActivity(intent)
    }

}