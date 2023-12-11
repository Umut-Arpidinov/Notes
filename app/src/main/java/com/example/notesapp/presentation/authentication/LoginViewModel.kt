package com.example.notesapp.presentation.authentication

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.base.BaseViewModel
import com.example.notesapp.data.repository.AuthRepository
import com.example.notesapp.util.UiState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepository,
) : BaseViewModel() {

    private val TAG = "MainViewModel"

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val currentUser get() = _firebaseUser


    fun onSignIn(email: String, password: String): LiveData<UiState<AuthResult>> {
        return authRepo.signIn(email, password).asLiveData(viewModelScope.coroutineContext)
    }

    fun logIn(email: String, password: String){
        authRepo.logIn(email, password)
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }


     fun validateEmail(email: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()) {
            return false
        }
        return true
    }

     fun validatePassword(password: String): Boolean {
        return password.length < 6
    }



}