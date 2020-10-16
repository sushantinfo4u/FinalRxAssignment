package com.siliconstack.framework.cleanarch.data.repository

import com.siliconstack.framework.cleanarch.data.source.remote.ApiHelper
import com.siliconstack.framework.cleanarch.data.model.User
import com.siliconstack.rxkotlinassignment.data.room.appdatabase.DatabaseHelper
import io.reactivex.Single

class RemoteRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

}