package com.example.domain.entity.responses

data class UpdateUserProfileResponseEntityError(
    val code: Int,
    val `data`: UpdateUserProfileResponseEntityErrorData,
    val message: String? = null,
    val status: String
)

data class UpdateUserProfileResponseEntityErrorData(
    val errors: List<UpdateUserProfileResponseEntityErrorDetail>,
    val type: String
)

data class UpdateUserProfileResponseEntityErrorDetail(
    val detail: String
)