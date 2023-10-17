package com.example.notesapp.presentation.update_note

import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
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



    override fun setUpListener() = with(binding) {
        super.setUpListener()
        linearBack.setOnClickListener { navigateToHome() }
        onBackPressed()
        btnSave.setOnClickListener {
            val title = editTextPageTitle.text.toString()
            val text = editTextNoteContent.text.toString()
            val note = Note(title = title, text = text)
            navigateToHome()
        }

    }


    override fun setUpViews() {
        super.setUpViews()
        setButtonVisibility()


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