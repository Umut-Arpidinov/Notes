package com.example.notesapp.presentation.create_note

import android.text.Editable
import android.text.TextWatcher
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.local.database.enitites.Note
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.example.notesapp.presentation.extensions.showSnackBar
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
            val title = editTextPageTitle.text.toString().trim()
            val text = editTextNoteContent.text.toString().trim()
            val lastModifiedTime = newNoteViewModel.getTime(DATE)
            val note = Note(title = title, text = text, lastSavedUpdatedTime = lastModifiedTime)
            saveNote(note)
            navigateToHome()
        }

    }

    override fun observeData() = with(binding) {
        super.observeData()
        newNoteViewModel.symbols.observe(viewLifecycleOwner) {
            setNumberOfSymbols(it)
        }

    }

    override fun setUpViews() {
        super.setUpViews()
        setButtonVisibility()
        setCurrentDate()
        binding.editTextNoteContent.addTextChangedListener(editTextWatcher)
        binding.editTextPageTitle.addTextChangedListener(editTextWatcher)
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

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigateToHome()
        }
    }

    private fun setButtonVisibility() = with(binding) {
        var isTextEmpty = false
        var isTitleEmpty = false
        editTextNoteContent.doOnTextChanged { text, _, _, _ ->
            isTextEmpty = !text.isNullOrEmpty()
            btnSave.isVisible = isTextEmpty || isTitleEmpty
        }
        editTextPageTitle.doOnTextChanged { text, _, _, _ ->
            isTitleEmpty = !text.isNullOrEmpty()
            btnSave.isVisible = isTextEmpty || isTitleEmpty
        }
    }

    private fun setCurrentDate() = with(binding) {
        val currentDate = newNoteViewModel.getTime(DATE)
        tvModifiedDate.text = currentDate
    }

    private fun setNumberOfSymbols(amount: Int) = with(binding) {
        tvSymbols.text = getString(R.string.symbols, amount.toString())
    }

    private var editTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            val noteBodyLength = binding.editTextNoteContent.text.trim().length
            val noteTitleLength = binding.editTextPageTitle.text.trim().length
            newNoteViewModel.incrementAmountOfSymbols(noteBodyLength + noteTitleLength)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val noteBodyLength = binding.editTextNoteContent.text.trim().length
            val noteTitleLength = binding.editTextPageTitle.text.trim().length
            newNoteViewModel.incrementAmountOfSymbols(noteBodyLength + noteTitleLength)
        }

        override fun afterTextChanged(s: Editable?) {}

    }


    companion object {
        private const val DATE = "dd MMMM HH:mm"
    }

}
