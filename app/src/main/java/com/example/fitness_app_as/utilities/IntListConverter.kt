package com.example.fitness_app_as.utilities

import androidx.room.TypeConverter

class IntListConverter {
    @TypeConverter
    fun intListToString(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun StringToIntList(data: String): List<Int> {
        if (data.isEmpty()) return emptyList()
        return data.split(",").map { it.toInt() }
    }
}