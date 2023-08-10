package com.example.blanball.utils.di

import com.example.domain.usecases.implementations.FillingTheUserProfileUseCaseImpl
import com.example.domain.usecases.implementations.GetMyProfileUseCaseImpl
import com.example.domain.usecases.implementations.GetUserPlannedEventsByIdUseCaseImpl
import com.example.domain.usecases.implementations.GetUserProfileByIdUseCaseImpl
import com.example.domain.usecases.implementations.GetUserReviewsByIdUseCaseImpl
import com.example.domain.usecases.implementations.GetUsersListUseCaseImpl
import com.example.domain.usecases.implementations.RegistrationUseCaseImpl
import com.example.domain.usecases.implementations.ResetPasswordUseCaseImpl
import com.example.domain.usecases.implementations.UserLoginUseCaseImpl
import com.example.domain.usecases.interfaces.FillingTheUserProfileUseCase
import com.example.domain.usecases.interfaces.GetMyProfileUseCase
import com.example.domain.usecases.interfaces.GetUserPlannedEventsByIdUseCase
import com.example.domain.usecases.interfaces.GetUserProfileByIdUseCase
import com.example.domain.usecases.interfaces.GetUserReviewsByIdUseCase
import com.example.domain.usecases.interfaces.GetUsersListUseCase
import com.example.domain.usecases.interfaces.RegistrationUseCase
import com.example.domain.usecases.interfaces.ResetPasswordUseCase
import com.example.domain.usecases.interfaces.UserLoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindUserLoginUseCase(loginUseCase: UserLoginUseCaseImpl): UserLoginUseCase

    @Binds
    fun bindResetPasswordUseCase(resetPasswordUseCase: ResetPasswordUseCaseImpl): ResetPasswordUseCase

    @Binds
    fun bindRegistrationUseCase(registrationUseCase: RegistrationUseCaseImpl): RegistrationUseCase

    @Binds
    fun bindGetUserProfileByIdUseCase(getUserProfileByIdUseCase: GetUserProfileByIdUseCaseImpl): GetUserProfileByIdUseCase

    @Binds
    fun bindGetUserReviewsByIdUseCase(getUserReviewsByIdUseCase: GetUserReviewsByIdUseCaseImpl): GetUserReviewsByIdUseCase

    @Binds
    fun bindGetUserPlannedEventsByIdUseCase(getUserPlannedEventsByIdUseCase: GetUserPlannedEventsByIdUseCaseImpl): GetUserPlannedEventsByIdUseCase

    @Binds
    fun bindFillingTheUserProfileUseCase(fillingTheUserProfileUseCaseImpl: FillingTheUserProfileUseCaseImpl): FillingTheUserProfileUseCase

    @Binds
    fun bindGetUsersListUseCase(getUsersListUseCase: GetUsersListUseCaseImpl): GetUsersListUseCase

    @Binds
    fun bindGetMyProfileUseCase(getMyProfileUseCaseImpl: GetMyProfileUseCaseImpl): GetMyProfileUseCase
}