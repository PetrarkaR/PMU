package com.example.embeddedLogger.DB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "store_table")
data class Store(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: String,
    val price: Double
)