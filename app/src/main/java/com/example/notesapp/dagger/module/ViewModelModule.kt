package com.example.notesapp.dagger.module

import androidx.lifecycle.ViewModel
import com.example.notesapp.dagger.mapkey.ViewModelKey
import com.example.notesapp.dagger.scope.ApplicationScope
import com.example.notesapp.presentation.authentication.LoginFragment
import com.example.notesapp.presentation.authentication.LoginViewModel
import com.example.notesapp.presentation.create_note.NewNoteFragmentViewModel
import com.example.notesapp.presentation.event.EventViewModel
import com.example.notesapp.presentation.notes.NotesViewModel
import com.example.notesapp.presentation.update_note.UpdateNoteFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    @Binds
    fun bindNotesViewModel(impl: NotesViewModel): ViewModel

    @IntoMap
    @ViewModelKey(NewNoteFragmentViewModel::class)
    @Binds
    fun bindNewNoteViewModel(impl: NewNoteFragmentViewModel): ViewModel

    @IntoMap
    @ViewModelKey(UpdateNoteFragmentViewModel::class)
    @Binds
    fun bindUpdateNoteViewModel(impl: UpdateNoteFragmentViewModel): ViewModel


    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    @Binds
    fun bindLoginViewModel(impl: LoginViewModel): ViewModel

    @IntoMap
    @ViewModelKey(EventViewModel::class)
    @Binds
    fun bindEventViewModel(impl: EventViewModel): ViewModel


}