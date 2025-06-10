package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerBody() {
    val sortCategoryList = listOf(
        "by default",
        "ascending",//vozr
        "descending",//ubiv
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedOption by remember { mutableStateOf(sortCategoryList[0]) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .clickable {
                expanded = true
            }
    )
    {
        Text(text = selectedOption)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            sortCategoryList.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedOption = option
                    },
                    text = {
                        Text(text = option)
                    }
                )
            }
        }
    }

}