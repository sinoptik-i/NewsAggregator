package com.example.newsaggregator.ui.main_screen.components.drawerMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun RoundedCornerDropDownMenu(
    title: String = "title",
    selected: Int = 0,
    onOptionSelected: (Int) -> Unit = {},
    categoriesList: List<String> = listOf(
        "fantasy", "drama", "bestsell"
    )
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedOption by remember { mutableStateOf(selected) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            fontFamily = FontFamily.Serif
        )
        Box(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(5.dp)
                )
                .clip(shape = RoundedCornerShape(5.dp))
//                .background(Color.White)
                .clickable {
                    expanded = true
                }
                .padding(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(text = categoriesList[selectedOption])

                Icon(
                    imageVector = if (expanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },

                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(),

                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                categoriesList.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                //                            modifier = Modifier
                                //                                .fillMaxWidth(),
                                text = option
                            )
                        },
                        onClick = {
                            selectedOption = categoriesList.indexOf(option)
                            expanded = false
                            onOptionSelected(categoriesList.indexOf(option))
                        },
                    )
                }

            }
        }
    }
}