package com.example.newsaggregator.ui.main_screen.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.ui.web_view.WebScreenObject


@Composable
fun ArticleListItemUi(
    article: Article,
    onArticleClick: (webScreenObject: WebScreenObject) -> Unit = {},
    onCategoryClick: (category: String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = Color.Blue,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    onArticleClick(
                        WebScreenObject(
                            link = article.link
                        )
                    )
                }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onArticleClick(
                            WebScreenObject(
                                link = article.link
                            )
                        )
                    }
            ) {
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(15.dp)),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = article.title,
                    color = Color.Black,
                    fontSize = 20.sp,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = article.description,
                    color = Color.Gray,
                    fontSize = 16.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(  modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "creator: ${article.creator}",
                        color = Color.Gray,
                        fontSize = 10.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,

                        )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "date: ${article.pubDate}",
                        color = Color.Gray,
                        fontSize = 10.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,

                        )
                }

            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Spacer(modifier = Modifier.height(5.dp))
                CategoriesUi(
                    article.categories,
                    { it ->
                        onCategoryClick(it)
                    })
            }
        }

    }
}