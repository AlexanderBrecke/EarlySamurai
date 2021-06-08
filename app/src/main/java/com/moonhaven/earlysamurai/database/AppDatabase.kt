package com.moonhaven.earlysamurai.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// Logic class to create room database. Use Type converters for list of enums
@Database(entities = [UserObject::class, IdeaObject::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase(){

    // Initializing the DAOs
    abstract fun userDAO():UserDAO
    abstract fun ideaDAO():IdeaDAO

    // Companion object to create an instance of the database using a singleton pattern.
    // Create an instance and set it to null
    companion object{
        // Make sure we have an instance and set it to null
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Function to get the database
        // Create build and return an instance of a database object
        // Set the instance above to be the instance of this object
        fun getDatabase(context: Context):AppDatabase{
            return INSTANCE?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}