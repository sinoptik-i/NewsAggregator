package com.example.newsaggregator.ui.main_screen.components.share_button

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@Composable
fun ShareButton(
    textToShare: String = "",
    viewmodel: ShareButtonVM = hiltViewModel()
) {
    val context = LocalContext.current


    IconButton(
        onClick = {
//            viewmodel.shareArticle(textToShare)
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, textToShare)
            }
            try {
                context.startActivity(shareIntent)
            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка: Нет приложений для обмена", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = null,
//            tint = LocalContentColors
        )
    }

}