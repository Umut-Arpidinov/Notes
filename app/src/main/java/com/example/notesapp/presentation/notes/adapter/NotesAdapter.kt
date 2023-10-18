package com.example.notesapp.presentation.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.databinding.ItemNoteBinding
import java.util.ArrayList

class NotesAdapter : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NotesDiffUtil) {

    private var  onNoteClickListener: ((note: Note) -> Unit)? = null


    fun setOnNoteClickListener(listener: ((note: Note) -> Unit)?){
        onNoteClickListener = listener
    }

    inner class NotesViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) = with(binding) {
            tvNoteTitle.text = if(!note.title.isNullOrEmpty()) note.title else note.text
            tvNoteBody.text = note.text
        }
    }
    fun getNotes(): Int{
        return itemCount
    }
    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
            holder.binding.cardViewItem.setOnClickListener {
                onNoteClickListener?.invoke(currentItem)
            }
        }

    }

}