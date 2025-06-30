package com.example.newsaggregator.news_loader

import android.R.attr.category
import android.util.Log
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.data.rss.dto.ItemDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject


const val TAG = "NewsLoader"

class NewsLoader @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.theguardian.com")
        .addConverterFactory(
            XML.asConverterFactory(
//            MediaType.get("application/xml; charset=UTF8")
                "application/xml; charset=UTF8".toMediaType()
            )
        ).build()

    private val guardian = retrofit.create(RssFeed::class.java)


    suspend fun getListArticles(): MutableList<Article> {
        val articles = mutableListOf<Article>()
        val r = guardian.getRss()
        r.channel.items.forEach { item ->
            articles.add(
                item.toArticle()
            )
        }

        return articles
    }


}


fun ItemDto.toArticle(): Article {
    return Article(
        title = title,
        description = description
            .replace("<.*?>".toRegex(), ""),
        imageUrl = contents[1].url,
        link = link,
        creator = dcCreator,
        pubDate = pubDate,
        categories = categories.map { it ->
            it.value//.toString()
        },
        id = 0
    )
}

