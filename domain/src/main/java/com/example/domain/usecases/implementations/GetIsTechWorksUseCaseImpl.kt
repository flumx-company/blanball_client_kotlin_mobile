package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetIsTechnicalWorkStatusResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetIsTechWorksUseCase
import javax.inject.Inject

class GetIsTechWorksUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetIsTechWorksUseCase {
    override suspend fun executeGetIsTechWorks(): GetIsTechnicalWorkStatusResultEntity {
        return appRepository.getIsTechnicalWorkStatus()
    }
}