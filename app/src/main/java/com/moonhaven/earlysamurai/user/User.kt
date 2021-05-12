package com.moonhaven.earlysamurai.user

import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.City
import com.moonhaven.earlysamurai.enums.UserType

class User(
    private var name:String,
    private val userType:UserType,
    private var city:City,
    private var age:Int,
    private var category:Category,
    private var quote:String? = null,
    private var pitch:String? = null,

    private var image:String? = null
    ) {

    private val credibility:Double = 0.0
    private val ideas:MutableList<String> = mutableListOf()

    fun getName():String{
        return name
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

    fun getCredibility():Double{
        return credibility
    }

    fun getCategory():Category{
        return category
    }

    fun getIdeas():MutableList<String>{
        return ideas
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

}