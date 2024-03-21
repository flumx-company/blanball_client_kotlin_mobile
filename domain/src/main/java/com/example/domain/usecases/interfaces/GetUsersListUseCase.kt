package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUsersListResult

interface GetUsersListUseCase {
    suspend fun executeGetUsersList(
        page: Int,
        gender: String?,
        age_min: Int?,
        age_max: Int?,
        ordering: String?,
        position: String?
    ): GetUsersListResult
}