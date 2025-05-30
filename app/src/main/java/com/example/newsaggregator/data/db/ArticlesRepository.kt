package com.example.newsaggregator.data.db

import com.example.newsaggregator.news_loader.NewsLoader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton


interface ArticlesRepository {
    fun getArticlesFlow(): Flow<List<Article>>

    suspend fun setArticles(articles: List<Article>)//: Long
    suspend fun syncArticles(): List<Article>
}

@Singleton
class ArticlesRepositoryImpl @Inject constructor(
    val dao: ArticleDao,
    val newsLoader: NewsLoader
) : ArticlesRepository {

    override fun getArticlesFlow(): Flow<List<Article>> = dao.getArticles()
        .distinctUntilChanged()


    override suspend fun setArticles(articles: List<Article>) = dao.setArticles(articles)
    override suspend fun syncArticles(): List<Article> {
        val articles = newsLoader.getListArticles()
        setArticles(articles)
        return articles
    }


}
