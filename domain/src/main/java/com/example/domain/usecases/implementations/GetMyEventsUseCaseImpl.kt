package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetMyEventsResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetMyEventsUseCase
import javax.inject.Inject

class GetMyEventsUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetMyEventsUseCase {
    override suspend fun executeMyEventsEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String
    ): GetMyEventsResultEntity {
        return appRepository.getMyEvents(
            page = page,
            typeOfSport = typeOfSport,
            gender = gender,
            time_and_date = time_and_date,
        )
    }
}