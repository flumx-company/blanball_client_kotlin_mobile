package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetUkraineCitiesListResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetListOfUkraineCitiesUseCase
import javax.inject.Inject

class GetListOfUkraineCitiesUseCaseImpl @Inject constructor( val appRepository: AppRepository): GetListOfUkraineCitiesUseCase{
    override suspend fun executeGetListOfUkraineCities() : GetUkraineCitiesListResultEntity {
        return appRepository.getUkraineCitiesList()
    }
}