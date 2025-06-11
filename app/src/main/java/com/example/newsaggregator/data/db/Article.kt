package com.example.newsaggregator.data.db

import android.annotation.SuppressLint
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Calendar


@Entity(tableName = "articles")
@TypeConverters(StringListConverter::class)
data class Article(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "imageUrl") val imageUrl: String = "",
    @ColumnInfo(name = "link") val link: String = "",
    @ColumnInfo(name = "creator") val creator: String = "",
    @ColumnInfo(name = "pubDate") val pubDate: String = "",
    @ColumnInfo(name = "categories") val categories: List<String> = emptyList<String>()
)
//    : Comparable<Article> {
//    //    override fun compareTo(other: Person): Int = age - other.age
//    override fun compareTo(other: Article): Int {
//        TODO("Not yet implemented")
//    }
//}

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)//:Long

    @Query("DELETE FROM articles")
    suspend fun dropAll()

    @Transaction
    suspend fun setArticles(articles: List<Article>) {
        dropAll()
        insertArticles(articles)
    }

}