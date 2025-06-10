package com.example.newsaggregator.ui.main_screen.components.share_button

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ShareButtonVM @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {


//    val context = LocalContext.current

    fun shareArticle(textToShare: String){
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, textToShare)
        }
        try {
            context.startActivity(shareIntent)
        } catch (e: Exception) {
            Log.e("EEE","error",e)
            Toast.makeText(context, "Ошибка: Нет приложений для обмена", Toast.LENGTH_SHORT)
                .show()
        }
    }


}