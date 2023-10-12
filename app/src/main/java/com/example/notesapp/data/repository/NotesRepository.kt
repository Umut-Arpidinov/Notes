package com.example.notesapp.data.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.data.database.enitites.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface NotesRepository {

    fun getAllNotes(): Single<List<Note>>

    fun getNoteById(id: Int): Note

    fun saveNote(note: Note): Single<Unit>

    fun deleteNote(id: Int): Single<Unit>

    fun editNote(id: Int)
}