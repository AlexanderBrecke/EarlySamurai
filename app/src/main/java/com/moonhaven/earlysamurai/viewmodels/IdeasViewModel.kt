package com.moonhaven.earlysamurai.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.repositories.IdeaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch

class IdeasViewModel:ViewModel() {
    // Setup the idea repository
    private val ideaRepository = IdeaRepository()

    // Setup live data for the user ideas
    private val _currentUserIdeasLiveData = MutableLiveData<List<IdeaObject>>()
    val userIdeasLiveData = _currentUserIdeasLiveData

    // Function to get all ideas from a user
    fun getUserIdeas(userId:String){
        CoroutineScope(Dispatchers.IO).launch {
            val allUserIdeas = ideaRepository.getAllUserIdeas(userId)
            if(_currentUserIdeasLiveData.value != allUserIdeas){
                _currentUserIdeasLiveData.postValue(allUserIdeas)
            }
        }
    }

}