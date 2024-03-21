package com.example.domain.entity.responses.errors

data class GetPrivateRequestListResponseErrorEntity(
    val code: Int,
    val `data`: GetPrivateRequestListResponseErrorEntityData,
    val message: Any? = null,
    val status: String
)

data class GetPrivateRequestListResponseErrorEntityData(
    val errors: List<GetPrivateRequestListResponseErrorDetailEntity>,
    val type: String
)

data class GetPrivateRequestListResponseErrorDetailEntity(
    val detail: String
)