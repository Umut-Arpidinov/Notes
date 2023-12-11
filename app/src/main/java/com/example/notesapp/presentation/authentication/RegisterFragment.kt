package com.example.notesapp.presentation.authentication

import androidx.navigation.fragment.findNavController
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override fun getViewBinding(): FragmentRegisterBinding =
        FragmentRegisterBinding.inflate(layoutInflater)




    override fun setUpViews() {
        super.setUpViews()
    }

    override fun setUpListener() {
        super.setUpListener()
        binding.tvSignIn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeData() {
        super.observeData()
    }

}