package com.example.notesapp.dagger.module

import androidx.lifecycle.ViewModel
import com.example.notesapp.dagger.mapkey.ViewModelKey
import com.example.notesapp.dagger.scope.ApplicationScope
import com.example.notesapp.presentation.notes.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    @Binds
    fun bindNotesViewModel(impl: NotesViewModel): ViewModel

}