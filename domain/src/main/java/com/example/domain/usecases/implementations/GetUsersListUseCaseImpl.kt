package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetUsersListResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetUsersListUseCase
import javax.inject.Inject

class GetUsersListUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetUsersListUseCase {
    override suspend fun executeGetUsersList(page: Int, gender: String?, age_min: Int?, age_max: Int?, ordering: String?, position: String?): GetUsersListResultEntity {
        return appRepository.getUsersList(
            page = page,
            gender = gender,
            age_min = age_min,
            age_max = age_max,
            ordering = ordering,
            position = position,
        )
    }
}