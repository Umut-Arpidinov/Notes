package com.example.notesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.data.local.database.enitites.Event
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.local.database.enitites.NoteType
import io.reactivex.rxjava3.core.Single

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getNotes(): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE type =:type")
    fun getNotesByType(type: NoteType): Single<List<Note>>

    @Insert
    fun addNote(note: Note): Single<Unit>

    @Query("SELECT * FROM notes WHERE uid =:uid ")
    fun getNoteById(uid: Int): Single<Note>

    @Query("DELETE FROM notes where uid = :id")
    fun removeNote(id: Int): Single<Unit>

    @Update
    fun updateNote(note: Note): Single<Unit>

    @Query("UPDATE notes SET type =:type WHERE uid =:uid")
    fun updateNoteType(uid: Int, type: NoteType): Single<Unit>

    @Query("SELECT * FROM notes WHERE text LIKE '%' || :keyword || '%' OR title LIKE '%' || :keyword || '%'")
    fun getNotesByKeyword(keyword: String): Single<List<Note>>

    @Query("SELECT * FROM events")
    fun getEvents(): Single<List<Event>>

    @Insert
    fun addEvent(event: Event): Single<Unit>

    @Query("DELETE FROM events where uid = :id")
    fun removeEvent(id: Int): Single<Unit>




}