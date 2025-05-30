package com.example.newsaggregator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java


@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = true
)
abstract class ArticleDb : RoomDatabase() {
    abstract fun dao(): ArticleDao


    companion object {
        private var INSTANCE: ArticleDb? = null

        fun getDatabase(context: Context): ArticleDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ArticleDb::class.java,
                    "article_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}