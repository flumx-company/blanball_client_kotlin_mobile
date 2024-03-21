package com.example.domain.entity.results

import com.example.domain.entity.responses.success.GetUsersListResponseDataEntity
import com.example.domain.entity.responses.errors.GetUsersListResponseErrorDetailDataEntity

sealed class GetUsersListResult {
    data class Success(val data: GetUsersListResponseDataEntity) : GetUsersListResult()
    data class Error(val error: GetUsersListResponseErrorDetailDataEntity) :
        GetUsersListResult()
}