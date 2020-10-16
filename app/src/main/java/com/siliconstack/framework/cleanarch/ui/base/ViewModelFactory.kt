package com.siliconstack.framework.cleanarch.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.siliconstack.framework.cleanarch.data.source.remote.ApiHelper
import com.siliconstack.framework.cleanarch.data.repository.RemoteRepository
import com.siliconstack.framework.cleanarch.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(RemoteRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}