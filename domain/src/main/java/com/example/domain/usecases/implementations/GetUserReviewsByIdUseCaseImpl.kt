package com.example.domain.usecases.implementations

import android.util.Log
import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetUserReviewsByIdUseCase
import javax.inject.Inject

class GetUserReviewsByIdUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetUserReviewsByIdUseCase {
    override suspend fun executeGetUserReviewsById(): GetUserReviewsByIdResultEntity {
        val result = appRepository.getUserReviewsById(2)
        Log.d("MyLOG", result.toString())
        return result
    }
}