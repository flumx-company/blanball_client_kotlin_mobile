package com.example.blanball.utils.di

import com.example.domain.usecases.implementations.CreationAnEventUseCaseImpl
import com.example.domain.usecases.implementations.EditEventByIdUseCaseImpl
import com.example.domain.usecases.implementations.EmailVerificationUseCaseImpl
import com.example.domain.usecases.implementations.FillingTheUserProfileUseCaseImpl
import com.example.domain.usecases.implementations.GetAllEventsUseCaseImpl
import com.example.domain.usecases.implementations.GetEventByIdUseCaseImpl
import com.example.domain.usecases.implementations.GetIsTechWorksUseCaseImpl
import com.example.domain.usecases.implementations.GetMyEventsUseCaseImpl
import com.example.domain.usecases.implementations.GetMyProfileUseCaseImpl
import com.example.domain.usecases.implementations.GetRelevantUserSearchListUseCaseImpl
import com.example.domain.usecases.implementations.GetUserPlannedEventsByIdUseCaseImpl
import com.example.domain.usecases.implementations.GetUserProfileByIdUseCaseImpl
import com.example.domain.usecases.implementations.GetUserReviewsByIdUseCaseImpl
import com.example.domain.usecases.implementations.GetUsersListUseCaseImpl
import com.example.domain.usecases.implementations.RegistrationUseCaseImpl
import com.example.domain.usecases.implementations.ResetPasswordUseCaseImpl
import com.example.domain.usecases.implementations.SendingRequestToChangeUserProfileUseCaseImpl
import com.example.domain.usecases.implementations.UserLoginUseCaseImpl
import com.example.domain.usecases.interfaces.CreationAnEventUseCase
import com.example.domain.usecases.interfaces.EditEventByIdUseCase
import com.example.domain.usecases.interfaces.EmailVerificationUseCase
import com.example.domain.usecases.interfaces.FillingTheUserProfileUseCase
import com.example.domain.usecases.interfaces.GetAllEventsUseCase
import com.example.domain.usecases.interfaces.GetEventByIdUseCase
import com.example.domain.usecases.interfaces.GetIsTechWorksUseCase
import com.example.domain.usecases.interfaces.GetMyEventsUseCase
import com.example.domain.usecases.interfaces.GetMyProfileUseCase
import com.example.domain.usecases.interfaces.GetRelevantUserSearchListUseCase
import com.example.domain.usecases.interfaces.GetUserPlannedEventsByIdUseCase
import com.example.domain.usecases.interfaces.GetUserProfileByIdUseCase
import com.example.domain.usecases.interfaces.GetUserReviewsByIdUseCase
import com.example.domain.usecases.interfaces.GetUsersListUseCase
import com.example.domain.usecases.interfaces.RegistrationUseCase
import com.example.domain.usecases.interfaces.ResetPasswordUseCase
import com.example.domain.usecases.interfaces.SendingRequestToChangeUserProfileUseCase
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

    @Binds
    fun bindGetAllEventsUseCase(getAllEventsUseCaseImpl: GetAllEventsUseCaseImpl): GetAllEventsUseCase

    @Binds
    fun bindCreationAnEventUseCase(creationAnEventUseCaseImpl: CreationAnEventUseCaseImpl): CreationAnEventUseCase

    @Binds
    fun bindGetMyEventsUseCase(getMyEventsUseCaseImpl: GetMyEventsUseCaseImpl): GetMyEventsUseCase

    @Binds
    fun bindGetEventByIdUseCase(getEventByIdUseCaseImpl: GetEventByIdUseCaseImpl): GetEventByIdUseCase

    @Binds
    fun bindEditEventByIdUseCase(editEventByIdUseCaseImpl: EditEventByIdUseCaseImpl): EditEventByIdUseCase

    @Binds
    fun bindEmailVerificationUseCase(emailVerificationUseCaseImpl: EmailVerificationUseCaseImpl): EmailVerificationUseCase

    @Binds
    fun bindGetIsTechWorksUseCase(getIsTechWorksUseCaseImpl: GetIsTechWorksUseCaseImpl): GetIsTechWorksUseCase

    @Binds
    fun bindGetRelevantUserSearchListUseCase(getRelevantWorkUseCaseImpl: GetRelevantUserSearchListUseCaseImpl): GetRelevantUserSearchListUseCase

    @Binds
    fun bindSendingRequestToChangeUserProfileUseCase(SendingRequestToChangeUserProfileUseCaseImpl: SendingRequestToChangeUserProfileUseCaseImpl
    ): SendingRequestToChangeUserProfileUseCase

}
