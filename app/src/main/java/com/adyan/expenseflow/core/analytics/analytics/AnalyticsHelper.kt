package com.adyan.expenseflow.core.analytics.analytics

interface AnalyticsHelper {
    fun logEvent(event: AnalyticsEvent)
}