package com.example.data.backend.models.responses.errors

data class ErrorResponseDto<T>(
    val code: Int,
    val `data`: ErrorResponseDtoData<T>,
    val message: String? = null,
    val status: String
)

data class ErrorResponseDtoData<T>(
    val errors: List<ErrorResponseDtoDetail<T>>,
    val type: String
)

data class ErrorResponseDtoDetail<T>(
    val detail: String
)