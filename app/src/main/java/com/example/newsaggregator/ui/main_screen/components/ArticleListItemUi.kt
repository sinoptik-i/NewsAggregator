package com.example.newsaggregator.ui.main_screen.components

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
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsaggregator.data.db.Article
import com.example.newsaggregator.ui.main_screen.components.categories.CategoriesUi
import com.example.newsaggregator.ui.main_screen.components.share_button.ShareButton
import com.example.newsaggregator.ui.web_view.WebScreenObject

@Immutable
data class ArticleUi(
    val article: Article,
    val dateText: String,
)


@Composable
fun ArticleListItemUi(
    articleUi: ArticleUi,
    onArticleClick: (webScreenObject: WebScreenObject) -> Unit = {},
    onCategoryClick: (category: String) -> Unit = {}
) {

    val article=articleUi.article
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
//                    model = R.drawable.test_article_image,
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                        text = "date: ${articleUi.dateText}",
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

            ) {
                Spacer(modifier = Modifier.height(5.dp))
                CategoriesUi(
                    article.categories,
                    { it ->
                        onCategoryClick(it)
                    })
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    ShareButton(
                        textToShare = article.link
                    )

                }
            }
        }

    }
}

//@Preview
//@Composable
//fun ArticleListItemUiPreview() {
//    ArticleListItemUi(
//        article = Article(
//            id = 0,
//            title = "title",
//            description = "description",
//            imageUrl = R.drawable.test_article_image.toString(),
//            link = "link",
//            creator = "creator",
//            pubDate = "pubDate",
//            categories = listOf("category", "category", "category"),
//        )
//    )
//}