package se.umu.alro0113.trackandbet.common.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

// Formats a long date into a smaller date
fun convertToMyDate(longDate : String?) : String{
    if (longDate.isNullOrBlank()) {
        return ""
    }
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
    val dateTime = ZonedDateTime.parse(longDate, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("d MMM")
    return dateTime.format(outputFormatter)
}