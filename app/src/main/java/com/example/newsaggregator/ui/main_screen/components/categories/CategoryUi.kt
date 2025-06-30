package com.example.newsaggregator.ui.main_screen.components.categories

import android.R.attr.maxLines
import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


fun truncateString(text: String, maxLength: Int): String {
    return if (text.length > maxLength) {
        text.substring(0, maxLength) + "..."
    } else {
        text
    }
}

@Preview
@Composable
fun CategoryItem(
    category: String = "category",
    categoryCount: Int = 123,
    onCancelIconVisible: Boolean = true,
    onCategoryClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(3.dp)
            .border(
                1.dp,
                Color.Blue,
                RoundedCornerShape(5.dp)
            )
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(

            text = truncateString(category, 15),
            color = Color.Blue,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )


        if (categoryCount != 0) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        } else if (onCancelIconVisible) {
            Spacer(
                modifier = Modifier.width(10.dp)
            )
        }


        if (categoryCount != 0) {
            Text(
//                modifier = Modifier.padding(end = 5.dp),
                color = Color.Blue,
//                fontWeight = FontWeight.Bold,
                text = categoryCount.toString()
            )
        }

        if (onCancelIconVisible) {
            //Color???
            IconButton(
                modifier = Modifier.size(20.dp),
//                    .weight(1f),
                onClick = {
                    onCategoryClick()
                }
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        category = "categorycategorycategorycategorycategorycategorycategorycategorycategory",
        categoryCount = 123,
        onCancelIconVisible = false,
    )
}