package com.example.notesapp.dagger.module

import com.example.notesapp.dagger.scope.ApplicationScope
import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.data.repository.NotesRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
interface RepositoryModule {

    @Binds
    fun bindNotesRepository(impl: NotesRepositoryImpl) : NotesRepository
}