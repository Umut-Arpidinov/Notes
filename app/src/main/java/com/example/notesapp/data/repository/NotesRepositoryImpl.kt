package com.example.notesapp.data.repository


import com.example.notesapp.data.local.database.NotesRoomDatabase
import com.example.notesapp.data.local.database.enitites.Event
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.local.database.enitites.NoteType
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDB: NotesRoomDatabase,
) : NotesRepository {
    override fun getAllNotes(): Single<List<Note>> {
        return notesDB.notesDao().getNotes()
    }

    override fun getNotesByType(type: NoteType): Single<List<Note>> {
        return notesDB.notesDao().getNotesByType(type)
    }

    override fun getNoteById(uid: Int): Single<Note> {
        return notesDB.notesDao().getNoteById(uid)
    }

    override fun getNotesByKeyword(keyword: String): Single<List<Note>> {
        return notesDB.notesDao().getNotesByKeyword(keyword)
    }


    override fun saveNote(note: Note): Single<Unit> {
        return notesDB.notesDao().addNote(note)
    }

    override fun saveEvent(event: Event): Single<Unit> {
        return notesDB.notesDao().addEvent(event)
    }

    override fun deleteNote(id: Int): Single<Unit> {
        return notesDB.notesDao().removeNote(id)
    }

    override fun editNote(note: Note): Single<Unit> {
        return notesDB.notesDao().updateNote(note)
    }

    override fun updateNoteType(uid: Int, type: NoteType): Single<Unit> {
        return notesDB.notesDao().updateNoteType(uid, type)
    }

    override fun getAllEvents(): Single<List<Event>> {
        return notesDB.notesDao().getEvents()
    }

    override fun deleteEvent(id: Int): Single<Unit> {
        return notesDB.notesDao().removeEvent(id)
    }

}