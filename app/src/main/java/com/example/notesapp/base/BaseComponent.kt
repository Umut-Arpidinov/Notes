package com.example.notesapp.base

import com.example.notesapp.dagger.scope.ApplicationScope



interface BaseComponent<T> {
    fun inject(target: T)
}