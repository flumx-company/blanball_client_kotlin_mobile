package com.example.domain.entity.results

import com.example.domain.entity.responses.EditMyProfileErrorEntity
import com.example.domain.entity.responses.EditMyProfileResponseEntity

sealed class EditMyProfileResultEntity {
    data class Success(val data: EditMyProfileResponseEntity) : EditMyProfileResultEntity()
    data class Error(val error: EditMyProfileErrorEntity) : EditMyProfileResultEntity()
}