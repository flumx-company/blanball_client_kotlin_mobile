package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.UpdateUserProfileResultEntity

interface UpdateUserProfile {
    suspend fun executeUpdateUserProfile(
    ): UpdateUserProfileResultEntity
}