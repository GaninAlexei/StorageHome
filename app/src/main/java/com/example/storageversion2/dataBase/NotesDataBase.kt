package com.example.storageversion2.dataBase

import androidx.room.*

@Database(entities = [Note::class], version = 1)
abstract class NotesDataBase: RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}