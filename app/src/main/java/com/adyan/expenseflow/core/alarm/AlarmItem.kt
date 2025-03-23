package com.adyan.expenseflow.core.alarm

import java.time.LocalDateTime

data class AlarmItem(
    val time:LocalDateTime,
    val message:String
)