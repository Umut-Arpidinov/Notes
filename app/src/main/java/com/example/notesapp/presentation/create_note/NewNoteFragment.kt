package com.example.notesapp.presentation.create_note

import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.database.enitites.Note
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.example.notesapp.presentation.extensions.showSnackBar
import javax.inject.Inject

class NewNoteFragment @Inject constructor(
    viewModelFactory: ViewModelFactory,
) : BaseFragment<FragmentNewNoteBinding, NewNoteFragmentViewModel>() {

    override fun getViewModelClass(): Class<NewNoteFragmentViewModel> =
        NewNoteFragmentViewModel::class.java


    private val newNoteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewNoteFragmentViewModel::class.java]
    }

    override fun getViewBinding(): FragmentNewNoteBinding =
        FragmentNewNoteBinding.inflate(layoutInflater)


    override fun setUpListener() = with(binding) {
        super.setUpListener()
        linearBack.setOnClickListener { navigateToHome() }


        btnSave.setOnClickListener {
            val title = editTextPageTitle.text.toString()
            val text = editTextNoteContent.text.toString()
            val note = Note(title = title, text = text)
            saveNote(note)
            showSnackBar("Added")
            navigateToHome()
        }

    }


    override fun setUpViews() {
        super.setUpViews()
        setButtonVisibility()
    }

    private fun saveNote(note: Note) {
        newNoteViewModel.saveNote(note)
    }


    private fun navigateToHome() {
        findNavController().popBackStack()
    }

    private fun showSnackBar(message: String) {
        context?.showSnackBar(message, this.view)
    }

    private fun setButtonVisibility()=with(binding){
       editTextNoteContent.doOnTextChanged { text, _, _, _ ->
           btnSave.isVisible = !text.isNullOrEmpty()
       }
    }
}
