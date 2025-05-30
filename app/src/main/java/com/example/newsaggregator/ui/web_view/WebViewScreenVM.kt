package com.example.newsaggregator.ui.web_view

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.newsaggregator.ui.main_screen.InProgress
import com.example.newsaggregator.ui.main_screen.LoadState
import com.example.newsaggregator.ui.main_screen.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WebViewScreenVM @Inject constructor() : ViewModel() {
    private val currentContent get() = (state.value as? Success)?.data

    fun onPageStarted() {
        val content = currentContent
        if (content == null) {
            state.value = InProgress
        } else {
            state.value = Success(content, isInProgress = true)
        }
    }

    fun onPageFinished(url: String) {
        state.value = Success(url)
    }


    val state: MutableStateFlow<LoadState<String>> =
        MutableStateFlow(InProgress)

}