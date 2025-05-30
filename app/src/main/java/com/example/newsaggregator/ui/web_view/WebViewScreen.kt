package com.example.newsaggregator.ui.web_view

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsaggregator.ui.main_screen.Failed
import com.example.newsaggregator.ui.main_screen.InProgress
import com.example.newsaggregator.ui.main_screen.Success


@Composable
fun WebViewScreen(
    navData: WebScreenObject = WebScreenObject(),
    viewModel: WebViewScreenVM = hiltViewModel()
) {

    Box {
        val state = viewModel.state.collectAsStateWithLifecycle()
        when (val current = state.value) {
            is Failed -> Button({}) { }
            InProgress -> {
                CircularProgressIndicator()
            }

            is Success<*> -> {
                if (current.isInProgress) {
                    LinearProgressIndicator(modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp))
                }
            }
        }
        AndroidView(
            factory = {
                WebView(it).apply {
                    settings.javaScriptEnabled = true
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            viewModel.onPageStarted()
                        }

                        override fun onReceivedError(
                            view: WebView?,
                            errorCode: Int,
                            description: String?,
                            failingUrl: String?
                        ) {
                            super.onReceivedError(view, errorCode, description, failingUrl)
                        }

                        override fun onReceivedError(
                            view: WebView?,
                            request: WebResourceRequest?,
                            error: WebResourceError?
                        ) {
                            super.onReceivedError(view, request, error)
                        }

                        override fun onReceivedHttpError(
                            view: WebView?,
                            request: WebResourceRequest?,
                            errorResponse: WebResourceResponse?
                        ) {
                            super.onReceivedHttpError(view, request, errorResponse)
                        }

                        override fun onPageFinished(view: WebView?, url: String) {
                            viewModel.onPageFinished(url)
                        }
                    }
                    loadUrl(navData.link)
                }
            }
        )
    }
}
