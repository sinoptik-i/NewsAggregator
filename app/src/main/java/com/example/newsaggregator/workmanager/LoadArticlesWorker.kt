package com.example.newsaggregator.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.newsaggregator.data.db.ArticlesRepository
import javax.inject.Inject

class LoadArticlesWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    @Inject
    lateinit var repository: ArticlesRepository

    override suspend fun doWork(): Result {
        try {

            repository.syncArticles()
            return Result.success()
        } catch (ex: Exception) {
            return Result.failure()
        }
    }


}