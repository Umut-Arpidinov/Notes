package com.example.notesapp.presentation.notes

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.database.enitites.Note
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.presentation.extensions.showSnackBar
import com.example.notesapp.presentation.notes.adapter.NotesAdapter
import javax.inject.Inject

class NotesFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentNotesBinding, NotesViewModel>() {

    private val notesAdapter = NotesAdapter()

    override fun getViewModelClass(): Class<NotesViewModel> = NotesViewModel::class.java

    override fun getViewBinding(): FragmentNotesBinding = FragmentNotesBinding.inflate(layoutInflater)

    private val notesViewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[NotesViewModel::class.java]
    }

    override fun setUpViews()  {
        super.setUpViews()
        binding.notesRv.apply {
            adapter = notesAdapter
        }
        setUpCurrentTime()
        notesViewModel.getAllNotes()
    }

    override fun setUpListener() = with(binding) {
        super.setUpListener()
        binding.tvTitleDate.setOnClickListener {
            requireContext().showSnackBar("Some message",this@NotesFragment.requireView())
        }
    }

    override fun observeData() {
        super.observeData()
        notesViewModel.notes.observe(viewLifecycleOwner){
            println("${it.size} it it it ")
            notesAdapter.submitList(it)
        }
    }
    private fun setUpCurrentTime(){
        val currentTime = notesViewModel.getTime()
        binding.tvTitleDate.text = currentTime
    }


}