package com.example.domain.entity.results

import com.example.domain.entity.responses.UpdateUserProfileResponseEntity
import com.example.domain.entity.responses.UpdateUserProfileResponseEntityErrorDetail

sealed class FillingTheUserProfileResultEntity {
        data class Success(val success: UpdateUserProfileResponseEntity) : FillingTheUserProfileResultEntity()
        data class Error(val error: UpdateUserProfileResponseEntityErrorDetail) : FillingTheUserProfileResultEntity()
}