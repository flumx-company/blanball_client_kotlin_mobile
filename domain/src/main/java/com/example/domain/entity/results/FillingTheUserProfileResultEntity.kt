package com.example.domain.entity.results

import com.example.domain.entity.responses.UpdateUserProfileResponseDataEntity
import com.example.domain.entity.responses.errors.UpdateUserProfileResponseEntityErrorDetail

sealed class FillingTheUserProfileResultEntity {
        data class Success(val success: UpdateUserProfileResponseDataEntity) : FillingTheUserProfileResultEntity()
        data class Error(val error: UpdateUserProfileResponseEntityErrorDetail) : FillingTheUserProfileResultEntity()
}