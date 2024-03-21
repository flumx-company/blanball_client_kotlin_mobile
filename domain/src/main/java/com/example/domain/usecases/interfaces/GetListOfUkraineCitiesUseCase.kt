package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUkraineCitiesListResult

interface GetListOfUkraineCitiesUseCase {
    suspend fun executeGetListOfUkraineCities() : GetUkraineCitiesListResult
}