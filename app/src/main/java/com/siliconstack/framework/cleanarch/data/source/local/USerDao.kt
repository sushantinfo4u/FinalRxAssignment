package com.siliconstack.framework.cleanarch.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.siliconstack.framework.cleanarch.data.model.User

@Dao
interface USerDao {

    @Query("SELECT * FROM user_table")
    fun getAll(): List<User>

    @Insert
    suspend fun insertAll(users: List<User>)


}