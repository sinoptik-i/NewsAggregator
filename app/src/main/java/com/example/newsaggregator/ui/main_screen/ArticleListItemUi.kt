package com.example.newsaggregator.ui.main_screen

import android.R.attr.maxLines
import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.newsaggregator.R
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.news_loader.TAG
import com.example.newsaggregator.ui.web_view.WebScreenObject
import com.example.newsaggregator.ui.web_view.WebViewScreen

const val TAG_ArticleListItemUi = "ArticleListItemUi"

@Composable
fun ArticleListItemUi(
    article: Article,
    onArticleClick: (webScreenObject: WebScreenObject) -> Unit = {},
    onCategoryClick: (category: String) -> Unit
) {

    Log.d(TAG_ArticleListItemUi, article.link)
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

//            model = R.drawable.test_article_image,// article.imageUrl,

//        Log.d(TAG_ArticleListItemUi, article.link)
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(16.dp)
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
            Row() {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(16.dp)
//                .clickable {
//                    onArticleClick(
//                        WebScreenObject(
//                            link = article.link
//                        )
//                    )
//                }
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