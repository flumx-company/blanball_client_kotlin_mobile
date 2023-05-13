package com.example.domain.entity.results

import com.example.domain.entity.responses.GetUserProfileByIdDetailDataEntity
import com.example.domain.entity.responses.GetUserProfileByIdResponseEntity


sealed class GetUserProfileByIdResultEntity {
    data class Success(val data: GetUserProfileByIdResponseEntity) : GetUserProfileByIdResultEntity()
    data class Error(val error: GetUserProfileByIdDetailDataEntity) : GetUserProfileByIdResultEntity()
}