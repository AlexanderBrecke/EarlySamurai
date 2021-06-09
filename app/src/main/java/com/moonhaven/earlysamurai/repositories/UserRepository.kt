package com.moonhaven.earlysamurai.repositories

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moonhaven.earlysamurai.EarlySamuraiApplication
import com.moonhaven.earlysamurai.URL
import com.moonhaven.earlysamurai.database.AppDatabase
import com.moonhaven.earlysamurai.database.UserObject
import java.lang.reflect.Type

// Logic class for the user repository
class UserRepository {

    private val baseUrl = URL

    // Get hold of the user DAO
    private val userDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).userDAO()

    // Function to get all users from the database
    fun getAllUsersFromDatabase():List<UserObject>{
         return userDao.getAllUsers()
    }

    // Function to get a specified user from the database
    fun getUserWithId(id:String):UserObject{
        return userDao.getUserWithId(id)
    }

    fun fetchUsers(callback:(List<UserObject>?)->Unit){
        val url = "$baseUrl/asdf?userList='foo'"
        val queue = Volley.newRequestQueue(EarlySamuraiApplication.application.applicationContext)
        val stringRequest = StringRequest(Request.Method.GET, url, {
            response ->
            val listType: Type = object : TypeToken<List<UserObject?>?>() {}.type
            val users = Gson().fromJson<List<UserObject>>(response,listType)
            callback(users)
        }, {
            callback(null)
        })
        queue.add(stringRequest)
    }
}