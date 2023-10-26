package com.example.notesapp.presentation.notes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.local.database.enitites.NoteType
import com.example.notesapp.databinding.ItemNoteBinding

class NotesAdapter(private val context: Context) :
    ListAdapter<Note, NotesAdapter.NotesViewHolder>(NotesDiffUtil) {

    private var onNoteClickListener: ((note: Note, position: Int) -> Unit)? = null

    private var onNoteActionClickListener: ((uid: Int, position: Int, type: NoteType?, view: View) -> Unit)? =
        null


    fun setOnNoteActionClickListener(listener: ((uid: Int, position: Int, type: NoteType?, view: View) -> Unit)?) {
        onNoteActionClickListener = listener
    }


    fun setOnNoteClickListener(listener: ((note: Note, position: Int) -> Unit)?) {
        onNoteClickListener = listener
    }

    inner class NotesViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) = with(binding) {
            tvNoteTitle.text = note.title.ifEmpty { note.text }
            tvNoteBody.text = note.text
            tvNoteSavedDate.text = note.lastSavedUpdatedTime
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
            when (currentItem.type) {
                NoteType.HIDDEN -> holder.binding.relativeLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.blue_background
                    )
                )
                NoteType.FAVOURITES -> holder.binding.relativeLayout.setBackgroundColor(
                    ContextCompat.getColor(context,R.color.yellow)
                )
                NoteType.DEFAULT -> holder.binding.relativeLayout.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.white)
                )
                NoteType.TRASH -> holder.binding.relativeLayout.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.red)
                )
                else -> {
                    holder.binding.relativeLayout.setBackgroundColor(
                        ContextCompat.getColor(context,R.color.white)
                    )
                }
            }

            holder.binding.lienarChild.setOnClickListener {
                onNoteClickListener?.invoke(currentItem, position)
            }
            holder.binding.actionList.setOnClickListener {
                onNoteActionClickListener?.invoke(currentItem.uid, position, currentItem.type, it)
            }
        }

    }

}