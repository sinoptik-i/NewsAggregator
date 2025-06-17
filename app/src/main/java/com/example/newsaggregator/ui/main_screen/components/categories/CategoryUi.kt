package com.example.newsaggregator.ui.main_screen.components.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
            .padding(3.dp)
            .border(
                1.dp,
                Color.Blue,
                RoundedCornerShape(5.dp)
            )
            .padding(3.dp)
//            .clip(RoundedCornerShape(5.dp))
//            .padding(6.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
//                .fillMaxWidth()
//                .weight(1f)

            text = category,
            color = Color.Blue,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
        )


        if (categoryCount != 0) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        else if (onCancelIconVisible) {
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
