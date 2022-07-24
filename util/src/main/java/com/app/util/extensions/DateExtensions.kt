package com.app.util.extensions

import java.text.SimpleDateFormat
import java.util.*


const val FULL_DATE_WITH_TIME = "d MMM yyyy, HH:mm"
const val PATTERN_CLIENT = "dd/MMM/yyyy"

fun Date.byPattern(pattern: String = FULL_DATE_WITH_TIME): String {
    val formattedDate: String

    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    formattedDate = simpleDateFormat.format(this)

    return formattedDate
}