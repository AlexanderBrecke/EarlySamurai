package com.moonhaven.earlysamurai.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private val _usersLiveData = MutableLiveData<List<UserObject>>()
    val usersLiveData = _usersLiveData

    fun getUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            val allUsers = userRepository.getAllUsersFromDatabase()
            if(_usersLiveData.value != allUsers){
                _usersLiveData.postValue(allUsers)
            }
        }
    }


}