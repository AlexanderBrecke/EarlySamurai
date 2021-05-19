package com.moonhaven.earlysamurai.database

import androidx.room.*

@Dao interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(userToInsert:UserObject)

    @Delete
    fun deleteUser(userToDelete:UserObject)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<UserObject>

    @Query("SELECT * FROM user_table LIMIT 1")
    fun getUser():UserObject?

    @Query("SELECT * FROM user_table WHERE id LIKE :userId")
    fun getUserWithId(userId:String):UserObject

}