package com.adyan.expenseflow.core.di

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.adyan.expenseflow.BuildConfig
import com.adyan.expenseflow.core.alarm.AlarmItem
import com.adyan.expenseflow.core.alarm.AndroidAlarmScheduler
import com.adyan.expenseflow.core.analytics.crashlytics.CrashlyticsTree
import com.adyan.expenseflow.core.util.ProfileVerifierLogger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.time.LocalDateTime
import javax.inject.Inject

@HiltAndroidApp
class ExpenseTrackerApp :Application(){
//    lateinit var scheduler: AndroidAlarmScheduler

    @Inject
    lateinit var profileVerifierLogger: ProfileVerifierLogger
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate() {
        super.onCreate()
        profileVerifierLogger()
        initTimber()
//        scheduler = AndroidAlarmScheduler(this)
//        scheduler.schedule(AlarmItem(LocalDateTime.now(),""))
    }
}

private fun initTimber() = when {
    BuildConfig.DEBUG -> {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })
    }
    else -> {
        Timber.plant(CrashlyticsTree())
    }
}