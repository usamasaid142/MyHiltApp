package com.example.myhiltapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhiltapp.api.RetrofitInstance
import com.example.myhiltapp.model.ResponseActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class RandomActivityviewmodel:ViewModel() {

    private val apiservice=RetrofitInstance()
    private val compositeDisposable=CompositeDisposable()
    val loadrandomActivity=MutableLiveData<Boolean>()
    val randomActivityerror=MutableLiveData<Boolean>()
    val response=MutableLiveData<ResponseActivity>()

    @SuppressLint("SuspiciousIndentation")
    fun getAllRandomActivity(){

        loadrandomActivity.value=true
            compositeDisposable.add(

                apiservice.getAllActivity()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(
                        object :DisposableSingleObserver<ResponseActivity>(){
                            override fun onSuccess(t: ResponseActivity) {
                                loadrandomActivity.value=false
                                response.value=t
                                randomActivityerror.value=false
                            }

                            override fun onError(e: Throwable) {
                                loadrandomActivity.value=false
                                randomActivityerror.value=false
                                e.printStackTrace()
                            }

                        }
                    )

            )
    }


}