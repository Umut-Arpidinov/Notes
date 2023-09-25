package com.example.notesapp.ui.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.example.notesapp.R
import com.example.notesapp.databinding.CustomCategoryVeiwBinding

class CustomNotesCategoryView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: CustomCategoryVeiwBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_category_veiw, this, true)
        binding = CustomCategoryVeiwBinding.bind(this)
        initAttrs(attrs,defStyleAttr,defStyleRes)
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNotesCategoryView, defStyleAttr, defStyleRes)
        with(binding){
            val text = typedArray.getString(R.styleable.CustomNotesCategoryView_category_name)
            val drawable = typedArray.getDrawable(R.styleable.CustomNotesCategoryView_category_icon)
            val iconBackgroundColor = typedArray.getColor(R.styleable.CustomNotesCategoryView_category_color,-1)
            val notesCategoryBackgroundColor = typedArray.getColor(R.styleable.CustomNotesCategoryView_category_background,-1)

            setNotesCategoryIcon(drawable)
            setNotesCategoryText(text)
            setIconBackgroundColor(iconBackgroundColor)
            setNotesCategoryBackgroundColor(notesCategoryBackgroundColor)

        }

        typedArray.recycle()

    }

    private fun setNotesCategoryIcon(drawable: Drawable?){
        binding.ivCategoryNote.setImageDrawable(drawable)
    }
    private fun setNotesCategoryText(text: String?){
        binding.tvNotesCategory.text = text
    }
    private fun setIconBackgroundColor(color: Int){
        binding.ivCategoryNote.setBackgroundColor(color)
    }
    private fun setNotesCategoryBackgroundColor(color: Int){
        ViewCompat.setBackgroundTintList(binding.linearParent, ColorStateList.valueOf(color))
    }



}