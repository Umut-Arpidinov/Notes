package com.example.notesapp.data.local.database.enitites

import android.graphics.drawable.Drawable

data class NotesCategory(
    val name: String,
    val icon: Drawable?,
    val iconBackgroundColor: Int,
    val type: NoteType
)