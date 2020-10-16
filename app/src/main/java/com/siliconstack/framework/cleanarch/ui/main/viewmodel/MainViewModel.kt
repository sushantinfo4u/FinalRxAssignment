package com.siliconstack.framework.cleanarch.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siliconstack.framework.cleanarch.data.model.User
import com.siliconstack.framework.cleanarch.data.repository.RemoteRepository
import com.siliconstack.framework.cleanarch.utils.ApiStatus
import com.siliconstack.rxkotlinassignment.data.room.appdatabase.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository:RemoteRepository) : ViewModel() {

    private val users = MutableLiveData<ApiStatus<List<User>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        users.postValue(ApiStatus.loading(null))
        compositeDisposable.add(
            mainRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    users.postValue(ApiStatus.success(userList))
                }, { throwable ->
                    users.postValue(ApiStatus.error("Internet not available", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUsers(): LiveData<ApiStatus<List<User>>> {
        return users
    }


}