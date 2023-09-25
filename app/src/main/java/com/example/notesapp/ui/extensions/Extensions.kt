package com.example.notesapp.ui.extensions

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemSnackBarBinding
import com.google.android.material.snackbar.Snackbar



fun Context.showSnackBar(message: String,container: View?){
    container?.let {
        val snackView = View.inflate(this,R.layout.item_snack_bar,null)
        val binding = ItemSnackBarBinding.bind(snackView)
        val snackbar = Snackbar.make(container,"",Snackbar.LENGTH_LONG)
        snackbar.apply {
            (view as ViewGroup).addView(binding.root)
            binding.tvSnackMessage.text = message
            show()
        }
    }


}