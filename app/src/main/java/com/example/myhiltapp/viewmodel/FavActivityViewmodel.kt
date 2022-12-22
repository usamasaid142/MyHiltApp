package com.example.myhiltapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myhiltapp.model.ResponseActivity
import com.example.myhiltapp.repo.FavActREpo
import kotlinx.coroutines.launch

class FavActivityViewmodel(private val repo:FavActREpo):ViewModel() {

    fun insert(responseActivity: ResponseActivity)=viewModelScope.launch {
        repo.insert(responseActivity)
    }


    fun delete(responseActivity: ResponseActivity)=viewModelScope.launch {
        repo.delete(responseActivity)
    }

    val allactivities:LiveData<List<ResponseActivity>> = repo.getAllactivity.asLiveData()

}