package com.example.newsaggregator.workmanager

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.jvm.java

class LoadArticleManager @Inject constructor(
    val workManager: WorkManager
) {

    private val uniquePeriodicWork = "loadArticles"

    fun startPeriodicLoadData() {
        val weatherPeriodReq = PeriodicWorkRequest.Builder(
            LoadArticlesWorker::class.java,
            3,
            TimeUnit.HOURS,
            2,
            TimeUnit.HOURS
        )
//            .setInitialDelay(
//                2,
//                //Calendar.getInstance().minutesToNextWeatherRequest().toLong(),
//                TimeUnit.MINUTES
//            )
            .build()


        workManager.enqueueUniquePeriodicWork(
            uniquePeriodicWork,
            ExistingPeriodicWorkPolicy.KEEP,
            weatherPeriodReq
        )
    }

    fun stop(){
        workManager.cancelUniqueWork(uniquePeriodicWork)
    }

}