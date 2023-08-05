package com.example.domain.entity.results

import com.example.domain.entity.responses.GetMyProfileErrorDetailEntity
import com.example.domain.entity.responses.GetMyProfileResponseDataEntity

sealed class GetMyProfileResultEntity {
    data class Success(val success: GetMyProfileResponseDataEntity) : GetMyProfileResultEntity()
    data class Error(val error: GetMyProfileErrorDetailEntity) : GetMyProfileResultEntity()
}