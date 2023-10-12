package com.example.notesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notesapp.data.database.enitites.Note
import io.reactivex.rxjava3.core.Single

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getNotes(): Single<List<Note>>

    @Insert
    fun addNote(note: Note): Single<Unit>

    @Query("DELETE FROM notes where uid = :id")

    fun removeNote(id: Int): Single<Unit>

}