package com.example.blanball.utils.di

import com.example.domain.usecases.implementations.RegistrationUseCaseImpl
import com.example.domain.usecases.implementations.ResetPasswordUseCaseImpl
import com.example.domain.usecases.interfaces.RegistrationUseCase
import com.example.domain.usecases.interfaces.ResetPasswordUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
@Binds
fun bindResetPasswordUseCase(resetPasswordUseCase: ResetPasswordUseCaseImpl) : ResetPasswordUseCase

@Binds
fun bindRegistrationUseCase(registrationUseCase: RegistrationUseCaseImpl) : RegistrationUseCase

}