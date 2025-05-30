package com.example.newsaggregator.ui.main_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.ui.web_view.WebScreenObject


const val TAG = "MainScreen"

@Composable
fun MainScreen(
    onItemClick: (webScreenObject: WebScreenObject) -> Unit,
    viewModel: MainScreenVM = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
//    val categoryChoise by viewModel.categoryChoise.collectAsStateWithLifecycle()
    val categoryState by viewModel.categoryState.collectAsStateWithLifecycle()

    Column()
    {
        if (categoryState.isNotEmpty()) {
            CategoryPanel(
                category = categoryState,
                onCancelCategoryClick = { viewModel.clearCategory() }
            )
        }
        Box {

            Spacer(modifier = Modifier.width(5.dp))
            when (val current = state) {
                is Success -> {
                    ContentArticles(
                        articles = current.data,
                        onItemClick = { it ->
                            onItemClick(it)
                        },
                        onCategoryClick = { it ->
                            viewModel.changeCategory(it)
                        },
                    )
                }

                is Failed -> {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        onClick = viewModel::loadContent
                    ) {
                        Text("Ошибка. Повторить попытку.")
                    }
                }

                is InProgress -> {
                    ProgressBar()
                }
            }
        }
    }
}


@Composable
fun ProgressBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    )
    {

        CircularProgressIndicator(
            modifier = Modifier
                .size(56.dp)
        )
    }
}

@Composable
private fun ContentArticles(
    articles: List<Article>,
    onItemClick: (webScreenObject: WebScreenObject) -> Unit,
    onCategoryClick: (category: String) -> Unit

) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(articles) { item ->
            ArticleListItemUi(
                item,
                onArticleClick = { it ->
                    onItemClick(it)
                },
                onCategoryClick = { it ->
                    onCategoryClick(it)

                }//item.link) }
            )
            Log.d(TAG, "${item.imageUrl}")
        }

    }
}

//    var articles by remember { mutableStateOf(emptyList<Article>()) }
//    var articles = viewModel.getListArticles()
//        .collectAsState(initial = emptyList<Article>())

//    mainScreenVM.getArticles(
//        takeArticles = { it ->
//            articles = it
//        }
//    )
