package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.FillingTheUserProfileResult

interface UpdateUserProfile {
    suspend fun executeUpdateUserProfile(
    ): FillingTheUserProfileResult
}