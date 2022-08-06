package com.app.util

sealed class DataState<out R> {
    data class Success<out T>(val data: T?) : DataState<T>()
    data class Error(val errorModel: ErrorModel) : DataState<Nothing>()

    companion object {
        fun <T> successes(data: T?) = Success(data)
        fun error(errorModel: ErrorModel) = Error(errorModel)
    }

    fun toState() = when (this) {
        is Success -> ViewState.successes(this.data)
        is Error -> ViewState.error(this.errorModel)
    }

    suspend fun getResult(
        successes: suspend (R?) -> Unit,
        error: suspend ((ErrorModel) -> Unit)
    ) {
        when (this) {
            is Success -> successes(data)
            is Error -> error(errorModel)
        }
    }

}