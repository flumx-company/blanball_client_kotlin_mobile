package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetRelevantUserSearchListResult

interface GetRelevantUserSearchListUseCase {
    suspend fun executeGetRelevantUserSearchList(
        search: String,
        page: Int,
        skipids: String,
    ): GetRelevantUserSearchListResult
}