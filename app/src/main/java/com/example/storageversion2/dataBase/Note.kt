package com.example.storageversion2.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteTable")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)