package com.moonhaven.earlysamurai.mockdata

import com.moonhaven.earlysamurai.database.*
import com.moonhaven.earlysamurai.enums.*

// Simply mock data
object MockData{

    // Some mock data to use for our users and ideas
    private const val loremIpsumShort = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id sagittis nulla."
    private const val quote1 = "It's fucked"

    // Some mock users
    private val user1 = UserObject("asdf1", "FirstName1","LastName", UserType.Entrepreneur, City.Bergen, 42, mutableListOf(Category.Environment, Category.Finance, Category.Medicine),quote1,loremIpsumShort)
    private val user2 = UserObject("asdf2", "FirstName2","LastName", UserType.Entrepreneur, City.Oslo, 42, mutableListOf(Category.Environment),quote1,loremIpsumShort)
    private val user3 = UserObject("asdf3", "FirstName3","LastName", UserType.Entrepreneur, City.Bergen, 42, mutableListOf(Category.Environment),quote1,loremIpsumShort)
    private val user4 = UserObject("asdf4", "FirstName4","LastName", UserType.Entrepreneur, City.Trondheim, 42, mutableListOf(Category.Finance),quote1,loremIpsumShort)

    val userList = mutableListOf(user1,user2,user3,user4)

    // Some mock ideas
    private val idea1 = IdeaObject("asdf1",Category.Environment,IdeaStatus.Sold,loremIpsumShort)
    private val idea2 = IdeaObject("asdf1",Category.Finance,IdeaStatus.Sold,loremIpsumShort)
    private val idea3 = IdeaObject("asdf1",Category.Medicine,IdeaStatus.ForSale,loremIpsumShort)
    private val idea4 = IdeaObject("asdf1",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea5 = IdeaObject("asdf2",Category.Environment,IdeaStatus.Sold,loremIpsumShort)
    private val idea6 = IdeaObject("asdf2",Category.Finance,IdeaStatus.ForSale,loremIpsumShort)
    private val idea7 = IdeaObject("asdf3", Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea8 = IdeaObject("asdf4", Category.Environment,IdeaStatus.Sold,loremIpsumShort)

    val ideaList = mutableListOf(idea1,idea2,idea3,idea4, idea5,idea6,idea7,idea8)

}

