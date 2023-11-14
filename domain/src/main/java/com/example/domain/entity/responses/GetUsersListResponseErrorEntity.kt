package com.example.domain.entity.responses

data class GetUsersListResponseErrorEntity(
    val code: Int,
    val `data`: GetUsersListResponseErrorDataEntity ,
    val message: String? = null,
    val status: String
)

data class GetUsersListResponseErrorDataEntity (
    val errors: List<GetUsersListResponseErrorDetailDataEntity>,
    val type: String
)

data class GetUsersListResponseErrorDetailDataEntity(
    val detail: String
)