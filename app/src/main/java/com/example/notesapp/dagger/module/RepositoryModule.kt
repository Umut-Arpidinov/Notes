package com.example.notesapp.dagger.module

import com.example.notesapp.data.repository.AuthRepository
import com.example.notesapp.data.repository.AuthRepositoryImpl
import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.data.repository.NotesRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
interface RepositoryModule {

    @Binds
    fun bindNotesRepository(impl: NotesRepositoryImpl) : NotesRepository

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository


}