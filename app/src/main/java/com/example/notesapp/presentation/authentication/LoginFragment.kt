package com.example.notesapp.presentation.authentication

import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.databinding.FragmentLoginBinding
import com.example.notesapp.presentation.extensions.showSnackBar
import com.example.notesapp.presentation.notes.NotesViewModel
import com.example.notesapp.util.ApiStatus
import com.google.firebase.Firebase
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sign


class LoginFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentLoginBinding>() {


    override fun getViewBinding(): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater)

    private val loginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun setUpViews()=with(binding){
        super.setUpViews()
        tvCreateAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionGlobalRegisterFragment()
            findNavController().navigate(action)
        }
        tvForgotPassword.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToResetFragment()
            findNavController().navigate(action)
        }

    }

    override fun setUpListener()=with(binding) {
        super.setUpListener()
        btnLogin.setOnClickListener {
            progressBar.isVisible = true
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()


            signIn(email, password)
        }
    }
    override fun observeData() {
        super.observeData()
    }


    private fun signIn(email: String, password: String)=with(binding){
        loginViewModel.onSignIn(email,password).observe(viewLifecycleOwner){result ->
            progressBar.isVisible = (result.status == ApiStatus.LOADING)
            when(result.status){
                ApiStatus.SUCCESS -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToNotesFragment()
                    findNavController().navigate(action)
                }
                ApiStatus.ERROR -> {
                  when(result.error){
                      is FirebaseAuthInvalidCredentialsException -> {
                          if(result.error.errorCode == INVALID_EMAIL){
                              tlEmail.error = "Invalid email"
                          }
                      }
                  }
                }

                ApiStatus.LOADING -> Unit
            }
        }
    }


    private val fieldTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val isPasswordValid = loginViewModel.validatePassword(password)
            val isEmailValid = loginViewModel.validateEmail(email)

        }

        override fun afterTextChanged(s: Editable?) {}

    }





    private fun showSnackBar(message: String) {
        context?.showSnackBar(message, this.view)
    }

    companion object{
        private const val INVALID_EMAIL = "ERROR_INVALID_EMAIL"
    }

}