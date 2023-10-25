package com.example.notesapp.presentation.create_note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.base.BaseViewModel
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.repository.NotesRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NewNoteFragmentViewModel @Inject constructor(
    private val repository: NotesRepository,
) : BaseViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> get() = _note
    val symbols = MutableLiveData<Int>()

    init {
        symbols.value = 0
    }

    fun saveNote(note: Note) {
        makeRequest(
            repository.saveNote(note),
            onSuccess = {},
            onError = { error -> error.printStackTrace() }
        )

    }

    fun incrementAmountOfSymbols(amount: Int) {
        symbols.value = amount
    }


    fun getTime(pattern: String): String {
        val simpleDate = SimpleDateFormat(pattern, Locale("En"))
        return simpleDate.format(Date())
    }
}