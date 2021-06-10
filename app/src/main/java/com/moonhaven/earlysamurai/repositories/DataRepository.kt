package com.moonhaven.earlysamurai.repositories

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moonhaven.earlysamurai.EarlySamuraiApplication
import com.moonhaven.earlysamurai.database.AppDatabase
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.enums.DataType

class DataRepository {

    // Get hold of DAOs
    private val userDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).userDAO()
    private val ideaDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).ideaDAO()

    // --- Data type specified logic ---

    // Function to get a specified user from the database
    fun getUserWithId(id:String): UserObject {
        return userDao.getUserWithId(id)
    }

    // Function to get all ideas from a specified user from the database
    fun getAllUserIdeas(userId:String):List<IdeaObject>{
        return ideaDao.getAllUserIdeas(userId)
    }

    // --- ---

    // --- Data type abstracted logic ---

    // Function to get all data with the type specified from the database
    fun getAllTypeDataFromDatabase(whatData:DataType): List<*>{
        return when(whatData){
            DataType.USER -> userDao.getAllUsers()
            DataType.IDEA -> ideaDao.getAllIdeas()
        }
    }

    // Function to delete all data with the type specified from the database
    fun deleteAllTypeDataFromDb(whatData: DataType){
        when(whatData){
            DataType.USER -> userDao.deleteAllUsers()
            DataType.IDEA -> ideaDao.deleteAllIdeas()
        }
    }

    // Function to save data with a specified type to the database
    @Suppress("UNCHECKED_CAST")
    fun saveDataToDb(whatData: DataType, newData:List<*>){
        val currentList = getAllTypeDataFromDatabase(whatData)
        when(whatData){
            DataType.USER -> {
                if(newData != currentList){
                    deleteAllTypeDataFromDb(whatData)
                    userDao.insertMultipleUsers(newData as List<UserObject>)
                }
            }
            DataType.IDEA -> {
                if(newData != currentList){
                    deleteAllTypeDataFromDb(whatData)
                    ideaDao.insertMultipleIdeas(newData as List<IdeaObject>)
                }
            }
        }
    }

    // Function to fetch data from an api and serve the data back in a callback
    fun fetchDataFromApi(whatToFetch:DataType, url:String, callback:(List<*>?)->Unit){
        val queue = Volley.newRequestQueue(EarlySamuraiApplication.application.applicationContext)
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val listType = when(whatToFetch){
                DataType.USER -> object : TypeToken<List<UserObject?>?>() {}.type
                DataType.IDEA -> object : TypeToken<List<IdeaObject?>?>() {}.type
            }
            val data = Gson().fromJson<List<*>>(response,listType)
            callback(data)
        }, {
            callback(null)
        })
        queue.add(stringRequest)
    }

    // --- ---

}