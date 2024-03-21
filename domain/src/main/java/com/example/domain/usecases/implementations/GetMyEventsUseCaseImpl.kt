package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetMyEventsResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetMyEventsUseCase
import javax.inject.Inject

class GetMyEventsUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetMyEventsUseCase {
    override suspend fun executeGetMyEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetMyEventsResult {
        return appRepository.getMyEvents(
            page = page,
            typeOfSport = typeOfSport,
            gender = gender,
            time_and_date = time_and_date,
            ordering = ordering,
            filterDateAndTimeBefore = filterDateAndTimeBefore,
            filterDateAndTimeAfter = filterDateAndTimeAfter,
        )
    }
}