package com.example.notesapp.data.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.data.local.database.enitites.Event
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.local.database.enitites.NoteType
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface NotesRepository {

    fun getAllNotes(): Single<List<Note>>

    fun getNotesByType(type: NoteType): Single<List<Note>>

    fun getNoteById(uid: Int): Single<Note>

    fun getNotesByKeyword(keyword: String): Single<List<Note>>

    fun saveNote(note: Note): Single<Unit>

    fun deleteNote(id: Int): Single<Unit>

    fun editNote(note: Note): Single<Unit>

    fun updateNoteType(uid: Int, type: NoteType): Single<Unit>

    fun getAllEvents(): Single<List<Event>>

    fun saveEvent(event: Event): Single<Unit>

    fun deleteEvent(id: Int): Single<Unit>



}