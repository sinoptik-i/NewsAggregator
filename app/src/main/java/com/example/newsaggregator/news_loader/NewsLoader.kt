package com.example.newsaggregator.news_loader

import android.util.Log
import com.example.newsaggregator.data.rss.Article
import com.example.newsaggregator.data.rss.RssFeed
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


const val TAG = "NewsLoader"

class NewsLoader {




    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.theguardian.com")
        .addConverterFactory(
            XML.asConverterFactory(
//            MediaType.get("application/xml; charset=UTF8")
                "application/xml; charset=UTF8".toMediaType()
            )
        ).build()

    private val guardian = retrofit.create(RssFeed::class.java)

    fun getArticles(
        scope: CoroutineScope,
        takeArticles: (List<Article>) -> Unit
    ) {
        scope.launch {
            val articles = mutableListOf<Article>()
            val r = guardian.getRss()
            r.channel.items.forEach { item ->
                articles.add(
                    Article(
                        title = item.title,
                        description = item.description
                            .replace("<.*?>".toRegex(), ""),
                        imageUrl = item.contents[1].url,
                        link = item .link
                    )
                )
            }
            takeArticles(articles)
        }
    }


    fun example() {
        Log.d("happy", "done")

//        scope.launch {
        runBlocking {
            val r = guardian.getRss()
            r.channel.items.forEach { item ->
//                Log.d(TAG, "link ${it.title},")// ${it.description}, ${it.link}")
//                Log.d(TAG, "guid ${it.description}")
//                Log.d(TAG, "dcDate ${it.guid}")
//                Log.d(TAG, "pubDate $it.pubDate")
//                Log.d(TAG, "link ${it.link}")
//                Log.d(TAG, "title ${it.title}")
//                Log.d(TAG, "dcDate ${it.dcDate}")
//                Log.d(TAG, "categories ${it.categories}")

//                item.contents[0].url
                Log.d(TAG, "categories ${item.guid}")

//                item.contents.forEach { pic ->
//                    Log.d(TAG, "contents ${pic.url}")
//                }
//                Log.d(TAG, "pubDate ${it.pubDate}")
//                Log.d(TAG, "dcCreator ${it.dcCreator}")
                Log.d(
                    TAG,
                    "--------------------------------------------------------------------"
                )


            }
        }
    }


}