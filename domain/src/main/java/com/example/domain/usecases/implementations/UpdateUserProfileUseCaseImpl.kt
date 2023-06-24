package com.example.domain.usecases.implementations

import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.UpdateUserProfileUseCase
import javax.inject.Inject

class UpdateUserProfileUseCaseImpl @Inject constructor(internal val appRepository: AppRepository): UpdateUserProfileUseCase {
    override suspend fun executeUpdateUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String
    ) {
        return appRepository.updateUserProfile()
    }
}