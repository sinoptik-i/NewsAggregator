package com.example.newsaggregator.ui.main_screen

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScreenLoaderState {
}
interface ContentLoader<T> {
    val content: Flow<LoadState<T>>


    suspend fun loadContent(): T
    fun syncContent()
}

sealed interface LoadState<out T>

data object InProgress : LoadState<Nothing>
data class Success<T>(
    val data: T,
    val isInProgress: Boolean = false,
    val error: Throwable? = null
) : LoadState<T>

data class Failed(val throwable: Throwable) : LoadState<Nothing>

fun <T> wrapWWithContentState(action: suspend () -> T): Flow<LoadState<T>> = flow {
    emit(InProgress)
    try {
        emit((Success(action())))
    } catch (e: Throwable) {
        emit(Failed(e))
    }
}
