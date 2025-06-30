package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue

@Preview
@Composable
fun DrawerBody(
    onSortOptionSelected: (Int) -> Unit = {},

    selectedCats: Collection<String> = emptyList(),
    onCategorySelected: (String) -> Unit = {},
    onCategoryCanceled: (String) -> Unit = {}
) {

    val sortCategoryList = listOf(
        "by default",
        "ascending",//vozr
        "descending",//ubiv
    )

    val selectedSortArg=0

  //  val selectedCategories by remember { mutableStateOf(selectedCats) }
    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .fillMaxHeight()
            .background(Color.White),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        RoundedCornerDropDownMenu(
            title = "Sort by date",
            selected = selectedSortArg,
            onOptionSelected = { it ->
                onSortOptionSelected(it)
            },
            categoriesList = sortCategoryList
        )
        Spacer(modifier = Modifier.height(40.dp))
        CategoriesColumn(
            selectedCategories = selectedCats.toList(),
            onCategorySelected = {
                onCategorySelected(it)
            },
            onCategoryCanceled = {
                onCategoryCanceled(it)
            },
        )
    }


}