package com.moonhaven.earlysamurai.mockdata

import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.City
import com.moonhaven.earlysamurai.enums.IdeaStatus
import com.moonhaven.earlysamurai.enums.UserType

class MockData{

    private val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id sagittis nulla. Ut porta placerat velit, quis malesuada nisi dapibus sit amet. In aliquet ornare sapien, non dignissim ipsum. Vivamus sed sem odio. Proin ultrices, elit non tempor dictum, purus diam maximus turpis, ac congue orci velit non nisl. Maecenas mollis ut risus eget sodales."
    private val loremIpsumShort = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id sagittis nulla."
    private val quote1 = "It's fucked"

    private val user1 = UserObject("asdf1", "asdf1", UserType.Entrepreneur, City.Bergen, 42, mutableListOf(Category.Environment, Category.Finance, Category.Medicine),quote1,loremIpsum)
    private val user2 = UserObject("asdf2", "asdf2", UserType.Entrepreneur, City.Bergen, 42, mutableListOf(Category.Environment),quote1,loremIpsum)
    private val user3 = UserObject("asdf3", "asdf3", UserType.Entrepreneur, City.Bergen, 42, mutableListOf(Category.Environment),quote1,loremIpsum)
    private val user4 = UserObject("asdf4", "asdf4", UserType.Entrepreneur, City.Bergen, 42, mutableListOf(Category.Finance),quote1,loremIpsum)
    private val user5 = UserObject("asdf5", "asdf5", UserType.Investor, City.Bergen, 42)

    val userList = mutableListOf(user1,user2,user3,user4,user5)


    private val idea1 = IdeaObject("asdf1",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea2 = IdeaObject("asdf1",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea3 = IdeaObject("asdf1",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea4 = IdeaObject("asdf2",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea5 = IdeaObject("asdf3",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)
    private val idea6 = IdeaObject("asdf4",Category.Environment,IdeaStatus.ForSale,loremIpsumShort)

    val ideaList = mutableListOf(idea1,idea2,idea3,idea4, idea5,idea6)
}

