package com.app.util

interface EventHandler<T> {
    fun obtainEvent(event: T)
}

fun <T, R> invalidEvent(event: T, state: R) {
    throw NotImplementedError("Invalid event: $event for state $state")
}