package com.example.notesapp.presentation.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.data.local.database.enitites.NoteType


class NotesAdapter :
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

    inner class NotesViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        private val tvNoteTitle = view.findViewById<TextView>(R.id.tv_note_title)
        private val tvNoteBody = view.findViewById<TextView>(R.id.tv_note_body)
        private val tvNoteSavedDate = view.findViewById<TextView>(R.id.tv_note_saved_date)

        val actionList: ImageView = view.findViewById(R.id.action_list)
        val linearChild = itemView.findViewById<RelativeLayout>(R.id.relativeLayout)

        fun bind(note: Note) {
            tvNoteTitle.text = note.title.ifEmpty { note.text }
            tvNoteBody.text = note.text
            tvNoteSavedDate.text = note.lastSavedUpdatedTime
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val layout = when (viewType) {
            ALL -> R.layout.item_note_all
            HIDDEN -> R.layout.item_note_hidden
            FAVORITES -> R.layout.item_note_favourites
            else -> throw RuntimeException("Unknown View type")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)

            holder.linearChild.setOnClickListener {
                onNoteClickListener?.invoke(currentItem, position)
            }
            holder.actionList.setOnClickListener {
                onNoteActionClickListener?.invoke(currentItem.uid, position, currentItem.type, it)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentNote = currentList[position]

        return when (currentNote.type) {
            NoteType.DEFAULT -> return ALL
            NoteType.HIDDEN -> return HIDDEN
            NoteType.FAVOURITES -> return FAVORITES
            else -> ALL
        }
    }


    companion object {
        const val ALL = 1
        const val HIDDEN = 2
        const val FAVORITES = 3

    }

}