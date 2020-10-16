package com.siliconstack.framework.cleanarch.data.source.remote

class ApiHelper(private val apiService: ApiService) {

    fun getUsers() = apiService.getUsers()

}