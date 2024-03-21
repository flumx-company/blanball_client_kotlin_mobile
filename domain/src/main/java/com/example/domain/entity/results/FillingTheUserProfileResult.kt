package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.UpdateUserProfileResponseEntityErrorDetail
import com.example.domain.entity.responses.success.UpdateUserProfileResponseDataEntity

sealed class FillingTheUserProfileResult {
        data class Success(val success: UpdateUserProfileResponseDataEntity) : FillingTheUserProfileResult()
        data class Error(val error: UpdateUserProfileResponseEntityErrorDetail) : FillingTheUserProfileResult()
}