package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.db.ArticlesRepository
import com.example.newsaggregator.ui.main_screen.extensions.categories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DrawerMenuVM @Inject constructor(
    val repository: ArticlesRepository,
): ViewModel() {
    val sortCategoryList = listOf(
        "by default",
        "ascending",//vozr
        "descending",//ubiv
    )
    val selectedSortArg=0

    val allCategories=repository.getArticlesFlow()
        .categories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


}