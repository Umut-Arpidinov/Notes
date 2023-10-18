package com.example.notesapp.data.local.database.enitites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "type") var type: NoteType? = NoteType.DEFAULT
)
