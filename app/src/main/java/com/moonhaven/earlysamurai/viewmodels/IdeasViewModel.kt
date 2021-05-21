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
    private val ideaRepository = IdeaRepository()
    private val _ideasLiveData = MutableLiveData<List<IdeaObject>>()
    val ideasLiveData = _ideasLiveData

    private val _currentUserIdeasLiveData = MutableLiveData<List<IdeaObject>>()
    val userIdeasLiveData = _currentUserIdeasLiveData

    fun getIdeas(){
        CoroutineScope(Dispatchers.IO).launch {
            val allIdeas = ideaRepository.getAllIdeasFromDatabase()
            if(_ideasLiveData.value != allIdeas){
                _ideasLiveData.postValue(allIdeas)
            }
        }
    }

    fun getUserIdeas(userId:String){
        CoroutineScope(Dispatchers.IO).launch {
            val allUserIdeas = ideaRepository.getAllUserIdeas(userId)
            if(_currentUserIdeasLiveData.value != allUserIdeas){
                _currentUserIdeasLiveData.postValue(allUserIdeas)
            }
        }
    }

}