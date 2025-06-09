package com.example.newsaggregator.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.intl.Locale
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsaggregator.navigation.Destinations
import com.example.newsaggregator.ui.main_screen.MainScreen
import com.example.newsaggregator.ui.theme.NewsAggregatorTheme
import com.example.newsaggregator.ui.web_view.WebScreenObject
import com.example.newsaggregator.ui.web_view.WebViewScreen
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.Calendar
import java.util.Date
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val newsLoader = NewsLoader()
//        newsLoader.example()

//        testTime()
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




