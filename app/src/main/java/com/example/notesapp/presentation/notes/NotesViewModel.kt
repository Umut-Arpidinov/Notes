package com.example.notesapp.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.repository.NotesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val repository: NotesRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes


    fun getAllNotes() {
        val disposable = repository.getAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       _notes.value = it
            },{
                it.printStackTrace()
            })
        compositeDisposable.add(disposable)

    }

    fun removeNote(uid: Int) {
        val disposable = repository.deleteNote(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       getAllNotes()
            },{
                it.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }

    fun getTime(): String {
        val simpleDate = SimpleDateFormat("dd MMMM, yyyy", Locale("En"))
        return simpleDate.format(Date())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}


