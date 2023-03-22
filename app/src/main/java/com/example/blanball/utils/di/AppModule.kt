package com.example.blanball.utils.di

import android.content.Context
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideLoginViewModelFactory (loginRepository: LoginRepository): LoginViewModel.LoginViewModelFactory {
        return LoginViewModel.LoginViewModelFactory(loginRepository = loginRepository)
    }
}