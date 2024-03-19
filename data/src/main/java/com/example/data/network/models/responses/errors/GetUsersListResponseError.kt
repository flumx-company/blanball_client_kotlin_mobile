package com.example.data.network.models.responses.errors

data class GetUsersListResponseError(
    val code: Int,
    val `data`: GetUsersListResponseErrorData,
    val message: String? = null,
    val status: String
)

data class GetUsersListResponseErrorData(
    val errors: List<GetUsersListResponseErrorDetailData>,
    val type: String
)

data class GetUsersListResponseErrorDetailData(
    val detail: String
)