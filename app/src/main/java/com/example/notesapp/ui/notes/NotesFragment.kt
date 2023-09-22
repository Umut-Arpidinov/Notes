package com.example.notesapp.ui.notes

import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.ui.extensions.showSnackBar

class NotesFragment : BaseFragment<FragmentNotesBinding, NotesViewModel>() {

    override fun getViewModelClass(): Class<NotesViewModel> = NotesViewModel::class.java

    override fun getViewBinding(): FragmentNotesBinding = FragmentNotesBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()
        binding.tv.setOnClickListener {
            showSnackBar("Clicked")
        }

    }

    override fun setUpListener() {
        super.setUpListener()

    }

    override fun observeData() {
        super.observeData()
    }


}