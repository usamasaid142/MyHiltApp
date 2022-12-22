package com.example.myhiltapp.repo

import com.example.myhiltapp.db.FavActDatabase
import com.example.myhiltapp.model.ResponseActivity
import kotlinx.coroutines.flow.Flow

class FavActREpo(private val db:FavActDatabase) {

    suspend fun insert(responseActivity: ResponseActivity)=db.favDao().insert(responseActivity)

    suspend fun delete(responseActivity: ResponseActivity)=db.favDao().delete(responseActivity)

   val getAllactivity: Flow<List<ResponseActivity>> = db.favDao().getAllFavourite()

}