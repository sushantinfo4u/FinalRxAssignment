package com.siliconstack.framework.cleanarch.utils

data class ApiStatus<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): ApiStatus<T> {
            return ApiStatus(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ApiStatus<T> {
            return ApiStatus(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ApiStatus<T> {
            return ApiStatus(Status.LOADING, data, null)
        }

    }

}