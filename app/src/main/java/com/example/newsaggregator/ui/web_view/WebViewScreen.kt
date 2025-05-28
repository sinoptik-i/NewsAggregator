package com.example.newsaggregator.ui.web_view

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

//@Composable
//fun WebViewScreen(
//    modifier: Modifier,
//    link: String
//) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        AndroidView(factory = {
//            WebView(it).apply {
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                )
//            }
//        }, update = {
//            val HTMLstring =
//                "<!DOCTYPE html><html><head><style>html, body { margin: 0; padding: 0; height: 100%; }</style></head><body><div style='height: 100%; background-color: green;'></div></body><html>"
//            it.loadDataWithBaseURL(link, HTMLstring, "text/html", "utf-8", null)
//        })
//    }
//}

@Composable
fun WebViewScreen(
    modifier: Modifier = Modifier,
    navData: WebScreenObject = WebScreenObject()
) {
    AndroidView(factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true // Включите JavaScript, если нужно
            webViewClient = WebViewClient() // Замените на вашу реализацию WebViewClient
            loadUrl(navData.link) // Загрузите URL
        }
    })
}
