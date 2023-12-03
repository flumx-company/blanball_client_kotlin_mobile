package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetIsTechnicalWorkStatusResultEntity

interface GetIsTechWorksUseCase {
    suspend fun executeGetIsTechWorks(

    ) : GetIsTechnicalWorkStatusResultEntity
}