package com.example.notesapp.presentation.notes

import com.example.notesapp.base.BaseViewModel
import com.example.notesapp.data.database.enitites.Note
import com.example.notesapp.data.repository.NotesRepository
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
): BaseViewModel() {


    fun getNotes() : List<Note>{
        return repository.getAllNotes()
    }

}