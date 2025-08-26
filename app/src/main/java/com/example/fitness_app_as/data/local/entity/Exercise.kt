package com.example.fitness_app_as.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "primary_muscle") val primaryMuscle: String,
    @ColumnInfo(name = "secondary_muscle") val secondaryMuscle: String,
    @ColumnInfo(name = "equipment") val equipment: String,
    @ColumnInfo(name = "description") val description: String
)