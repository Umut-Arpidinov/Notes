package com.example.notesapp.dagger.module

import android.content.Context
import androidx.room.Room
import com.example.notesapp.dagger.scope.ApplicationScope
import com.example.notesapp.data.local.database.NotesRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @ApplicationScope
    @Provides
    fun provideNotesDb(context: Context): NotesRoomDatabase {
        return Room.databaseBuilder(context, NotesRoomDatabase::class.java,"NotesDB").build()
    }
}