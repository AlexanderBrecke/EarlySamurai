package com.moonhaven.earlysamurai.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moonhaven.earlysamurai.enums.Category
import com.moonhaven.earlysamurai.enums.City
import com.moonhaven.earlysamurai.enums.IdeaStatus
import com.moonhaven.earlysamurai.enums.UserType


@Database(entities = [UserObject::class, IdeaObject::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase(){

    abstract fun userDAO():UserDAO
    abstract fun ideaDAO():IdeaDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

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