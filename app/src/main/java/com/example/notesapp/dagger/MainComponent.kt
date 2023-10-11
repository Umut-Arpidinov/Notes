package com.example.notesapp.dagger

import android.content.Context
import com.example.notesapp.base.BaseComponent
import com.example.notesapp.dagger.module.DataBaseModule
import com.example.notesapp.dagger.module.FragmentModule
import com.example.notesapp.dagger.module.RepositoryModule
import com.example.notesapp.dagger.module.ViewModelModule
import com.example.notesapp.dagger.scope.ApplicationScope
import com.example.notesapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [DataBaseModule::class,RepositoryModule::class, ViewModelModule::class, FragmentModule::class])
interface MainComponent : BaseComponent<MainActivity>{

    @Component.Factory
    interface MainComponentFactory {
        fun create(@BindsInstance context: Context): MainComponent
    }
}
