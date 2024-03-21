package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetUserProfileByIdDetailDataEntity
import com.example.domain.entity.responses.success.PublicProfileDataResponseEntity


sealed class GetUserProfileByIdResult {
    data class Success(val data: PublicProfileDataResponseEntity) : GetUserProfileByIdResult()
    data class Error(val error: GetUserProfileByIdDetailDataEntity) : GetUserProfileByIdResult()
}