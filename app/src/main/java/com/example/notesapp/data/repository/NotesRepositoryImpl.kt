package com.example.notesapp.data.repository


import android.util.Log
import com.example.notesapp.data.database.NotesRoomDatabase
import com.example.notesapp.data.database.enitites.Note
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor (
    private val notesDB: NotesRoomDatabase
) : NotesRepository {
    override fun getAllNotes(): List<Note> {
        return listOf()
    }

    override fun getNoteById(id: Int): Note {
        TODO("Not yet implemented")
    }

    override fun saveNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun editNote(id: Int) {
        TODO("Not yet implemented")
    }

}