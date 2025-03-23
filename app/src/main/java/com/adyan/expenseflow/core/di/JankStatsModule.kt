package com.adyan.expenseflow.core.di

import android.app.Activity
import android.util.Log
import android.view.Window
import androidx.metrics.performance.JankStats
import com.adyan.expenseflow.core.analytics.analytics.FirebaseAnalyticsHelper
import com.adyan.expenseflow.core.analytics.analytics.logJankyFrames
import com.adyan.expenseflow.core.util.ProfileVerifierLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

@Module
@InstallIn(ActivityComponent::class)
object JankStatsModule {


    @Provides
    fun providesOnFrameListener(
        analyticsHelper: FirebaseAnalyticsHelper,
    ): JankStats.OnFrameListener {
        return JankStats.OnFrameListener { frameData ->
            // Make sure to only log janky frames.
            if (frameData.isJank) {
                Timber.tag("App Jank").v(frameData.toString())
                analyticsHelper.logJankyFrames(frameData)
            }
        }
    }

    @Provides
    fun providesWindow(activity: Activity): Window {
        return activity.window
    }

    @Provides
    fun providesJankStats(
        window: Window,
        frameListener: JankStats.OnFrameListener,
    ): JankStats {
        return JankStats.createAndTrack(window, frameListener)
    }
}
