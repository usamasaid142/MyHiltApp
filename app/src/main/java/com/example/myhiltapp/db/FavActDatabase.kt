package com.example.myhiltapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myhiltapp.model.ResponseActivity

@Database(entities = [ResponseActivity::class], version = 1, exportSchema = false)
 abstract class FavActDatabase:RoomDatabase() {

      abstract fun favDao():DaoFAV

      companion object{

          @Volatile
          private var Instance:FavActDatabase?=null

          fun getDatabase(context: Context):FavActDatabase{

              return Instance?: synchronized(this){

                  val instance= Room.databaseBuilder(context.applicationContext,FavActDatabase::class.java,"db_act")
                      .fallbackToDestructiveMigration()
                      .build()
                  Instance=instance
                  instance
              }
          }
      }

}