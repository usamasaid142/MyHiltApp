package com.example.myhiltapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhiltapp.api.ApiService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch


class HiltViewmodel:ViewModel() {

    val apiservice=CompositeDisposable()
    fun login()=viewModelScope.launch {

    }
}