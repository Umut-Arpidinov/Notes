package com.example.notesapp.ui.notes

import androidx.core.content.ContextCompat
import com.example.notesapp.R
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.ui.extensions.showSnackBar
import com.example.notesapp.ui.views.CustomNotesCategoryView

class NotesFragment : BaseFragment<FragmentNotesBinding, NotesViewModel>() {

    override fun getViewModelClass(): Class<NotesViewModel> = NotesViewModel::class.java

    override fun getViewBinding(): FragmentNotesBinding = FragmentNotesBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()
    }

    override fun setUpListener() = with(binding) {
        super.setUpListener()

    }

    override fun observeData() {
        super.observeData()
    }

}