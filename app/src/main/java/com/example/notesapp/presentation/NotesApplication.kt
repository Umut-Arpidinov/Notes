package com.example.notesapp.presentation

import android.app.Application
import com.example.notesapp.dagger.DaggerMainComponent

class NotesApplication : Application() {

    val component by lazy {
        DaggerMainComponent.factory()
            .create(this)

    }

}