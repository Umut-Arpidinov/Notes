package com.example.notesapp.util

import com.google.firebase.FirebaseError
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import java.lang.Exception


enum class ApiStatus{
    SUCCESS,
    ERROR,
    LOADING
}

sealed class UiState <out T> (val status: ApiStatus, val data: T?, val error:Exception?) {

    data class Success<out R>(val _data: R?) : UiState<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        error = null
    )

    data class Error(val exception: Exception) : UiState<Nothing>(
        status = ApiStatus.ERROR,
        data = null,
        error = exception
    )

    data class Loading(val isLoading: Boolean) : UiState<Nothing>(
        status = ApiStatus.LOADING,
        data = null,
        error = null
    )
}