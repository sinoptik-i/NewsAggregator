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
import com.example.newsaggregator.ui.main_screen.components.ArticleListItemUi
import com.example.newsaggregator.ui.main_screen.components.categories.CategoryItem
import com.example.newsaggregator.ui.web_view.WebScreenObject

import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import com.example.newsaggregator.ui.main_screen.components.ArticleUi
import com.example.newsaggregator.ui.main_screen.components.drawerMenu.DrawerBody

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    onItemClick: (webScreenObject: WebScreenObject) -> Unit,
    viewModel: MainScreenVM = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val categoryChoise by viewModel.categoryChoise.collectAsStateWithLifecycle()
//    val sortState by viewModel.sortState.collectAsStateWithLifecycle()


    val ptrVisible by viewModel.ptrState.collectAsStateWithLifecycle()
    val ptrState = rememberPullRefreshState(
        ptrVisible,
        { viewModel.pullToRefresh() })

    val drawerState = rememberDrawerState(DrawerValue.Open)

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.fillMaxWidth(),
        drawerContent = {
            DrawerBody(
                onOptionSelected = { sortState ->
                    viewModel.changeSortState(sortState)
                },
            )
        }
    )
    {
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

                    categoryChoise?.let {
                        CategoryItem(
                            category = it,
                            categoryCount = 0,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                PullRefreshIndicator(
                    ptrVisible,
                    ptrState,

                    )
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
    articles: List<ArticleUi>,
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

                }
            )
        }

    }
}
