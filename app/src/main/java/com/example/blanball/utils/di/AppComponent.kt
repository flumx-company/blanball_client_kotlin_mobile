package com.example.blanball.utils.di

import com.example.blanball.presentation.fragments.LoginFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
}