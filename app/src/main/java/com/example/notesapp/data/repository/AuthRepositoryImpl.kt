package com.example.notesapp.data.repository

import com.example.notesapp.util.UiState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthRepository {
    override fun signIn(email: String, password: String): Flow<UiState<AuthResult>> {
        return flow {
            emit(UiState.Loading(true))
            try {
                val result = auth.signInWithEmailAndPassword(email, password)
                    .await()
                emit(UiState.Success(result))

            } catch (e: Exception) {
                emit(UiState.Error(e))
            } catch (e: FirebaseAuthException) {
                emit(UiState.Error(e))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                emit(UiState.Error(e))
            }
        }


    }

    override fun logIn(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email,password)
    }


}