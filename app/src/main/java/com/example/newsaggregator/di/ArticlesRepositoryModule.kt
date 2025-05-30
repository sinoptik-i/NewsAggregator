package com.example.newsaggregator.di

import android.app.Application
import android.content.Context
import androidx.room.withTransaction
import com.example.newsaggregator.data.db.ArticleDao
import com.example.newsaggregator.data.db.ArticleDb
import com.example.newsaggregator.data.db.ArticlesRepository
import com.example.newsaggregator.data.db.ArticlesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object ArticlesRepositoryModule {

    @Provides
    fun providesArticlesDao(
        @ApplicationContext context: Context
    ): ArticleDao = ArticleDb.getDatabase(context).dao()
//        .also {
//        ArticleDb.getDatabase(context).withTransaction {
//            it.dropAll()
//            it.setArticles()
//        }
//    }


    @Provides
    fun providesArticlesRepo(impl: ArticlesRepositoryImpl): ArticlesRepository = impl
}