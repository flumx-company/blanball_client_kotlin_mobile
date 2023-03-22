package com.example.blanball.utils.di

import com.example.blanball.presentation.fragments.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, DataStoreModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
}