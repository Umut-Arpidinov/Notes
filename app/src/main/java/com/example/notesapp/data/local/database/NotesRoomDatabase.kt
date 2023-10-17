package com.example.notesapp.data.local.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.local.database.dao.NotesDAO
import com.example.notesapp.data.local.database.enitites.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesRoomDatabase  : RoomDatabase(){

    abstract fun notesDao(): NotesDAO

}