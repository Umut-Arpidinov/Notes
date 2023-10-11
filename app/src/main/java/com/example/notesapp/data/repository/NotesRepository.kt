package com.example.notesapp.data.repository

import com.example.notesapp.data.database.enitites.Note


interface NotesRepository {

    fun getAllNotes(): List<Note>

    fun getNoteById(id: Int): Note

    fun saveNote(note: Note)

    fun editNote(id: Int)
}