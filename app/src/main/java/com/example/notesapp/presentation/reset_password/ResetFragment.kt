package com.example.notesapp.presentation.reset_password

import com.example.notesapp.base.BaseFragment
import com.example.notesapp.databinding.FragmentResetPasswordBinding

class ResetFragment : BaseFragment<FragmentResetPasswordBinding>() {

    override fun getViewBinding(): FragmentResetPasswordBinding =
        FragmentResetPasswordBinding.inflate(layoutInflater)
}