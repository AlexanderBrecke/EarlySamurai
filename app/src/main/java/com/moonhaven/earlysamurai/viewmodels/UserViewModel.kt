package com.moonhaven.earlysamurai.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.enums.DataType
import com.moonhaven.earlysamurai.repositories.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    // Setup the repository and live data for all users
    private val repo = DataRepository()
    private val _usersLiveData = MutableLiveData<List<UserObject>>()
    val usersLiveData = _usersLiveData

    // Function to get all the users from the repository
    fun getUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            val allUsers = repo.getAllTypeDataFromDatabase(DataType.USER) as List<UserObject>
            if(_usersLiveData.value != allUsers){
                _usersLiveData.postValue(allUsers)
            }
        }
    }


}