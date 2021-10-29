package com.example.flickerdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Flicker")
data class Flicker(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "URL") val url: String,
    @ColumnInfo(name = "Title") val title: String,

)