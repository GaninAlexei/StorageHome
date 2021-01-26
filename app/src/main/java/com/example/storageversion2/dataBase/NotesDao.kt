package com.example.storageversion2.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class NotesDao {
    @Insert
    abstract fun insertNote(note: Note)

    @Query("SELECT name from noteTable ORDER BY id DESC LIMIT 1")
    abstract fun getLastNote() : String
}

