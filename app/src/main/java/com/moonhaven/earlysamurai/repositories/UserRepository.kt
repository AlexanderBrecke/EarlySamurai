package com.moonhaven.earlysamurai.repositories

import com.moonhaven.earlysamurai.EarlySamuraiApplication
import com.moonhaven.earlysamurai.database.AppDatabase
import com.moonhaven.earlysamurai.database.UserObject

class UserRepository {

    private val userDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).userDAO()

    fun getAllUsersFromDatabase():List<UserObject>{
         return userDao.getAllUsers()
    }

    fun getUserWithId(id:String):UserObject{
        return userDao.getUserWithId(id)
    }
}