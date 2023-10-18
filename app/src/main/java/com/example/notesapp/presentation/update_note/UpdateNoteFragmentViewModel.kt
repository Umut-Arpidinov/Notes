package com.example.notesapp.presentation.update_note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.repository.NotesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UpdateNoteFragmentViewModel @Inject constructor(
    private val repository: NotesRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _note: MutableLiveData<Note> = MutableLiveData()
    val note: LiveData<Note> get() = _note


    fun getNoteById(uid: Int){
        compositeDisposable.add(repository.getNoteById(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       _note.value  = it
            },{})
        )
    }

}