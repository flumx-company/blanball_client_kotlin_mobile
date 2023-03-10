package com.example.blanball.utils.di

import com.example.blanball.presentation.fragments.LoginFragment
import com.example.data.datastore.TokenManagerImpl
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(retrofit: Retrofit)
    fun inject(tokenManagerImpl: TokenManagerImpl)
}