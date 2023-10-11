package com.example.notesapp.presentation.create_note

import androidx.navigation.fragment.findNavController
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.example.notesapp.presentation.extensions.showSnackBar

class NewNoteFragment : BaseFragment<FragmentNewNoteBinding, NewNoteFragmentViewModel>() {

    override fun getViewModelClass(): Class<NewNoteFragmentViewModel> = NewNoteFragmentViewModel::class.java

    override fun getViewBinding(): FragmentNewNoteBinding = FragmentNewNoteBinding.inflate(layoutInflater)


    override fun setUpListener() = with(binding){
        super.setUpListener()
        linearBack.setOnClickListener { navigateToHome() }


        binding.btnSave.setOnClickListener {
           if(isFieldsValid()){
               showSnackBar("Ok")
           }
            else {

           }
        }



    }






    override fun setUpViews() {
        super.setUpViews()
    }

    private fun navigateToHome(){
        findNavController().popBackStack()
    }

    private fun showSnackBar(message: String){
        context?.showSnackBar(message,this.view)
    }

    private fun isFieldsValid(): Boolean{
        return !binding.editTextNoteContent.text.isNullOrEmpty()
    }
}