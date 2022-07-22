package com.app.domain.model

import java.util.*

data class Note(
    val id: String,
    val title: String,
    val text: String,
    val createDate: Date,
    val editDate: Date
)
