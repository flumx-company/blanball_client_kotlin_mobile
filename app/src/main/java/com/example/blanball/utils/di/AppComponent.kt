package com.example.blanball.utils.di

import com.example.blanball.presentation.fragments.LoginFragment
import com.example.blanball.presentation.fragments.ResetPasswordStep1Fragment
import com.example.blanball.presentation.fragments.ResetPasswordStep2Fragment
import com.example.blanball.presentation.fragments.ResetPasswordStep3Fragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, DataStoreModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(resetPasswordStep1Fragment: ResetPasswordStep1Fragment)
    fun inject(resetPasswordStep2Fragment: ResetPasswordStep2Fragment)
    fun inject(resetPasswordStep3Fragment: ResetPasswordStep3Fragment)
}