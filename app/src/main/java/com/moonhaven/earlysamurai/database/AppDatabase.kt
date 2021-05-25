package com.moonhaven.earlysamurai.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.City
import com.moonhaven.earlysamurai.enums.IdeaStatus
import com.moonhaven.earlysamurai.enums.UserType

// Logic class to create room database. Use Type converters for list of enums
@Database(entities = [UserObject::class, IdeaObject::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase(){

    // Initializing the DAOs
    abstract fun userDAO():UserDAO
    abstract fun ideaDAO():IdeaDAO

    // Companion object to create an instance of the database using a singleton pattern.
    // This was taken from SmallTalk. I am documenting this for my own understanding.
    companion object{
        // Make sure we have an instance and set it to null
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Function to get the database
        fun getDatabase(context: Context):AppDatabase{
            // Create, build and return an instance of a database object.
            // Make sure we set the instance this instance
            return INSTANCE?: synchronized(this) {
                // Create the instance
                val instance = androidx.room.Room.databaseBuilder(
                    // Give it the context
                    context.applicationContext,
                    // Define what database class to use
                    AppDatabase::class.java,
                    // Give the database a name
                    "app_database"
                //Build the database
                ).build()
                // Set the instance here
                INSTANCE = instance
                // Return the instance
                instance
            }
        }
    }


}