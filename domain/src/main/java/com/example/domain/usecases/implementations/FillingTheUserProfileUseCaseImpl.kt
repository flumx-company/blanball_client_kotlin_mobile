package com.example.domain.usecases.implementations

import com.example.domain.entity.results.FillingTheUserProfileResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.FillingTheUserProfileUseCase
import javax.inject.Inject

class FillingTheUserProfileUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) :
    FillingTheUserProfileUseCase {
    override suspend fun executeUpdateUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String,
    ): FillingTheUserProfileResult {
        return appRepository.fillingTheUserProfile(
            birthday = birthday,
            height = height,
            weight = weight,
            position = position,
            working_leg = working_leg,
            place_name = place_name,
        )
    }
}