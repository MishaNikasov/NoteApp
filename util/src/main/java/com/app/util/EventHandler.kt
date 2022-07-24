package com.app.util

interface EventHandler<T> {
    fun obtainEvent(event: T)
}