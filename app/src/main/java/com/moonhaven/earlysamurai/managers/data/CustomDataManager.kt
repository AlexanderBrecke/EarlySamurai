package com.moonhaven.earlysamurai.managers.data

import android.util.Log
import com.moonhaven.earlysamurai.enums.DataType
import com.moonhaven.earlysamurai.ideaMockUrl
import com.moonhaven.earlysamurai.mockdata.MockData
import com.moonhaven.earlysamurai.repositories.DataRepository
import com.moonhaven.earlysamurai.userMockUrl
import com.moonhaven.earlysamurai.utilities.Utils

// A custom data manager to handle populating the database
object CustomDataManager {

    // Function to populate the database
    fun populateDatabase(populate:Boolean){
        Utils.runFunctionAsCoroutine {
            val repo = DataRepository()

            val emptyUserDb = repo.getAllTypeDataFromDatabase(DataType.USER).isNullOrEmpty()
            val emptyIdeaDb = repo.getAllTypeDataFromDatabase(DataType.IDEA).isNullOrEmpty()

            //If we are supposed to populate the database
            if(populate){
                initializeData(DataType.USER,userMockUrl)
                initializeData(DataType.IDEA,ideaMockUrl)
            } else {
                // Check, if not empty wipe database
                if(!emptyUserDb || !emptyIdeaDb) Log.d("FOO", "Wiping the database..")
                if(!emptyUserDb) repo.deleteAllTypeDataFromDb(DataType.USER)
                if(!emptyIdeaDb) repo.deleteAllTypeDataFromDb(DataType.IDEA)
            }

            // Logs for information
            if(emptyUserDb) Log.d("FOO", "Currently 0 users in database")
            else Log.d("FOO", "Currently ${repo.getAllTypeDataFromDatabase(DataType.USER).size} users in database \n ${repo.getAllTypeDataFromDatabase(DataType.USER)}")

            if(emptyIdeaDb) Log.d("FOO", "Currently 0 ideas in database")
            else Log.d("FOO", "Currently ${repo.getAllTypeDataFromDatabase(DataType.IDEA).size} ideas in database \n ${repo.getAllTypeDataFromDatabase(DataType.IDEA)}")
        }
    }


    // Function to initialize the data
    // Takes a data type to input, and an api url to fetch the data from
    // Setup values for things we will use
    // Check if the database is null or empty
    // If it is, fetch data from the api and save it to the database
    // If api cannot be reached, save data from mock data object instead
    private fun initializeData(whatData: DataType, url:String){
        val repo = DataRepository()
        val typeString = "${whatData.name.toLowerCase()}s"
        val mockUserList = MockData.userList
        val mockIdeaList = MockData.ideaList

        if(repo.getAllTypeDataFromDatabase(whatData).isNullOrEmpty()){
            Log.d("FOO","Populating $typeString..")
            repo.fetchDataFromApi(whatData, url) {
                it?.let {
                    Utils.runFunctionAsCoroutine {
                        repo.saveDataToDb(whatData,it)
                        Log.d("FOO", "Added: '${it.size}' $typeString to the database")
                    }
                } ?: run{
                    Utils.runFunctionAsCoroutine {
                        Log.d("FOO","Could not fetch $typeString from api.. Populating from mock data")
                        when (whatData) {
                            DataType.USER ->{
                                repo.saveDataToDb(whatData,mockUserList)
                                Log.d("FOO", "Added: '${mockUserList.size}' $typeString to the database")
                            }
                            DataType.IDEA ->{
                                repo.saveDataToDb(whatData, mockIdeaList)
                                Log.d("FOO", "Added: '${mockIdeaList.size}' $typeString to the database")
                            }
                        }
                    }
                }
            }
        }
    }
}