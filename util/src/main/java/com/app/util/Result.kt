package com.app.util

sealed class Result<out T> {

    class Loading<out T>(val data: T?) : Result<T>()
    data class Successes<out T>(val data: T) : Result<T>()
    data class Error(val errorModel: ErrorModel) : Result<Nothing>()
    object Empty : Result<Nothing>()

    companion object {
        fun <T> loading(data: T? = null) = Loading(data)
        fun <T> successes(data: T) = Successes(data)
        fun error(errorModel: ErrorModel) = Error(errorModel)
        fun empty() = Empty
    }

}