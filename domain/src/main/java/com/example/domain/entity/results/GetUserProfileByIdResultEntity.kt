package com.example.domain.entity.results

import com.example.domain.entity.responses.GetUserProfileByIdDetailDataEntity
import com.example.domain.entity.responses.PublicProfileDataResponseEntity


sealed class GetUserProfileByIdResultEntity {
    data class Success(val data: PublicProfileDataResponseEntity) : GetUserProfileByIdResultEntity()
    data class Error(val error: GetUserProfileByIdDetailDataEntity) : GetUserProfileByIdResultEntity()
}