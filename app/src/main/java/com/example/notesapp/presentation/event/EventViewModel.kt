package com.example.notesapp.presentation.event

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.base.BaseViewModel
import com.example.notesapp.data.local.database.enitites.Event
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.repository.NotesRepository
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val repository: NotesRepository
) : BaseViewModel() {


    private val _events: MutableLiveData<List<Event>> = MutableLiveData()
    val events: LiveData<List<Event>> = _events


    fun saveEvent(event: Event){
        makeRequest(
            repository.saveEvent(event),
            onSuccess = {},
            onError = {}
        )
    }

    fun getEvents(){
        makeRequest(
            repository.getAllEvents(),
            onSuccess = {
                        _events.value = it
            },
            onError = {}
        )
    }

    fun removeEvent(id: Int){
        makeRequest(
            repository.deleteEvent(id),
            onError = {},
            onSuccess = {
                getEvents()
            }
        )
    }
}