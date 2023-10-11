package com.example.notesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notesapp.data.database.enitites.Note

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getNotes(): List<Note>

    @Insert
    fun addNote(note: Note)

    @Query("DELETE FROM notes where uid = :id")

    fun removeNote(id: Int)

}