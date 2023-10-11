package com.example.notesapp.presentation.extensions

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemSnackBarBinding
import com.google.android.material.snackbar.Snackbar


fun Context.showSnackBar(message: String,container: View?){
    container?.let {
        val snackView = View.inflate(this,R.layout.item_snack_bar,null)
        val binding = ItemSnackBarBinding.bind(snackView)
        val snackBar = Snackbar.make(container,"",Snackbar.LENGTH_SHORT)
        snackBar.apply {
            (view as ViewGroup).addView(binding.root)
            view.background = null
            binding.tvSnackMessage.text = message
            show()
        }
    }


}