package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetUsersListResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetUsersListUseCase
import javax.inject.Inject

class GetUsersListUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetUsersListUseCase {
    override suspend fun executeGetUsersList(page: Int): GetUsersListResultEntity {
        return appRepository.getUsersList(page)
    }
}