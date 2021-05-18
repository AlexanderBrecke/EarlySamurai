package com.moonhaven.earlysamurai.database

import androidx.room.*

@Dao interface IdeaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIdea(ideaToInsert:IdeaObject)

    @Delete
    fun deleteIdea(ideaToDelete:IdeaObject)

    @Query("SELECT * FROM idea_table")
    fun getAllIdeas(): List<IdeaObject>?

    @Query("DELETE FROM idea_table")
    fun deleteAllIdeas()

    @Query("SELECT * FROM idea_table WHERE userId LIKE :id")
    fun getAllUserIdeas(id:String): List<IdeaObject>?

    @Query("SELECT * FROM idea_table LIMIT 1")
    fun getIdea(): IdeaObject?

}