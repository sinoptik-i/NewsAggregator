package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerMenuVM @Inject constructor(): ViewModel() {
    val sortCategoryList = listOf(
        "by default",
        "ascending",//vozr
        "descending",//ubiv
    )
    val selectedSortArg=0



}