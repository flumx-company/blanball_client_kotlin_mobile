package com.example.domain.entity.results

import com.example.domain.entity.responses.GetUsersListResponseDataEntity
import com.example.domain.entity.responses.errors.GetUsersListResponseErrorDetailDataEntity

sealed class GetUsersListResultEntity {
    data class Success(val data: GetUsersListResponseDataEntity) : GetUsersListResultEntity()
    data class Error(val error: GetUsersListResponseErrorDetailDataEntity) :
        GetUsersListResultEntity()
}