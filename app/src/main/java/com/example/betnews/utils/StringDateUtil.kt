package com.example.betnews.utils

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss"

fun String.convertDate(patern: String = DATE_FORMAT_1): String {
    var spf = SimpleDateFormat(patern)
    val newDate: Date = spf.parse(this)
    spf = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())
    return spf.format(newDate)
}