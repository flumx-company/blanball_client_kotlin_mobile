package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.EditMyProfileErrorEntityDetail
import com.example.domain.entity.responses.success.EditMyProfileResponseEntity

sealed class EditMyProfileResult {
    data class Success(val data: EditMyProfileResponseEntity) : EditMyProfileResult()
    data class Error(val error: EditMyProfileErrorEntityDetail) : EditMyProfileResult()
}