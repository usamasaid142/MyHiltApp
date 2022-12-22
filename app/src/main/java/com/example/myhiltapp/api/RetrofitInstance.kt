package com.example.myhiltapp.api

import com.example.myhiltapp.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

  val api=Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(ApiService::class.java)

   fun getAllActivity()=api.getAllRandomActiviy()

}