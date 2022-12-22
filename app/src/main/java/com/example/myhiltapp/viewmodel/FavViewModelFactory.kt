package com.example.myhiltapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.myhiltapp.repo.FavActREpo

@Suppress("UNREACHABLE_CODE", "UNCHECKED_CAST")
class FavViewModelFactory(private val repo:FavActREpo):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavActivityViewmodel::class.java)){
            return  FavActivityViewmodel(repo) as T
        }
        throw IllegalAccessException(" unknown  view model class ")
    }
}