package com.example.notesapp.presentation.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.database.enitites.Note
import com.example.notesapp.databinding.ItemNoteBinding

class NotesAdapter : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NotesDiffUtil) {

    inner class NotesViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(note: Note) = with(binding){
                tvNoteBody.text = note.text
                tvNoteTitle.text = note.title
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }

    }


}