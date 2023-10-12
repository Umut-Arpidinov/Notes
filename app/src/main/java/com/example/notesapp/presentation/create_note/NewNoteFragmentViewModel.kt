package com.example.notesapp.presentation.create_note

import android.app.Application
import android.util.Log
import com.example.notesapp.base.BaseViewModel
import com.example.notesapp.data.database.enitites.Note
import com.example.notesapp.data.repository.NotesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewNoteFragmentViewModel @Inject constructor(
    private val repository: NotesRepository
) : BaseViewModel() {

    fun saveNote(note: Note){
        repository.saveNote(note).request{}
    }
}