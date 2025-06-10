package com.example.newsaggregator.ui.main_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.newsaggregator.data.TimeConverter
import com.example.newsaggregator.data.db.Article
import javax.inject.Inject

interface Mapper<From, To> {
    fun map(from: From): To = from.transform()

    fun map(items: Collection<From>): List<To> = items.map(::map)
    fun From.transform(): To
}

class ArticleToMainUiMapper @Inject constructor(
    private val timeConverter: TimeConverter,
) : Mapper<Article, ArticleUi> {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun Article.transform(): ArticleUi = ArticleUi(
        article = this,
        dateText = timeConverter.deltaDate(pubDate)
    )
}