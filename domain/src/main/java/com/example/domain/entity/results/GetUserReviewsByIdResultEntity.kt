package com.example.domain.entity.results

import com.example.domain.entity.responses.GetUserReviewsByIdDataEntity
import com.example.domain.entity.responses.errors.GetUserReviewsByIdResponseDetailDataEntity

sealed class GetUserReviewsByIdResultEntity() {
    data class Success(val data: GetUserReviewsByIdDataEntity) : GetUserReviewsByIdResultEntity()
    data class Error(val error: GetUserReviewsByIdResponseDetailDataEntity) : GetUserReviewsByIdResultEntity()
}
