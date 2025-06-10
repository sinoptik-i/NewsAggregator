package com.example.newsaggregator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsaggregator.navigation.Destinations
import com.example.newsaggregator.ui.main_screen.MainScreen
import com.example.newsaggregator.ui.main_screen.components.drawerMenu.DrawerBody
import com.example.newsaggregator.ui.main_screen.components.share_button.ShareButton
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
//            DrawerBody()


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




