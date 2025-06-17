package com.example.newsaggregator.ui.main_screen.components.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategoriesArticleUi(
    categories: List<String> = listOf(),
    onCategoryClick: (category: String) -> Unit = {}
) {
    LazyRow(
        modifier = Modifier
            .background(Color.White)
    )
    {
        items(categories) { category ->
            CategoryArticleItem(
                category,
                { it ->
                    onCategoryClick(it)
                }
            )

        }

    }
}


@Preview
@Composable
fun CategoriesUiPreview() {
    CategoriesArticleUi(
        categories = listOf("category", "category", "category"),
        onCategoryClick = {}
    )
}

@Preview
@Composable
fun CategoryArticleItem(
    text: String = "category",
    onCategoryClick: (category: String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clickable {
                onCategoryClick(text)
            }
    )
    {
        CategoryItem(
            category = text,
            categoryCount = 0,
            onCancelIconVisible = false,
        )
    }
}

//
//@Composable
//fun CategoriesUi(
//    categories: List<String> = listOf(),
//    onCategoryClick: (category: String) -> Unit = {}
//) {
//    LazyRow(
//        modifier = Modifier
//            .background(Color.White)
//    )
//    {
//        items(categories) { category ->
//            CategoryArticleItem(
//                category,
//                { it ->
//                    onCategoryClick(it)
//                }
//            )
//
//        }
//
//    }
//}
//
//@Preview
//@Composable
//fun CategoriesUiPreview() {
//    CategoriesUi(
//        categories = listOf("category", "category", "category"),
//        onCategoryClick = {}
//    )
//}
//
//
//@Preview
//@Composable
//fun CategoryArticleItem(
//    text: String = "category",
//    onCategoryClick: (category: String) -> Unit = {}
//) {
//    Box(
//        modifier = Modifier
//            .background(Color.White)
//            .padding(3.dp)
//            .border(
//                width = 1.dp,
//                color = Color.Blue,
//                shape = RoundedCornerShape(5.dp)
//            )
//            .padding(3.dp)
//            .clickable {
//                onCategoryClick(text)
//            },
//
//        )
//    {
//        Text(
//            text = text,
//            color = Color.Blue,
////            modifier = Modifier
//        )
//    }
//}
