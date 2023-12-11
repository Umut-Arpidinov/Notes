package com.example.notesapp.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.base.BaseViewModel
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.local.database.enitites.NoteType
import com.example.notesapp.data.repository.NotesRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val repository: NotesRepository,
) : BaseViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes





    fun getDefaultNotes() {
        makeRequest(
            repository.getNotesByType(NoteType.HIDDEN),
            onSuccess = { _notes.value = it },
            onError = { it.printStackTrace() }
        )
    }

    fun getNotes(type: NoteType) {
        makeRequest(
            repository.getNotesByType(type),
            onSuccess = { _notes.value = it },
            onError = { it.printStackTrace() }
        )
    }


    fun removeNote(uid: Int, currentType: NoteType) {
        makeRequest(
            repository.deleteNote(uid),
            onSuccess = { getNotes(currentType) },
            onError = { it.printStackTrace() }
        )
    }

    fun updateNoteType(uid: Int, type: NoteType, currentType: NoteType) {
        makeRequest(
            repository.updateNoteType(uid, type),
            onSuccess = {
                getNotes(currentType)
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
    fun getNotesByKeyword(keyword: String){
        makeRequest(
            repository.getNotesByKeyword(keyword),
            onSuccess = {notes -> _notes.value = notes},
            onError = {it.printStackTrace()}
        )
    }

    fun getTime(pattern: String): String {
        val simpleDate = SimpleDateFormat(pattern, Locale("En"))
        return simpleDate.format(Date())
    }

}



