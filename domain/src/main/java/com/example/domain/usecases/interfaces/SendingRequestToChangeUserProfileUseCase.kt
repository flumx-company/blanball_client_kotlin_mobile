package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.EditMyProfileResult

interface SendingRequestToChangeUserProfileUseCase {
    suspend fun executeEditUserProfileRequest(
        phone: String,
        emailRequestConfiguration: Boolean,
        phoneRequestConfiguration: Boolean,
        showReviewsRequestConfiguration: Boolean,
        about_me: String,
        birthday: String,
        gender: String,
        height: Int?,
        last_name: String,
        name: String,
        position: String?,
        weight: Int?,
        working_leg: String?,
        lat: Double,
        lon: Double,
        place_name: String,
    ): EditMyProfileResult
}