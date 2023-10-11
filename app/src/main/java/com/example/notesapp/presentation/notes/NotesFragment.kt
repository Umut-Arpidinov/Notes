package com.example.notesapp.presentation.notes

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.presentation.extensions.showSnackBar
import javax.inject.Inject

class NotesFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentNotesBinding, NotesViewModel>() {

    override fun getViewModelClass(): Class<NotesViewModel> = NotesViewModel::class.java

    override fun getViewBinding(): FragmentNotesBinding = FragmentNotesBinding.inflate(layoutInflater)

    private val notesViewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[NotesViewModel::class.java]
    }

    override fun setUpViews() {
        super.setUpViews()
        notesViewModel.getNotes()
    }

    override fun setUpListener() = with(binding) {
        super.setUpListener()
        binding.tvTitleDate.setOnClickListener {
            requireContext().showSnackBar("Some message",this@NotesFragment.requireView())
        }
    }

    override fun observeData() {
        super.observeData()
    }

}