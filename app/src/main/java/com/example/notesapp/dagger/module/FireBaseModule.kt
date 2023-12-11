package com.example.notesapp.dagger.module

import com.example.notesapp.dagger.scope.ApplicationScope
import com.example.notesapp.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class FireBaseModule {

    @ApplicationScope
    @Provides
    fun provideFireBaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }





}