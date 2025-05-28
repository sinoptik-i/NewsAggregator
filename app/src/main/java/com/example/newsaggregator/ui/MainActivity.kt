package com.example.newsaggregator.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsaggregator.navigation.Destinations
import com.example.newsaggregator.news_loader.NewsLoader
import com.example.newsaggregator.ui.main_screen.MainScreen
import com.example.newsaggregator.ui.theme.NewsAggregatorTheme
import com.example.newsaggregator.ui.web_view.WebScreenObject
import com.example.newsaggregator.ui.web_view.WebViewScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewsAggregatorTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Destinations.MAIN_SCREEN
                ) {
                    composable(Destinations.MAIN_SCREEN) {
                        MainScreen(
                            modifier = Modifier,
                            onItemClick = { link ->
                                navController.navigate(
                                    WebScreenObject(
                                        link = link
                                    )
                                )
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

//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    WebViewScreen2(
//
//                    MainScreen(
//                        modifier = Modifier.padding(innerPadding),
//                    )


//                    //                    Greeting(
////                        text = "Press me!",
////                        modifier = Modifier.padding(innerPadding),
////                        guardian,
////                    )
//                }
//                }
            }
        }
    }
}



