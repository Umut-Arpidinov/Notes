package com.example.notesapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.database.dao.NotesDAO
import com.example.notesapp.data.database.enitites.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesRoomDatabase  : RoomDatabase(){

    abstract fun notesDao(): NotesDAO

}