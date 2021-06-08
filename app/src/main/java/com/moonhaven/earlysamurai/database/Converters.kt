package com.moonhaven.earlysamurai.database

import androidx.room.TypeConverter
import com.moonhaven.earlysamurai.enums.Category

// Logic class to convert list of enums to a string as room does not want to save lists
class Converters {

    // Function to change a list of enums to a string
    // Loop through enums in the list and add the name of the enum to a string plus a comma to separate
    // Return the string
    @TypeConverter
    fun toCategoriesString(values:MutableList<Category>):String{
        var enumString = ""

        for(enum in values){
            enumString += enum.name + ","
        }
        return enumString
    }

    // Function to change a string back to list of enums
    // Split to get a list of strings
    // Loop through the strings and check if it is not empty as this can happen
    // if it isn't, add the category with the value of the string to a list of categories
    // return list of categories
    @TypeConverter
    fun fromCategoriesString(string: String):List<Category>{
        val categoryStrings: List<String> = string.split(",")
        var categoryList:MutableList<Category> = mutableListOf()

        for(category in categoryStrings){
            if(category != "") categoryList.add(Category.valueOf(category))
        }
        return categoryList
    }

}