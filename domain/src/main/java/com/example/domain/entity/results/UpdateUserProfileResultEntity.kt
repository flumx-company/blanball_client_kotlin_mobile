package com.example.domain.entity.results

import com.example.domain.entity.responses.UpdateUserProfileResponseEntity
import com.example.domain.entity.responses.UpdateUserProfileResponseEntityErrorDetail

sealed class UpdateUserProfileResultEntity {
        data class Success(val success: UpdateUserProfileResponseEntity) : UpdateUserProfileResultEntity()
        data class Error(val error: UpdateUserProfileResponseEntityErrorDetail) : UpdateUserProfileResultEntity()
}