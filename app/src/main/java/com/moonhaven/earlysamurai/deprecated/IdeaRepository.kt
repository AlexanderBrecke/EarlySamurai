package com.moonhaven.earlysamurai.deprecated

// DEPRECATED AFTER MIGRATION TO DATA REPOSITORY

//import com.android.volley.Request
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.moonhaven.earlysamurai.EarlySamuraiApplication
//import com.moonhaven.earlysamurai.database.AppDatabase
//import com.moonhaven.earlysamurai.database.IdeaObject
//import com.moonhaven.earlysamurai.database.UserObject
//import java.lang.reflect.Type
//
//// Logic class for idea repository
//class IdeaRepository {
//    // Need to get hold of the idea DAO
//    private val ideaDao = AppDatabase.getDatabase(EarlySamuraiApplication.application.applicationContext).ideaDAO()
//
//    // Function to get all ideas from the database
//    fun getAllIdeasFromDatabase():List<IdeaObject>{
//        return ideaDao.getAllIdeas()
//    }
//
//    // Function to get all ideas from a specified user from the database
//    fun getAllUserIdeas(userId:String):List<IdeaObject>{
//        return ideaDao.getAllUserIdeas(userId)
//    }
//
////    fun fetchIdeasFromApi(callback:(List<IdeaObject>?)->Unit){
////        val url = "$URL/ideas"
////        val queue = Volley.newRequestQueue(EarlySamuraiApplication.application.applicationContext)
////        val stringRequest = StringRequest(Request.Method.GET, url, {
////                response ->
////            val listType: Type = object : TypeToken<List<IdeaObject?>?>() {}.type
////            val ideas = Gson().fromJson<List<IdeaObject>>(response,listType)
////            callback(ideas)
////        }, {
////            callback(null)
////        })
////        queue.add(stringRequest)
////    }
//}