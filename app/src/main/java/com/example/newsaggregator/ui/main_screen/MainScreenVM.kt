package com.example.newsaggregator.ui.main_screen

import android.R.attr.category
import android.text.TextUtils.isEmpty
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.data.db.ArticlesRepository
import com.example.newsaggregator.data.db.ArticlesRepositoryImpl
import com.example.newsaggregator.news_loader.NewsLoader
import com.example.newsaggregator.workmanager.LoadArticleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainScreenVM @Inject constructor(
    val repository: ArticlesRepository,
    workManager: LoadArticleManager
) : ViewModel() {


     val categoryState: MutableStateFlow<String> = MutableStateFlow("")

    //    val categoryChoise= categoryState.isEmpty() .asStateFlow()
//    val categoryChoise = categoryState.map {
//        it.isEmpty()
//    }
//        .stateIn(viewModelScope,SharingStarted.WhileSubscribed(),False.)

    fun getCat() = categoryState


    fun changeCategory(category: String) {
        categoryState.value = category
    }

    fun clearCategory() {
        categoryState.value = ""
    }


    private val syncState: MutableStateFlow<LoadState<List<Article>>> =
        MutableStateFlow(InProgress)
    val state = repository.getArticlesFlow()
        .combine(categoryState) { content, category ->
            if (category.isNotEmpty()) {
                content.filter { it ->
                    it.categories.contains(category)
                }
            } else {
                content
            }

        }
        .combine(syncState) { content, syncState ->
            when {
                content.isEmpty() -> syncState
                else -> when (syncState) {
                    is Failed -> Success(content, error = syncState.throwable)
                    InProgress -> Success(content, isInProgress = true)
                    is Success<*> -> Success(content)
                }
            }

        }

        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), InProgress)

    init {
        loadContent()
        workManager.startPeriodicLoadData()

    }

    fun loadContent() {
        wrapWWithContentState {
            repository.syncArticles()
        }
            .onEach(syncState::emit)
            .launchIn(viewModelScope)
    }
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