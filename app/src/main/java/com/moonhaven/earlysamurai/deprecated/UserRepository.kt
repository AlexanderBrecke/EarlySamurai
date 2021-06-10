package com.moonhaven.earlysamurai.deprecated

// DEPRECATED AFTER MIGRATION TO DATA REPOSITORY

//import com.android.volley.Request
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.moonhaven.earlysamurai.EarlySamuraiApplication
//import com.moonhaven.earlysamurai.URL
//import com.moonhaven.earlysamurai.database.AppDatabase
//import com.moonhaven.earlysamurai.database.UserObject
//import com.moonhaven.earlysamurai.userMockUrl
//import java.lang.reflect.Type
//
//// Logic class for the user repository
//class UserRepository {
//
//    // Get hold of the user DAO
//    private val userDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).userDAO()
//
//    // Function to get all users from the database
//    fun getAllUsersFromDatabase():List<UserObject>{
//         return userDao.getAllUsers()
//    }
//
//    // Function to get a specified user from the database
//    fun getUserWithId(id:String):UserObject{
//        return userDao.getUserWithId(id)
//    }
//
//    fun fetchUsersFromApi(callback:(List<UserObject>?)->Unit){
//        val url = userMockUrl
//        val queue = Volley.newRequestQueue(EarlySamuraiApplication.application.applicationContext)
//        val stringRequest = StringRequest(Request.Method.GET, url, {
//            response ->
//            val listType: Type = object : TypeToken<List<UserObject?>?>() {}.type
//            val users = Gson().fromJson<List<UserObject>>(response,listType)
//            callback(users)
//        }, {
//            callback(null)
//        })
//        queue.add(stringRequest)
//    }
//
//    fun saveUsersToDb(newList:List<UserObject>){
//        val currentList = getAllUsersFromDatabase()
//        if(newList != currentList){
//            userDao.deleteAllUsers()
//            userDao.insertMultipleUsers(newList)
//        }
//
//    }
//}