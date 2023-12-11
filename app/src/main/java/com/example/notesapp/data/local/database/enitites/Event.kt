package com.example.notesapp.data.local.database.enitites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "message") var message: String,
    @ColumnInfo(name = "date") var date: Long,
)
