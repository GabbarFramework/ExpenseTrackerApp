package com.peterchege.expensetrackerapp.core.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


fun previousDay(date: String): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val parsedDate = formatter.parse(date)
    val calendar = Calendar.getInstance()
    calendar.time = parsedDate
    calendar.add(Calendar.DATE, -1)
    return formatter.format(calendar.time)
}

fun generate7daysPriorDate(date: String): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val parsedDate = formatter.parse(date)
    val calendar = Calendar.getInstance()
    calendar.time = parsedDate
    calendar.add(Calendar.DATE, -6)
    return formatter.format(calendar.time)
}

fun datesBetween(startDate: String, endDate: String): List<String> {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val start = formatter.parse(startDate)
    val end = formatter.parse(endDate)
    val dates = mutableListOf<String>()
    var current = start

    while (current <= end) {
        dates.add(formatter.format(current))
        val calendar = Calendar.getInstance()
        calendar.time = current
        calendar.add(Calendar.DATE, 1)
        current = calendar.time
    }

    return dates
}

fun firstDayOfMonth(date: String): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val parsedDate = formatter.parse(date)
    val calendar = Calendar.getInstance()
    calendar.time = parsedDate
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    return formatter.format(calendar.time)
}

fun generateFormatDate(date: LocalDate):String{
    var dateCount:String;
    var monthCount:String;
    if (date.dayOfMonth < 10){
        dateCount = "0${date.dayOfMonth}"
    }else{
        dateCount = date.dayOfMonth.toString()
    }
    if (date.monthValue < 10){
        monthCount ="0${date.monthValue}"
    }else{
        monthCount = date.monthValue.toString()
    }
    return "${dateCount}/${monthCount}/${date.year}"

}