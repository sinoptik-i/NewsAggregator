package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.joda.time.format.ISODateTimeFormat.date
import androidx.compose.runtime.getValue

@Preview
@Composable
fun DrawerBody(
    onOptionSelected: (Int) -> Unit = {},
    viewmodel: DrawerMenuVM = hiltViewModel()
) {
    val allCategories by viewmodel.allCategories.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .fillMaxHeight()
            .background(Color.White),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        RoundedCornerDropDownMenu(
            title = "Sort by date",
            selected = viewmodel.selectedSortArg,
            onOptionSelected = { it ->
                onOptionSelected(it)
            },
            categoriesList = viewmodel.sortCategoryList
        )
        Spacer(modifier = Modifier.height(40.dp))
        CategoriesColumn(
            allCategories

        )


//        Spacer(modifier = Modifier.height(40.dp))
//        ArtDropDownMenu()
    }


}