package com.moonhaven.earlysamurai.database

import androidx.room.TypeConverter
import com.moonhaven.earlysamurai.enums.Category

// Logic class to convert list of enums to a string as room does not want to save lists
class Converters {

    // Function to change a list of enums to a string
    @TypeConverter
    fun toCategoriesString(values:MutableList<Category>):String{
        // Initialize empty string
        var enumString = ""

        // Loop through the enums in the list
        for(enum in values){
            // Add the name of the enum to the string plus a comma to separate
            enumString += enum.name + ","
        }
        // Return the string
        return enumString
    }

    // Function to change a string back to list of enums
    @TypeConverter
    fun fromCategoriesString(string: String):List<Category>{
        // Get a list of strings by splitting the input string,
        val categoryStrings: List<String> = string.split(",")

        // Initialize an empty list of category enums
        var categoryList:MutableList<Category> = mutableListOf()

        // Loop through the list of strings
        for(category in categoryStrings){
            // If the string is not an empty string, add the category with the value of the string to the list of categories
            if(category != "") categoryList.add(Category.valueOf(category))
        }
        // Return the list of category enums
        return categoryList
    }

}