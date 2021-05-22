package com.moonhaven.earlysamurai.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.IdeaStatus

// Data entity for idea objects
@Entity(tableName = "idea_table")
data class IdeaObject(
    @PrimaryKey(autoGenerate = true)
    private val id:Int,
    private val userId:String,
    private val category: Category,
    private var status: IdeaStatus,
    private val ideaPitch:String) {

    // Constructor so we don't have to supply the id when creating the object
    constructor(userId: String, category: Category,status: IdeaStatus,ideaPitch: String):
            this(0,userId,category,status,ideaPitch)


    // --- Functions to get values as they have been set to private ---
        // Here we would also have functions to set the values if they needed to be changed.
        // This is outside the scope of this assignment

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

    // --- ---

}