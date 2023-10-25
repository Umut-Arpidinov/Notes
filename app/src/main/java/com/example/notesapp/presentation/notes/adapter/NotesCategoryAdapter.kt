package com.example.notesapp.presentation.notes.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.local.database.enitites.NoteType
import com.example.notesapp.data.local.database.enitites.NotesCategory
import com.example.notesapp.databinding.CustomCategoryVeiwBinding


class NotesCategoryAdapter(
    private val listOfCategories: List<NotesCategory>
) : RecyclerView.Adapter<NotesCategoryAdapter.NotesCategoryViewHolder>(){


    private var onCategoryClickListener : ((position: Int, type: NoteType) -> Unit)? = null

    fun setOnCategoryClickListener(listener: (position: Int, type: NoteType) -> Unit){
        onCategoryClickListener = listener
    }

    inner class NotesCategoryViewHolder(val binding: CustomCategoryVeiwBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notesCategory: NotesCategory)=with(binding){
            tvNotesCategory.text = notesCategory.name
            ivCategoryNote.setImageDrawable(notesCategory.icon)
            ivCategoryNote.setBackgroundColor(notesCategory.iconBackgroundColor)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesCategoryViewHolder {
        val binding = CustomCategoryVeiwBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesCategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }

    override fun onBindViewHolder(holder: NotesCategoryViewHolder, position: Int) {
        val currentItem = listOfCategories[position]
        holder.bind(currentItem)

        holder.binding.linearChildCardView.setOnClickListener {
            onCategoryClickListener?.invoke(position, currentItem.type)
        }
    }






}