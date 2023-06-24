package com.example.domain.usecases.interfaces

interface FillingTheUserProfileUseCase {
    suspend fun executeUpdateUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String,
    )
}
