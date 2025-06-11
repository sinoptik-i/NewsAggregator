package com.example.newsaggregator.ui.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.data.db.ArticlesRepository
import com.example.newsaggregator.ui.main_screen.components.ArticleToMainUiMapper
import com.example.newsaggregator.ui.main_screen.extensions.categoryFilter
import com.example.newsaggregator.ui.main_screen.extensions.dateSort
import com.example.newsaggregator.ui.main_screen.extensions.loadStateTransmit
import com.example.newsaggregator.ui.main_screen.extensions.mapData
import com.example.newsaggregator.workmanager.LoadArticleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenVM @Inject constructor(
    val repository: ArticlesRepository,
    private val uiMapper: ArticleToMainUiMapper,
    workManager: LoadArticleManager,
) : ViewModel() {


    //category
    //-------------------------------------------------------------------------------------------
    private val categoryState: MutableStateFlow<String> = MutableStateFlow("")

    val categoryChoise = categoryState.map { it.takeIf { it.isNotEmpty() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)


    fun changeCategory(category: String) {
        categoryState.value = category
    }

    fun clearCategory() {
        categoryState.value = ""
    }

    //sort
    //-------------------------------------------------------------------------------------------
   private val sortState: MutableStateFlow<Int> = MutableStateFlow(0)
    fun changeSortState(sortSt: Int){
        sortState.value=sortSt
    }



    //articles
//-------------------------------------------------------------------------------------------
    private val syncState: MutableStateFlow<LoadState<List<Article>>> =
        MutableStateFlow(InProgress)

    @RequiresApi(Build.VERSION_CODES.O)
    val state = repository.getArticlesFlow()
        .categoryFilter(categoryState)
        .dateSort(sortState)
        .loadStateTransmit(syncState)
        .mapData(uiMapper::map)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), InProgress)

    //pullToRefresh
    //-------------------------------------------------------------------------------------------
    val ptrState = syncState.map { it ->
        when (it) {
            InProgress -> true
            else -> false
        }

    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)

    fun pullToRefresh() {
        loadContent()
    }

//    //pullToRefresh experiment
//    //-------------------------------------------------------------------------------------------
//    var ptrStateExp = mutableStateOf(false)
//
//    fun pullToRefreshExp() {
//        viewModelScope.launch {
//            ptrStateExp.value = true
//            delay(2000)
//            ptrStateExp.value = false
//        }
//    }

    init {
        loadContent()
        workManager.startPeriodicLoadData()
        addCloseable { workManager.stop() }
    }


    fun loadContent() {
        wrapWWithContentState {
            repository.syncArticles()
        }
            .onEach(syncState::emit)
            .launchIn(viewModelScope)
    }
}

