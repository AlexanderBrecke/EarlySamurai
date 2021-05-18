package com.moonhaven.earlysamurai.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.IdeaStatus

@Entity(tableName = "idea_table")
data class IdeaObject(
    @PrimaryKey(autoGenerate = true)
    private val id:Int,
    private val userId:String,
    private val category: Category,
    private val status: IdeaStatus,
    private val ideaPitch:String) {

    constructor(userId: String, category: Category,status: IdeaStatus,ideaPitch: String):
            this(0,userId,category,status,ideaPitch)

//    fun getUser(userId: String):User{
//        //Logic to return the user with the userId
//    }

    fun getId():Int{
        return id
    }

    fun getUserId():String{
        return userId
    }

    fun getCategory():Category{
        return category
    }

    fun getStatus():IdeaStatus{
        return status
    }

    fun getIdeaPitch():String{
        return ideaPitch
    }

}