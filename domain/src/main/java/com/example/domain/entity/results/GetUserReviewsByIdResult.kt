package com.example.domain.entity.results

import com.example.domain.entity.responses.success.GetUserReviewsByIdDataEntity
import com.example.domain.entity.responses.errors.GetUserReviewsByIdResponseDetailDataEntity

sealed class GetUserReviewsByIdResult() {
    data class Success(val data: GetUserReviewsByIdDataEntity) : GetUserReviewsByIdResult()
    data class Error(val error: GetUserReviewsByIdResponseDetailDataEntity) : GetUserReviewsByIdResult()
}
