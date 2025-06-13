package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsaggregator.ui.main_screen.components.categories.CategoryForUi
import com.example.newsaggregator.ui.main_screen.components.categories.CategoryItem

//prokinut' categories ili tyt sdelat' vm?
@Composable
fun CategoriesColumn(
    categoriesUi:List<CategoryForUi>,
    onValueChange: (String) -> Unit = {}
) {

    val text = "text"
    Column(
        modifier = Modifier.fillMaxWidth(),
    )
    {
        TextField(
            value = text,
            onValueChange = {
                onValueChange(text)
            }
        )
        LazyColumn {
            items(categoriesUi){item->
                CategoryColumnItem(
                    item.category,
                    item.count
                )

            }
        }
    }
}

@Preview
@Composable
fun CategoryColumnItem(
    text: String = "category",
    categoryCount:Int=123,
    onCategoryClick: (category: String) -> Unit = {}
){
    CategoryItem(
        category = text,
        categoryCount = categoryCount,
        onCancelIconVisible = false,
        onCancelCategoryClick = { onCategoryClick(text) },
    )

}