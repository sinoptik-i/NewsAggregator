package com.example.newsaggregator.data.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

class StringListConverter {
    @TypeConverter
    fun fromString(string: String): List<String> = if(string.isEmpty()) emptyList() else string.split('|')

    @TypeConverter
    fun fromList(list:List<String>): String = list.joinToString(separator = "|")
}