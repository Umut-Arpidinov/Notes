package com.example.notesapp.presentation.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.notesapp.R
import com.example.notesapp.databinding.CustomCategoryVeiwBinding

class CustomNotesCategoryView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: CustomCategoryVeiwBinding

    private val whiteColor = ContextCompat.getColor(context, R.color.white)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_category_veiw, this, true)
        binding = CustomCategoryVeiwBinding.bind(this)
        initAttrs(attrs, defStyleAttr, defStyleRes)
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNotesCategoryView, defStyleAttr, defStyleRes)
        with(binding) {
            val text = typedArray.getString(R.styleable.CustomNotesCategoryView_category_name)
            val drawable = typedArray.getDrawable(R.styleable.CustomNotesCategoryView_category_icon)
            val iconBackgroundColor = typedArray.getColor(R.styleable.CustomNotesCategoryView_category_color, -1)
            val categoryNotesBackgroundColor = typedArray.getColor(R.styleable.CustomNotesCategoryView_cardBackgroundColor, -1)
            val background = typedArray.getDrawable(R.styleable.CustomNotesCategoryView_category_background)

            setNotesCategoryIcon(drawable)
            setNotesCategoryText(text)
            setIconBackgroundColor(iconBackgroundColor)
            binding.linearChildCardView.background = background
        }

        typedArray.recycle()

    }

    private fun setNotesCategoryIcon(drawable: Drawable?) {
        binding.ivCategoryNote.setImageDrawable(drawable)
    }

    private fun setNotesCategoryText(text: String?) {
        binding.tvNotesCategory.text = text
    }

    private fun setIconBackgroundColor(color: Int) {
        binding.ivCategoryNote.setBackgroundColor(color)
    }

    fun setNotesCategoryBackgroundColor(color: Int) {
        binding.cardViewCategory.setCardBackgroundColor(color)
    }

    fun setNotesCategoryBackgroundDefaultColor() {
        binding.cardViewCategory.setCardBackgroundColor(whiteColor)
    }


}