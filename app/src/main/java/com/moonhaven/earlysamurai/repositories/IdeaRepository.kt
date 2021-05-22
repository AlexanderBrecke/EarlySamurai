package com.moonhaven.earlysamurai.repositories

import com.moonhaven.earlysamurai.EarlySamuraiApplication
import com.moonhaven.earlysamurai.database.AppDatabase
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.database.UserObject

// Logic class for idea repository
class IdeaRepository {
    // Need to get hold of the idea DAO
    private val ideaDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).ideaDAO()

    // Function to get all ideas from the database
    fun getAllIdeasFromDatabase():List<IdeaObject>{
        return ideaDao.getAllIdeas()
    }

    // Function to get all ideas from a specified user from the database
    fun getAllUserIdeas(userId:String):List<IdeaObject>{
        return ideaDao.getAllUserIdeas(userId)
    }
}