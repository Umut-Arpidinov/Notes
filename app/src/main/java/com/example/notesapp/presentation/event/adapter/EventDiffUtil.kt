package com.example.notesapp.presentation.event.adapter


import androidx.recyclerview.widget.DiffUtil
import com.example.notesapp.data.local.database.enitites.Event

object EventDiffUtil : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.uid == newItem.uid
    }
}