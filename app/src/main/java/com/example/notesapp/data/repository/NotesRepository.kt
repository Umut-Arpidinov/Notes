package com.example.notesapp.data.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.data.local.database.enitites.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface NotesRepository {

    fun getAllNotes(): Single<List<Note>>

    fun getNoteById(uid: Int): Single<Note>

    fun saveNote(note: Note): Single<Unit>

    fun deleteNote(id: Int): Single<Unit>

    fun editNote(note: Note): Single<Unit>
}