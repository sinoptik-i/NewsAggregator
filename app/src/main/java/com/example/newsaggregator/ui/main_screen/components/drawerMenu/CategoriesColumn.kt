package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsaggregator.ui.main_screen.components.categories.CategoryForUi
import com.example.newsaggregator.ui.main_screen.components.categories.CategoryItem
import com.example.newsaggregator.ui.main_screen.components.categories.CategoryItemCancelable

//prokinut' categories ili tyt sdelat' vm?
@Composable
fun CategoriesColumn(
    selectedCategories: List<String>,
    onCategorySelected: (String) -> Unit = {},
    onCategoryCanceled: (String) -> Unit = {},
    viewmodel: DrawerMenuVM = hiltViewModel()
) {
    val allCategories by viewmodel.allCategories.collectAsStateWithLifecycle()

    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth(),
    )
    {

        SelectedCategories(
            title = "Selected categories",
            selectedCategories = selectedCategories,
            onCategoryCanceled = { onCategoryCanceled(it) }
        )


//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = Modifier
////                .fillMaxSize()
//            //       .padding(paddingValues)
//        ) {
//            items(selectedCategories) { item ->
//                CategoryItemCancelable(
//                    text = item,
//                    onCategoryClick = {
//                        onCategoryCanceled(it)
//                    }
//                )
//
//            }
//        }


        RoundedCornerTextField(
            text = text,
            label = "category",
            onValueChange = {
                viewmodel.changeSearchText(it)
                text = it
            }
        )


        AllCategories(
            title = " All categories",
            allCategories = allCategories,
            onCategorySelected = { onCategorySelected(it) }
        )

//        LazyColumn {
//            items(allCategories) { item ->
//                CategoryColumnItem(
//                    text = item.category,
//                    categoryCount = item.count,
//                    onCategoryClick = {
//                        onCategorySelected(it)
//                    }

//                    item.category,
//                    item.count,
//                    onCategoryClick = {
//                        onCategorySelected(it)
//                    }
//        )

    }
}


@Preview
@Composable
fun SelectedCategories(
    title: String = "Title",
    selectedCategories: List<String> = emptyList(),
    onCategoryCanceled: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color.Blue,
                shape = RoundedCornerShape(5.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            fontFamily = FontFamily.Serif,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.Start
        ) {
            items(selectedCategories) { item ->
                CategoryItemCancelable(
                    text = item,
                    onCategoryClick = {
                        onCategoryCanceled(it)
                    }
                )

            }
        }
    }
}


@Preview
@Composable
fun AllCategories(
    title: String = "Title",
    allCategories: List<CategoryForUi> = emptyList(),
    onCategorySelected: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color.Blue,
                shape = RoundedCornerShape(5.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            fontFamily = FontFamily.Serif,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.Start
        ) {
            items(allCategories) { item ->
                CategoryColumnItem(
                    text = item.category,
                    categoryCount = item.count,
                    onCategoryClick = {
                        onCategorySelected(it)
                    }
                )

            }
        }
    }
}


@Preview
@Composable
fun CategoryColumnItem(
    text: String = "category",
    categoryCount: Int = 123,
    onCategoryClick: (category: String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clickable {
                onCategoryClick(text)
            }
    ) {
        CategoryItem(
            category = text,
            categoryCount = categoryCount,
            onCancelIconVisible = false,
            //   onCategoryClick = { onCategoryClick(text) },
        )
    }
}