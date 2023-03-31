package com.example.blanball.utils.di

import android.content.Context
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.presentation.viewmodels.ResetPasswordStep1ViewModel
import com.example.blanball.presentation.viewmodels.ResetPasswordStep2ViewModel
import com.example.blanball.presentation.viewmodels.ResetPasswordStep3ViewModel
import com.example.domain.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideLoginViewModelFactory (appRepository: AppRepository): LoginViewModel.LoginViewModelFactory {
        return LoginViewModel.LoginViewModelFactory(appRepository = appRepository)
    }

    @Provides
    fun provideResetPasswordStep1 (appRepository: AppRepository): ResetPasswordStep1ViewModel.ResetPasswordStep1ViewModelFactory {
        return  ResetPasswordStep1ViewModel.ResetPasswordStep1ViewModelFactory(appRepository = appRepository)
    }

    @Provides
    fun provideResetPasswordStep2 (appRepository: AppRepository): ResetPasswordStep2ViewModel.ResetPasswordStep2ViewModelFactory {
        return  ResetPasswordStep2ViewModel.ResetPasswordStep2ViewModelFactory(appRepository = appRepository)
    }

    @Provides
    fun provideResetPasswordStep3 (appRepository: AppRepository): ResetPasswordStep3ViewModel.ResetPasswordStep3ViewModelFactory {
        return  ResetPasswordStep3ViewModel.ResetPasswordStep3ViewModelFactory(appRepository = appRepository)
    }
}