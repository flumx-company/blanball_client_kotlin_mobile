package com.example.blanball.utils.di

import com.example.domain.usecases.implementations.ResetPasswordUseCaseImpl
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

}