package com.example.newsaggregator.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Locale

@Preview
@Composable
fun CategoriesUi(
    categories: List<String> = listOf("asdasd", "zxczxc"),
    onCategoryClick: (category: String) -> Unit={}
) {
    LazyRow {
        items(categories) { category ->
            CategoryItem(
                category,
                {it->
                    onCategoryClick(it) }
            )

        }

    }
}


@Preview
@Composable
fun CategoryItem(
    text: String = "category",
    onCategoryClick: (category: String) -> Unit={}
) {
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .padding(3.dp)
//            .clip(RoundedCornerShape(3.dp))
            .border(
                width = 1.dp,
//                brush = Brush.,
                color = Color.Black
            )
            .padding(3.dp)
            .clickable {
                onCategoryClick(text)
            },

        )
    {
        Text(
            text = text,
            modifier = Modifier
//                .clip(RoundedCornerShape(15.dp)),
        )
    }
}