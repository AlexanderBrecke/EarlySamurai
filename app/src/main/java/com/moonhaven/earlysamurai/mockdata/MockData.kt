package com.moonhaven.earlysamurai.mockdata

import android.util.Log
import com.moonhaven.earlysamurai.database.*
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.City
import com.moonhaven.earlysamurai.enums.IdeaStatus
import com.moonhaven.earlysamurai.enums.UserType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MockData{

    private val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id sagittis nulla. Ut porta placerat velit, quis malesuada nisi dapibus sit amet. In aliquet ornare sapien, non dignissim ipsum. Vivamus sed sem odio. Proin ultrices, elit non tempor dictum, purus diam maximus turpis, ac congue orci velit non nisl. Maecenas mollis ut risus eget sodales."
    private val loremIpsumShort = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id sagittis nulla."
    private val quote1 = "It's fucked"

    private val user1 = UserObject("asdf1", "FirstName1","LastName", UserType.Entrepreneur, City.Bergen, 42, mutableListOf(Category.Environment, Category.Finance, Category.Medicine),quote1,loremIpsum)
    private val user2 = UserObject("asdf2", "FirstName2","LastName", UserType.Entrepreneur, City.Oslo, 42, mutableListOf(Category.Environment),quote1,loremIpsum)
    private val user3 = UserObject("asdf3", "FirstName3","LastName", UserType.Entrepreneur, City.Bergen, 42, mutableListOf(Category.Environment),quote1,loremIpsum)
    private val user4 = UserObject("asdf4", "FirstName4","LastName", UserType.Entrepreneur, City.Trondheim, 42, mutableListOf(Category.Finance),quote1,loremIpsum)
    private val user5 = UserObject("asdf5", "FirstName5","LastName", UserType.Investor, City.Bergen, 42)

    private val userList = mutableListOf(user1,user2,user3,user4,user5)


    private val idea1 = IdeaObject("asdf1",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea2 = IdeaObject("asdf1",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea3 = IdeaObject("asdf1",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea4 = IdeaObject("asdf2",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea5 = IdeaObject("asdf3",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea6 = IdeaObject("asdf4",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)

    private val ideaList = mutableListOf(idea1,idea2,idea3,idea4, idea5,idea6)

    //Database initialization with mock data
    fun populateDatabase(database:AppDatabase,populate:Boolean){
        CoroutineScope(Dispatchers.IO).launch {

            //Initialize DAOs
            val userDao = database.userDAO()
            val ideaDAO = database.ideaDAO()

            // Values to not have to write too many times
            val emptyUserDb = userDao.getAllUsers().isNullOrEmpty()
            val emptyIdeaDb = ideaDAO.getAllIdeas().isNullOrEmpty()

            //If we are supposed to populate the database
            if(populate){
                initializeUserMockData(userDao)
                initializeIdeasMockData(ideaDAO)
            } else {
                // Check, if not empty wipe database
                if(!emptyUserDb || !emptyIdeaDb) Log.d("FOO", "Wiping the database..")
                if(!emptyUserDb) userDao.deleteAllUsers()
                if(!emptyIdeaDb) ideaDAO.deleteAllIdeas()
            }

            // Logs for information
            if(userDao.getAllUsers().isNullOrEmpty()) Log.d("FOO", "Currently 0 users in database")
            else Log.d("FOO", "Currently ${userDao.getAllUsers()?.size} users in database \n ${userDao.getAllUsers()}")

            if(ideaDAO.getAllIdeas().isNullOrEmpty()) Log.d("FOO", "Currently 0 ideas in database")
            else Log.d("FOO", "Currently ${ideaDAO.getAllIdeas()?.size} ideas in database \n ${ideaDAO.getAllIdeas()}")
        }
    }

    // Function to initialize user mock data
    private fun initializeUserMockData(userDao: UserDAO){
        if(userDao.getAllUsers().isNullOrEmpty()){
            Log.d("FOO","Populating users..")
            for(user in userList) {
                userDao.insertUser(user)
            }
            Log.d("FOO", "Added: '${userList.size}' users to the database")
        }
    }

    // Function to initialize idea mock data
    private fun initializeIdeasMockData(ideaDao: IdeaDAO){
        if(ideaDao.getAllIdeas().isNullOrEmpty()){
            Log.d("FOO","Populating ideas..")
            for(idea in ideaList){
                ideaDao.insertIdea(idea)
            }
            Log.d("FOO", "Added: '${ideaList.size}' ideas to the database")
        }
    }

}

