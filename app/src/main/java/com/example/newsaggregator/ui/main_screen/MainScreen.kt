package com.example.newsaggregator.ui.main_screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsaggregator.data.rss.Article
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


const val TAG="MainScreen"

@Composable
fun MainScreen(
    modifier: Modifier,
    mainScreenVM: MainScreenVM = hiltViewModel(),
) {
    var articles by remember { mutableStateOf(emptyList<Article>()) }

    mainScreenVM.getArticles(
        takeArticles = { it ->
            articles = it
        }
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {


        items(articles) { item ->
            ArticleListItemUi(item)
            Log.d(TAG,"${item.imageUrl}")
        }

    }

}