package com.app.util

interface EventHandler<T: ScreenEvent> {
    fun obtainEvent(event: T)
}

fun <T: ScreenEvent, R: ViewState<*>> invalidEvent(event: T, result: R) {
    throw NotImplementedError("Invalid event: $event for state $result")
}