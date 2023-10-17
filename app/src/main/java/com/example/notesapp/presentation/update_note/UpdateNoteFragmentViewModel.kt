package com.example.notesapp.presentation.update_note

import androidx.lifecycle.ViewModel
import com.example.notesapp.data.repository.NotesRepository
import javax.inject.Inject

class UpdateNoteFragmentViewModel @Inject constructor(
    private val repository: NotesRepository,
) : ViewModel() {

}