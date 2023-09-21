package com.example.notesapp.ui.create_note

import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentNewNoteBinding

class NewNoteFragment : BaseFragment<FragmentNewNoteBinding, NewNoteFragmentViewModel>() {

    override fun getViewModelClass(): Class<NewNoteFragmentViewModel> = NewNoteFragmentViewModel::class.java

    override fun getViewBinding(): FragmentNewNoteBinding = FragmentNewNoteBinding.inflate(layoutInflater)
}