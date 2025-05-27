package com.example.newsaggregator.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.rss.Article
import com.example.newsaggregator.news_loader.NewsLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenVM @Inject constructor() : ViewModel() {

    val newsLoader = NewsLoader()

    fun getTestArticles(): MutableList<Article> {
        val articles = mutableListOf<Article>()
        for (i in 1..10) {
            articles.add(
                Article(
                    title = "title $i",
                    description = "description $i",
                    imageUrl = "TODO()",
                    link = ""
                )
            )
        }
        return articles
    }

    fun getArticles(
        takeArticles: (List<Article>) -> Unit
    ) {
        newsLoader.getArticles(
            scope = viewModelScope,
            takeArticles = { it ->
                takeArticles(it)
            }
        )

    }
}