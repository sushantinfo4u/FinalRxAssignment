package com.siliconstack.rxkotlinassignment.data.room.appdatabase
import com.siliconstack.framework.cleanarch.data.model.User

interface DatabaseHelper {

    suspend fun getMovieData():List<User>
    suspend fun saveMovieData(movieData:List<User>)
}