package com.example.newsaggregator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsaggregator.data.db.ArticleDao
import com.example.newsaggregator.data.db.ArticlesRepository
import com.example.newsaggregator.navigation.Destinations
import com.example.newsaggregator.news_loader.NewsLoader
import com.example.newsaggregator.ui.main_screen.MainScreen
import com.example.newsaggregator.ui.theme.NewsAggregatorTheme
import com.example.newsaggregator.ui.web_view.WebScreenObject
import com.example.newsaggregator.ui.web_view.WebViewScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var dao: ArticleDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsLoader = NewsLoader()
        newsLoader.example()

        setContent {
            NewsAggregatorTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destinations.MAIN_SCREEN
                ) {
                    composable(Destinations.MAIN_SCREEN) {
                        MainScreen(
                            onItemClick = { it ->
                                navController.navigate(it)
                            }
                        )
                    }
                    composable<WebScreenObject> { navEntry ->
                        val navData = navEntry.toRoute<WebScreenObject>()
                        WebViewScreen(
                            navData = navData
                        )
                    }
                }
            }
        }
    }
}



