package com.moonhaven.earlysamurai.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.repositories.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IdeasViewModel:ViewModel() {

    // Setup the repository and live data for the user ideas
    private val repository = DataRepository()

    private val _currentUserIdeasLiveData = MutableLiveData<List<IdeaObject>>()
    val userIdeasLiveData = _currentUserIdeasLiveData

    // Function to get all ideas from a user
    fun getUserIdeas(userId:String){
        CoroutineScope(Dispatchers.IO).launch {
            val allUserIdeas = repository.getAllUserIdeas(userId)
            if(_currentUserIdeasLiveData.value != allUserIdeas){
                _currentUserIdeasLiveData.postValue(allUserIdeas)
            }
        }
    }

}