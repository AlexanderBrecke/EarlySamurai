package com.moonhaven.earlysamurai.utilities

import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.IdeaStatus

object Utils {

    // Function to get a string of all categories from a list of categories
    fun getCategoriesString(categoriesList: List<Category>):String{
        var categories = ""
        val loopSize = categoriesList.count()
        for(i in categoriesList.indices){
            categories += categoriesList[i]
            if(i < loopSize-1) categories += ", "
        }
        return categories
    }

    // Function to get the amount of ideas with specified status
    fun getStatusIdeasCount(allIdeas: List<IdeaObject>, statusToLookFor:IdeaStatus):Int{
        var count = 0
        for(idea in allIdeas) if(idea.getStatus() == statusToLookFor) count++
        return count
    }
}