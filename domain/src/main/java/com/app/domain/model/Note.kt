package com.app.domain.model

import java.util.*

data class Note(
    val id: Long,
    val title: String,
    val text: String,
    val createDate: Date,
    val editDate: Date? = null
)
