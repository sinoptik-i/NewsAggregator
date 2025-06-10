package com.example.newsaggregator.ui.main_screen.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.newsaggregator.data.TimeConverter
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.ui.main_screen.Failed
import com.example.newsaggregator.ui.main_screen.InProgress
import com.example.newsaggregator.ui.main_screen.LoadState
import com.example.newsaggregator.ui.main_screen.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlin.text.isNotEmpty

fun Flow<List<Article>>.categoryFilter(category: MutableStateFlow<String>) =
    combine(category) { content, category ->
        if (category.isNotEmpty()) {
            content.filter { it ->
                it.categories.contains(category)
            }
        } else {
            content
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
fun Flow<List<Article>>.convertDateForView(
//     timeConverter: TimeConverter
): Flow<List<Article>> {
    val timeConverter = TimeConverter()
    return map { articles ->
        articles.map { article ->
            article.copy(
                pubDate = timeConverter.deltaDate(article.pubDate)
            )
        }
    }
}

fun Flow<List<Article>>.loadStateTransmit(
    syncState: MutableStateFlow<LoadState<List<Article>>>
) = combine(syncState) { content, syncState ->
        when {
            content.isEmpty() -> syncState
            else -> when (syncState) {
                is Failed -> Success(content, error = syncState.throwable)

                InProgress -> Success(content, isInProgress = true)
                is Success<*> -> Success(content)
            }
        }
    }

fun <From, To> LoadState<From>.mapData(mapper: (From) -> To): LoadState<To> = when (this) {
    is Failed -> this
    InProgress -> InProgress
    is Success -> Success(data = mapper(data), isInProgress = isInProgress, error = error)
}

fun <From, To> Flow<LoadState<From>>.mapData(mapper: (From) -> To): Flow<LoadState<To>> =
    map { it.mapData(mapper) }

//
//fun categoryFilter(
//    articles: Flow<List<Article>>,
//    category: MutableStateFlow<String>
//): Flow<List<Article>> {
//    return articles.combine(category) { content, category ->
//        if (category.isNotEmpty()) {
//            content.filter { it ->
//                it.categories.contains(category)
//            }
//        } else {
//            content
//        }
//
//    }
//}