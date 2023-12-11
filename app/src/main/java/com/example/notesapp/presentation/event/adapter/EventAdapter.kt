package com.example.notesapp.presentation.event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.data.local.database.enitites.Event
import com.example.notesapp.databinding.ItemEventBinding
import java.util.Date

class EventAdapter(val context: Context) : ListAdapter<Event,EventAdapter.EventViewHolder>(EventDiffUtil) {

    private var onEventClickListener: ((view: View, uid: Int, position: Int) -> Unit)? = null

    fun onEventClickListener(listener: (view: View, uid: Int, position: Int) -> Unit) {
        onEventClickListener = listener
    }

    inner class EventViewHolder(val binding: ItemEventBinding): ViewHolder(binding.root){
        fun bind(event: Event)=with(binding){
            val date = Date(event.date)
            val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
            val timeFormat = android.text.format.DateFormat.getTimeFormat(context)
            tvTile.text = event.title
            tvMessage.text = event.message
            tvDate.text = dateFormat.format(date)
            tvTime.text = timeFormat.format(date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = currentList[position]
        holder.bind(currentEvent)
        holder.binding.itemEventCard.setOnLongClickListener {
            onEventClickListener?.invoke(it,currentEvent.uid,position)
            true
        }
    }
}