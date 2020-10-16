package com.siliconstack.framework.cleanarch.data.source.remote

import com.siliconstack.framework.cleanarch.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}