package com.example.notesapp.dagger.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.notesapp.base.InjectFragmentFactory
import com.example.notesapp.dagger.mapkey.FragmentKey
import com.example.notesapp.presentation.create_note.NewNoteFragment
import com.example.notesapp.presentation.notes.NotesFragment
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
}