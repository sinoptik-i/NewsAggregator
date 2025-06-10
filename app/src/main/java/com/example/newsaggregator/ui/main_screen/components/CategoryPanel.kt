package com.example.newsaggregator.ui.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun CategoryPanel(
    category: String = "category",
    onCancelCategoryClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
            .padding(8.dp)
            .border(
                1.dp,
                Color.Black,
                RoundedCornerShape(5.dp)
            )
//            .clip(RoundedCornerShape(5.dp))
            .padding(8.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = category,
            color = Color.Blue,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        IconButton(
            onClick = {
                onCancelCategoryClick()
            }
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = null,
            )
        }

    }

}