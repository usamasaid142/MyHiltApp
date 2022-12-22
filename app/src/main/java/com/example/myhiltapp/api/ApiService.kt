package com.example.myhiltapp.api

import com.example.myhiltapp.model.ResponseActivity
import com.example.myhiltapp.utils.Constants.BASE_URL
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("activity/")
    fun getAllRandomActiviy():Single<ResponseActivity>
}