package com.siliconstack.framework.cleanarch.domain

import com.siliconstack.framework.cleanarch.data.model.User
import com.siliconstack.framework.cleanarch.data.repository.RemoteRepository
import io.reactivex.Single

class UseCase(private val mainRepository: RemoteRepository) {

    fun getDataFromServer() : Single<List<User>>{
        return mainRepository.getUsers()
    }

}