package com.example.notesapp.presentation.update_note

import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.databinding.FragmentNoteUpdateBinding
import com.example.notesapp.presentation.extensions.showSnackBar
import javax.inject.Inject

class UpdateNoteFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentNoteUpdateBinding>() {

    override fun getViewBinding(): FragmentNoteUpdateBinding =
        FragmentNoteUpdateBinding.inflate(layoutInflater)


    private val updateNoteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UpdateNoteFragmentViewModel::class.java]
    }

    private val args: UpdateNoteFragmentArgs by navArgs()


    override fun setUpListener() = with(binding) {
        super.setUpListener()
        linearBack.setOnClickListener { navigateToHome() }
        onBackPressed()
        btnSave.setOnClickListener {
            val title = editTextPageTitle.text.toString().trimStart().trim()
            val text = editTextNoteContent.text.toString().trimStart().trim()
            val note = Note(title = title, text = text, uid = args.uid)
            updateNoteViewModel.updateNote(note)
            navigateToHome()
        }

    }


    override fun setUpViews() {
        super.setUpViews()
        updateNoteViewModel.getNoteById(args.uid)
    }

    override fun observeData() {
        super.observeData()
        updateNoteViewModel.note.observe(viewLifecycleOwner) {
            onNoteReceived(it)
            setButtonVisibility(it)
        }
    }


    private fun onNoteReceived(note: Note) = with(binding) {
        val text = note.text
        val title = note.title
        editTextNoteContent.setText(text)
        editTextPageTitle.setText(title)
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

    private fun setButtonVisibility(note: Note) = with(binding) {
        var textChanged = false
        var titleChanged = false
        val initialText = note.text
        val initialTitle = note.title
        editTextNoteContent.doOnTextChanged { text, _, _, _ ->
            textChanged = initialText != text.toString().trim().trimStart()
            btnSave.isVisible = titleChanged || textChanged
        }
        editTextPageTitle.doOnTextChanged { text, _, _, _ ->
            titleChanged = initialTitle != text.toString().trim().trimStart()
            btnSave.isVisible = titleChanged || textChanged
        }
    }


}