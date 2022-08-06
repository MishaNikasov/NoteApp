package com.app.util

sealed class ViewState<out T> {

    class Loading<out T>(val data: T?) : ViewState<T>()
    data class Successes<out T>(val data: T) : ViewState<T>()
    data class Error(val errorModel: ErrorModel) : ViewState<Nothing>()
    object Empty : ViewState<Nothing>()

    companion object {
        fun <T> loading(data: T? = null) = Loading(data)
        fun <T> successes(data: T) = Successes(data)
        fun error(errorModel: ErrorModel) = Error(errorModel)
        fun empty() = Empty
    }

}