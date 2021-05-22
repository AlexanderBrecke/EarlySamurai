package com.moonhaven.earlysamurai.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.City
import com.moonhaven.earlysamurai.enums.UserType
import kotlinx.android.parcel.Parcelize

// Parcelized data entity for user object
@Parcelize
@Entity(tableName = "user_table")
data class UserObject(
    @PrimaryKey
    private val id:String,
    private var firstName:String,
    private var lastName:String,
    private val userType:UserType,
    private var city:City,
    private var age:Int,
    private var categories:MutableList<Category> = mutableListOf(),
    private var quote:String? = null,
    private var pitch:String? = null,
    private var image:String? = null,
    private var credibility:Double = 0.0
):Parcelable {

    // --- Functions to get the values from the object as values are private ---
        // Same as IdeaObject, here we would normally have functions to set values if they should be able to change.

    fun getId():String{
        return id
    }

    fun getFirstName():String{
        return firstName
    }

    fun getLastName():String{
        return lastName
    }

    fun getUserType():UserType{
        return userType
    }

    fun getCity():City{
        return city
    }

    fun getAge():Int{
        return age
    }

    fun getCategories():MutableList<Category>{
        return categories
    }

    fun getQuote():String?{
        return quote
    }

    fun getPitch():String?{
        return pitch
    }

    fun getImage():String?{
        return image
    }

    fun getCredibility():Double{
        return credibility
    }

    // --- ---

}