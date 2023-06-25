package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.FillingTheUserProfileResultEntity

interface FillingTheUserProfileUseCase {
    suspend fun executeUpdateUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String,
    ) : FillingTheUserProfileResultEntity
}
