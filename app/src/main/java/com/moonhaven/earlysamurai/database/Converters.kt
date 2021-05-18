package com.moonhaven.earlysamurai.database

import android.util.Log
import androidx.room.TypeConverter
import com.moonhaven.earlysamurai.enums.Category

class Converters {

    @TypeConverter
    fun toCategoriesString(values:MutableList<Category>):String{
        var categoriesString = ""

        for(category in values){
            categoriesString += category.name + ","
        }
        return categoriesString
    }


    @TypeConverter
    fun fromCategoriesString(string: String):List<Category>{
        val categoryStrings: List<String> = string.split(",")
        var categoryList:MutableList<Category> = mutableListOf()

        for(category in categoryStrings){
            if(category != "") categoryList.add(Category.valueOf(category))
        }
        return categoryList
    }

    @TypeConverter
    fun fromIdListToString(values:List<Int>):String{
        var idString = ""
        for(id in values){
            idString += "$id,"
        }
        return idString
    }

    @TypeConverter
    fun fromIdStringToList(string: String):List<Int>{
        val idStrings: List<String> = string.split(",")
        val idList: MutableList<Int> = mutableListOf()
        for(id in idStrings){
            if(id != "") idList.add(id.toInt())
        }
        return idList
    }
}