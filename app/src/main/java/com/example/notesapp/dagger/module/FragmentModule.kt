package com.example.notesapp.dagger.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.notesapp.base.InjectFragmentFactory
import com.example.notesapp.dagger.mapkey.FragmentKey
import com.example.notesapp.presentation.authentication.LoginFragment
import com.example.notesapp.presentation.create_note.NewNoteFragment
import com.example.notesapp.presentation.event.CreateEventFragment
import com.example.notesapp.presentation.event.EventFragment
import com.example.notesapp.presentation.notes.NotesFragment
import com.example.notesapp.presentation.update_note.UpdateNoteFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @Binds
    fun bindFragmentFactory(factory: InjectFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(NotesFragment::class)
    fun bindNotesFragment(fragment: NotesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(NewNoteFragment::class)
    fun bindNewNoteFragment(fragment: NewNoteFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(UpdateNoteFragment::class)
    fun bindUpdateNoteFragment(fragment: UpdateNoteFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(LoginFragment::class)
    fun bindLoginFragment(fragment: LoginFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(CreateEventFragment::class)
    fun bindCreateEventFragment(fragment: CreateEventFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(EventFragment::class)
    fun bindEventFragment(fragment: EventFragment): Fragment



}