package com.example.notesapp.ui.extensions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notesapp.R

class CustomSnackBarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyle: Int = 0
): ConstraintLayout(context,attributeSet,defaultStyle) {

    init {
        View.inflate(context, R.layout)
    }


}