package com.example.notesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.data.local.database.enitites.Note
import io.reactivex.rxjava3.core.Single

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getNotes(): Single<List<Note>>

    @Insert
    fun addNote(note: Note): Single<Unit>

    @Query("SELECT * FROM notes WHERE uid =:uid ")
    fun getNoteById(uid: Int): Single<Note>

    @Query("DELETE FROM notes where uid = :id")
    fun removeNote(id: Int): Single<Unit>

    @Update
    fun updateNote(note: Note): Single<Unit>

}