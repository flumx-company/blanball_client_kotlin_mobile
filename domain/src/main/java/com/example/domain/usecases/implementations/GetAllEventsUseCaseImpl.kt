package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetAllEventsResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetAllEventsUseCase
import javax.inject.Inject

class GetAllEventsUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetAllEventsUseCase {
    override suspend fun executeGetAllEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetAllEventsResultEntity {
        return appRepository.getAllEvents(
            page,
            typeOfSport = typeOfSport,
            gender = gender,
            time_and_date = time_and_date,
            ordering = ordering,
            filterDateAndTimeBefore = filterDateAndTimeBefore,
            filterDateAndTimeAfter = filterDateAndTimeAfter,
        )
    }
}