package com.example.newsaggregator.ui.main_screen

import android.R.attr.contentDescription
import android.os.Build
import android.util.Log
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsaggregator.data.TimeConverter
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.ui.main_screen.components.ArticleListItemUi
import com.example.newsaggregator.ui.main_screen.components.CategoryPanel
import com.example.newsaggregator.ui.web_view.WebScreenObject


@Composable
fun MainScreen(
    onItemClick: (webScreenObject: WebScreenObject) -> Unit,
    viewModel: MainScreenVM = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val categoryChoise by viewModel.categoryChoise.collectAsStateWithLifecycle()
    val sortState by viewModel.sortState.collectAsStateWithLifecycle()

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        )
        {
            Spacer(modifier = Modifier.width(5.dp))
            Row {
//                IconButton(
//                    onClick = {
//                        viewModel.sort()
//                    }
//                ) {
//                    Icon(
//                        if (sortState) {
//                            Icons.Default.KeyboardArrowDown
//                        } else {
//                            Icons.Default.KeyboardArrowUp
//                        },
//                        contentDescription = null,
//                    )
//                }

                categoryChoise?.let {
                    CategoryPanel(
                        category = it,
                        onCancelCategoryClick = { viewModel.clearCategory() }
                    )
                }
            }
            Box {
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ContentArticles(
    articles: List<Article>,
    onItemClick: (webScreenObject: WebScreenObject) -> Unit,
    onCategoryClick: (category: String) -> Unit

) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        val timeConverter = TimeConverter()
        items(articles) { item ->
//            Log.d("timeTest", timeConverter.timeFromString(item.pubDate))
            ArticleListItemUi(
                item,
                onArticleClick = { it ->
                    onItemClick(it)
                },
                onCategoryClick = { it ->
                    onCategoryClick(it)

                }
            )
        }

    }
}
