package com.example.notesapp.presentation.create_note

import android.util.Log
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.local.database.enitites.Crud
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.example.notesapp.presentation.extensions.showSnackBar
import com.example.notesapp.presentation.update_note.UpdateNoteFragmentArgs
import javax.inject.Inject

class NewNoteFragment @Inject constructor(
    viewModelFactory: ViewModelFactory,
) : BaseFragment<FragmentNewNoteBinding>() {

    private val newNoteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewNoteFragmentViewModel::class.java]
    }

    override fun getViewBinding(): FragmentNewNoteBinding =
        FragmentNewNoteBinding.inflate(layoutInflater)


    override fun setUpListener() = with(binding) {
        super.setUpListener()
        linearBack.setOnClickListener { navigateToHome() }
        onBackPressed()
        btnSave.setOnClickListener {
            val title = editTextPageTitle.text.toString().trim().trimStart()
            val text = editTextNoteContent.text.toString().trim().trimStart()
            val note = Note(title = title, text = text)
            saveNote(note)
            navigateToHome()
        }

    }
    override fun observeData() = with(binding){
        super.observeData()

    }

    override fun setUpViews() {
        super.setUpViews()
        setButtonVisibility()
    }

    private fun saveNote(note: Note){
         newNoteViewModel.saveNote(note)
    }


    private fun navigateToHome() {
        findNavController().popBackStack()
    }

    private fun showSnackBar(message: String) {
        context?.showSnackBar(message, this.view)
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigateToHome()
        }
    }

    private fun setButtonVisibility() = with(binding) {
        editTextNoteContent.doOnTextChanged { text, _, _, _ ->
            btnSave.isVisible = !text.isNullOrEmpty()
        }
    }
}
