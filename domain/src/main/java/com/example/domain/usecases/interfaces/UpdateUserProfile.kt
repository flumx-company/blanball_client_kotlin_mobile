package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.FillingTheUserProfileResultEntity

interface UpdateUserProfile {
    suspend fun executeUpdateUserProfile(
    ): FillingTheUserProfileResultEntity
}