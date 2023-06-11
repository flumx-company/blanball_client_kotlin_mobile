package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUsersListResultEntity

interface GetUsersListUseCase {
    suspend fun executeGetUsersList (page: Int): GetUsersListResultEntity
}