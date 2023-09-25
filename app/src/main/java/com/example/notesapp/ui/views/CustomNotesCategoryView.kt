package com.example.notesapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.notesapp.R
import com.example.notesapp.databinding.CustomCategoryVeiwBinding

class CustomNotesCategoryView : CardView {
    private val binding = CustomCategoryVeiwBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        context.obtainStyledAttributes(attributeSet, R.styleable.CustomNotesCategoryView).run {
            getText(R.styleable.CustomNotesCategoryView_category_name)?.let {
                setCategoryName(it.toString())
            }
            recycle()
        }
    }

    fun setCategoryName(categoryName: String) {
        binding.tvNotesCategory.text = categoryName
    }


}