package com.example.notesapp.presentation.notes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.notesapp.base.BaseViewModel
import com.example.notesapp.data.database.enitites.Note
import com.example.notesapp.data.repository.NotesRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
): BaseViewModel() {



    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes


    fun getAllNotes(){
        repository.getAllNotes().request {
            _notes.postValue(it)
        }
    }

    fun removeNote(id: Int){
        repository.deleteNote(id).request{}
    }



    fun getTime(): String{
        val simpleDate = SimpleDateFormat("dd MMMM, yyyy", Locale("En"))
        return simpleDate.format(Date())

    }

}

