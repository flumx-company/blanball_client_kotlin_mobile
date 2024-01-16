package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUkraineCitiesListResultEntity

interface GetListOfUkraineCitiesUseCase {
    suspend fun executeGetListOfUkraineCities() : GetUkraineCitiesListResultEntity
}