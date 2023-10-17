package com.example.notesapp.presentation.notes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.notesapp.data.local.database.enitites.Note

object NotesDiffUtil : DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.uid == newItem.uid
    }


}