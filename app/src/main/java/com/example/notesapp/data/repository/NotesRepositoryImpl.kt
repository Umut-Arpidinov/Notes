package com.example.notesapp.data.repository


import android.util.Log
import androidx.lifecycle.LiveData
import com.example.notesapp.data.database.NotesRoomDatabase
import com.example.notesapp.data.database.enitites.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor (
    private val notesDB: NotesRoomDatabase
) : NotesRepository {
    override fun getAllNotes(): Single<List<Note>> {
        return notesDB.notesDao().getNotes()
    }

    override fun getNoteById(id: Int): Note {
        TODO("Not yet implemented")
    }

    override fun saveNote(note: Note): Single<Unit>{
        return notesDB.notesDao().addNote(note)
    }

    override fun deleteNote(id: Int): Single<Unit> {
        return notesDB.notesDao().removeNote(id)
    }

    override fun editNote(id: Int) {
        TODO("Not yet implemented")
    }

}