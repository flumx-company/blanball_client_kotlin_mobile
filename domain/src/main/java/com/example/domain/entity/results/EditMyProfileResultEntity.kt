package com.example.domain.entity.results

import com.example.domain.entity.responses.success.EditMyProfileResponseEntity
import com.example.domain.entity.responses.errors.EditMyProfileErrorEntityDetail

sealed class EditMyProfileResultEntity {
    data class Success(val data: EditMyProfileResponseEntity) : EditMyProfileResultEntity()
    data class Error(val error: EditMyProfileErrorEntityDetail) : EditMyProfileResultEntity()
}