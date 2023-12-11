package com.example.notesapp.data.repository

import com.example.notesapp.util.UiState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun signIn(email: String,password: String): Flow<UiState<AuthResult>>
    fun logIn(email: String, password: String): Task<AuthResult>
}