package com.app.util

sealed class State<out T> {

    class Loading<out T>(val data: T?) : State<T>()
    data class Successes<out T>(val data: T) : State<T>()
    data class Error(val errorModel: ErrorModel) : State<Nothing>()
    object Empty : State<Nothing>()

    companion object {
        fun <T> loading(data: T? = null) = Loading(data)
        fun <T> successes(data: T) = Successes(data)
        fun error(errorModel: ErrorModel) = Error(errorModel)
        fun empty() = Empty
    }

}