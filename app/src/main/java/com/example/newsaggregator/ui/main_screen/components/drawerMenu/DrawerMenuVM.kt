package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.db.ArticlesRepository
import com.example.newsaggregator.ui.main_screen.extensions.categories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DrawerMenuVM @Inject constructor(
    repository: ArticlesRepository,
) : ViewModel() {

    private val searchText = MutableStateFlow("")

    fun changeSearchText(text: String) {
        searchText.value = text
    }

    val allCategories = repository.getArticlesFlow()
        .categories()
        .combine(searchText) { categories, text ->
            if (text != "") {
                categories.filter { category ->
                    category.category.contains(text,ignoreCase = true)
                }
            } else {
                categories
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


}