package com.example.myhiltapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myhiltapp.model.ResponseActivity
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoFAV {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insert(responseActivity: ResponseActivity)

    @Delete
    suspend fun delete(responseActivity: ResponseActivity)

    @Query( "select * from ResponseActivity ORDER by id DESC")
    fun getAllFavourite():Flow<List<ResponseActivity>>

}