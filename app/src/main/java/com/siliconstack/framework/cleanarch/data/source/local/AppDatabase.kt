package com.siliconstack.rxkotlinassignment.data.room.appdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.siliconstack.framework.cleanarch.data.model.User
import com.siliconstack.framework.cleanarch.data.source.local.USerDao

@Database(entities = arrayOf(User::class),version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun movieDao():USerDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}