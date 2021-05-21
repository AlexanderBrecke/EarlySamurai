package com.moonhaven.earlysamurai.repositories

import com.moonhaven.earlysamurai.EarlySamuraiApplication
import com.moonhaven.earlysamurai.database.AppDatabase
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.database.UserObject

class IdeaRepository {
    private val ideaDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).ideaDAO()

    fun getAllIdeasFromDatabase():List<IdeaObject>{
        return ideaDao.getAllIdeas()
    }

    fun getAllUserIdeas(userId:String):List<IdeaObject>{
        return ideaDao.getAllUserIdeas(userId)
    }
}