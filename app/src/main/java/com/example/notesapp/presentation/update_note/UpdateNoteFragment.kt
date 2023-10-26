package com.example.notesapp.presentation.update_note

import android.text.Editable
import android.text.TextWatcher
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
            val lastModifiedTime = updateNoteViewModel.getTime(DATE)
            val note = Note(title = title, text = text, uid = args.uid, lastSavedUpdatedTime = lastModifiedTime )
            updateNoteViewModel.updateNote(note)
            navigateToHome()
        }

    }


    override fun setUpViews() {
        super.setUpViews()
        updateNoteViewModel.getNoteById(args.uid)
        binding.editTextNoteContent.addTextChangedListener(editTextWatcher)
        binding.editTextPageTitle.addTextChangedListener(editTextWatcher)
    }

    override fun observeData() {
        super.observeData()
        updateNoteViewModel.note.observe(viewLifecycleOwner) {
            onNoteReceived(it)
            setButtonVisibility(it)
        }
        updateNoteViewModel.symbols.observe(viewLifecycleOwner){
            onSymbolsCountReceived(it)
        }
    }


    private fun onNoteReceived(note: Note) = with(binding) {
        val text = note.text
        val title = note.title
        val symbols = text.length + title.length
        editTextNoteContent.setText(text)
        editTextPageTitle.setText(title)
        tvModifiedDate.text = note.lastSavedUpdatedTime
        tvSymbols.text = symbols.toString()

    }
    private fun onSymbolsCountReceived(amount: Int){
        binding.tvSymbols.text = amount.toString()

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
            textChanged = initialText != text.toString().trim().trimStart() && !text.isNullOrEmpty()
            btnSave.isVisible = (titleChanged || textChanged)
        }
        editTextPageTitle.doOnTextChanged { text, _, _, _ ->
            titleChanged = initialTitle != text.toString().trim().trimStart() && !text.isNullOrEmpty()
            btnSave.isVisible = (titleChanged || textChanged)
        }
    }
    private var editTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            val noteBodyLength = binding.editTextNoteContent.text.length
            val noteTitleLength = binding.editTextPageTitle.text.length
            updateNoteViewModel.incrementAmountOfSymbols(noteBodyLength + noteTitleLength)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val noteBodyLength = binding.editTextNoteContent.text.trim().length
            val noteTitleLength = binding.editTextPageTitle.text.trim().length
            updateNoteViewModel.incrementAmountOfSymbols(noteBodyLength + noteTitleLength)
        }

        override fun afterTextChanged(s: Editable?) {}

    }

    companion object {
        private const val DATE  = "dd MMMM HH:mm"
    }



}