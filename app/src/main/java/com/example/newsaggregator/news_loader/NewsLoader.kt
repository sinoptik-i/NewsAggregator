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
//            Log.d(TAG, "${item.toArticle().categories}")
        }
//        Log.d(TAG, "${articles[0].categories}")

        return articles
    }

    fun example() {


        runBlocking {
            val r = guardian.getRss()
            val categories = mutableMapOf("tag1" to 1)
            var maxcat = 0
            r.channel.items.forEach { item ->


//                Log.d(TAG, "categories ${item.dcCreator}")
//                Log.d(TAG, "categories ${item.pubDate}")
//
////                categories.map {
////                    it.toString()
////                },
//
//                maxcat = if (item.categories.size > maxcat) item.categories.size else maxcat
//                item.categories.forEach { category ->
//                    if (categories.get(category.value) != null) {
//                        categories.set(category.value, categories.get(category.value)!! + 1)
//                    } else {
//                        categories.set(category.value, 1)
//                    }
//
//                    Log.d(TAG, "categories ${category.value}")
//                }
//                Log.d(TAG, "--------------------------------------------------------------------")
//            }
//            Log.d(TAG, "--------------------------------------------------------------------")
//
//            categories.forEach {
//                Log.d(TAG, "$it")
//            }
//            Log.d(TAG, "$maxcat")

            }
        }
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


//        fun getListArticles() = flow {
//            val articles = mutableListOf<Article>()
//            val r = guardian.getRss()
//            r.channel.items.forEach { item ->
//                articles.add(
//                    Article(
//                        title = item.title,
//                        description = item.description
//                            .replace("<.*?>".toRegex(), ""),
//                        imageUrl = item.contents[1].url,
//                        link = item.link
//                    )
//                )
//            }
//            emit(articles)
//        }


//        fun getArticles() = flow {
//            val r = guardian.getRss()
//            r.channel.items.forEach { item ->
//                emit(
//                    Article(
//                        title = item.title,
//                        description = item.description
//                            .replace("<.*?>".toRegex(), ""),
//                        imageUrl = item.contents[1].url,
//                        link = item.link
//                    )
//                )
//            }
//        }
//
//
//        fun getArticles(
//            scope: CoroutineScope,
//            takeArticles: (List<Article>) -> Unit
//        ) {
//            scope.launch {
//                val articles = mutableListOf<Article>()
//                val r = guardian.getRss()
//                r.channel.items.forEach { item ->
//                    articles.add(
//                        Article(
//                            title = item.title,
//                            description = item.description
//                                .replace("<.*?>".toRegex(), ""),
//                            imageUrl = item.contents[1].url,
//                            link = item.link
//                        )
//                    )
//                }
//                takeArticles(articles)
//            }
//        }
