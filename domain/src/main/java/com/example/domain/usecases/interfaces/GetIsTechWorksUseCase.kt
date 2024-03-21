package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetIsTechnicalWorkStatusResult

interface GetIsTechWorksUseCase {
    suspend fun executeGetIsTechWorks(

    ) : GetIsTechnicalWorkStatusResult
}