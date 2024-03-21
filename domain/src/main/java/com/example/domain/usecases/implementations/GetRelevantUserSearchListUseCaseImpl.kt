package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetRelevantUserSearchListResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetRelevantUserSearchListUseCase
import javax.inject.Inject

class GetRelevantUserSearchListUseCaseImpl @Inject constructor(val appRepository: AppRepository):
    GetRelevantUserSearchListUseCase {
    override suspend fun executeGetRelevantUserSearchList(
        search: String,
        page: Int,
        skipids: String
    ): GetRelevantUserSearchListResult {
        return appRepository.getRelevantUserSearchList(page = page, search = search, skipids = skipids)
    }
}