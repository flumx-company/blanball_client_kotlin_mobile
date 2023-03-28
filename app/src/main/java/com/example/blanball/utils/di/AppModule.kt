package com.example.blanball.utils.di

import android.content.Context
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.domain.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideLoginViewModelFactory (loginRepository: AppRepository): LoginViewModel.LoginViewModelFactory {
        return LoginViewModel.LoginViewModelFactory(loginRepository = loginRepository)
    }
}