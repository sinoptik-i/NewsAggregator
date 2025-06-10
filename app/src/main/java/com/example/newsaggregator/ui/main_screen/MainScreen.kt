package com.example.newsaggregator.ui.main_screen

import android.os.Build
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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

import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.newsaggregator.ui.main_screen.components.ArticleUi

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    onItemClick: (webScreenObject: WebScreenObject) -> Unit,
    viewModel: MainScreenVM = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val categoryChoise by viewModel.categoryChoise.collectAsStateWithLifecycle()
    val sortState by viewModel.sortState.collectAsStateWithLifecycle()

//    val pullRefreshState = rememberPullRefreshState (refreshing, { viewModel.refresh() })

//p4m State<Boolean> a ne bool
//    val ptrVisible by viewModel.ptrStateExp.collectAsStateWithLifecycle()
//    val ptrState = rememberPullRefreshState(
//        viewModel.ptrState.collectAsStateWithLifecycle(),
//        { viewModel.pullToRefresh() })

    val ptrVis = remember { mutableStateOf(viewModel.ptrStateExp) }
    val ptrState = rememberPullRefreshState(
        viewModel.ptrStateExp.value,
        { viewModel.pullToRefreshExp() })



    Scaffold(
        modifier = Modifier.pullRefresh(ptrState)
    ) { paddingValues ->



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
        Row(
            modifier = Modifier.fillMaxWidth().padding(top=20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PullRefreshIndicator(
                viewModel.ptrStateExp.value,
                ptrState,

                )
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
    articles: List<ArticleUi>,
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
