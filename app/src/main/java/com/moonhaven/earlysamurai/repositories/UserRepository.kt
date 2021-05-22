package com.moonhaven.earlysamurai.repositories

import com.moonhaven.earlysamurai.EarlySamuraiApplication
import com.moonhaven.earlysamurai.database.AppDatabase
import com.moonhaven.earlysamurai.database.UserObject

// Logic class for the user repository
class UserRepository {

    // Get hold of the user DAO
    private val userDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).userDAO()

    // Function to get all users from the database
    fun getAllUsersFromDatabase():List<UserObject>{
         return userDao.getAllUsers()
    }

    // Function to get a specified user from the database
    fun getUserWithId(id:String):UserObject{
        return userDao.getUserWithId(id)
    }
}