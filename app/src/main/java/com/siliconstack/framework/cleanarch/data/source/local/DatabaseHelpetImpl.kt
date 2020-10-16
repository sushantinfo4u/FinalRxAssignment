package com.siliconstack.rxkotlinassignment.data.room.appdatabase
import com.siliconstack.framework.cleanarch.data.model.User

class DatabaseHelpetImpl(private val appDatabase: AppDatabase):DatabaseHelper {

    override suspend fun getMovieData(): List<User> =appDatabase.movieDao().getAll()
    override suspend fun saveMovieData(movieData: List<User>) {
        appDatabase.movieDao().insertAll(movieData)
    }
}