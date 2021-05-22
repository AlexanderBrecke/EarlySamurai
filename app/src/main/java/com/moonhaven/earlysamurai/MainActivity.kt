package com.moonhaven.earlysamurai

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.moonhaven.earlysamurai.database.AppDatabase
import com.moonhaven.earlysamurai.database.IdeaDAO
import com.moonhaven.earlysamurai.database.UserDAO
import com.moonhaven.earlysamurai.mockdata.MockData
import com.moonhaven.earlysamurai.ui.custom.CustomHeaderBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        setHeaderBackArrowFunction()

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

    fun setHeaderBackArrowFunction(){
        headerBar.runWhenBackPressed {
            onBackPressed()
        }
    }

    // --- ---

//    //Database initialization with mock data
//    private fun populateDatabase(populate:Boolean){
//        CoroutineScope(Dispatchers.IO).launch {
//
//            //Initialize DAOs
//            val userDao = database.userDAO()
//            val ideaDAO = database.ideaDAO()
//
//            // Values to not have to write too many times
//            val emptyUserDb = userDao.getAllUsers().isNullOrEmpty()
//            val emptyIdeaDb = ideaDAO.getAllIdeas().isNullOrEmpty()
//
//            //If we are supposed to populate the database
//            if(populate){
//                initializeUserMockData(userDao)
//                initializeIdeasMockData(ideaDAO)
//            } else {
//                // Check, if not empty wipe database
//                if(!emptyUserDb || !emptyIdeaDb) Log.d("FOO", "Wiping the database..")
//                if(!emptyUserDb) userDao.deleteAllUsers()
//                if(!emptyIdeaDb) ideaDAO.deleteAllIdeas()
//            }
//
//            // Logs for information
//            if(userDao.getAllUsers().isNullOrEmpty()) Log.d("FOO", "Currently 0 users in database")
//            else Log.d("FOO", "Currently ${userDao.getAllUsers()?.size} users in database \n ${userDao.getAllUsers().toString()}")
//
//            if(ideaDAO.getAllIdeas().isNullOrEmpty()) Log.d("FOO", "Currently 0 ideas in database")
//            else Log.d("FOO", "Currently ${ideaDAO.getAllIdeas()?.size} ideas in database \n ${ideaDAO.getAllIdeas().toString()}")
//        }
//    }
//
//    // Function to initialize user mock data
//    private fun initializeUserMockData(userDao: UserDAO){
//        if(userDao.getAllUsers().isNullOrEmpty()){
//            Log.d("FOO","Populating users..")
//            for(user in mockData.userList) {
//                userDao.insertUser(user)
//            }
//            Log.d("FOO", "Added: '${mockData.userList.size}' users to the database")
//        }
//    }
//
//    // Function to initialize idea mock data
//    private fun initializeIdeasMockData(ideaDao:IdeaDAO){
//        if(ideaDao.getAllIdeas().isNullOrEmpty()){
//            Log.d("FOO","Populating ideas..")
//            for(idea in mockData.ideaList){
//                ideaDao.insertIdea(idea)
//            }
//            Log.d("FOO", "Added: '${mockData.ideaList.size}' ideas to the database")
//        }
//    }
}