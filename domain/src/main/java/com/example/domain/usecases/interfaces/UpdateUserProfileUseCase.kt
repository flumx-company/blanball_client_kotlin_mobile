package com.example.domain.usecases.interfaces

import java.text.FieldPosition

interface UpdateUserProfileUseCase {
    suspend fun executeUpdateUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String,
    )
}
