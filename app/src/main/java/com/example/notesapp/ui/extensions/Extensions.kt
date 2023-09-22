package com.example.notesapp.ui.extensions

import android.graphics.Color
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View?.showSnackBar(@StringRes stringResId: Int) {
    this?.let {
        showSnackBar(resources.getString(stringResId))
    }
}

fun View?.showSnackBar(message: String) {
    this?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
            .setTextColor(Color.WHITE)
            .show()
    }
}

infix fun Fragment.showSnackBar(message: String){
    view.showSnackBar(message)
}