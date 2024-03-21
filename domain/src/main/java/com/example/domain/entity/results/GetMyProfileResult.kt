package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetMyProfileErrorDetailEntity
import com.example.domain.entity.responses.success.GetMyProfileResponseDataEntity

sealed class GetMyProfileResult {
    data class Success(val success: GetMyProfileResponseDataEntity) : GetMyProfileResult()
    data class Error(val error: GetMyProfileErrorDetailEntity) : GetMyProfileResult()
}