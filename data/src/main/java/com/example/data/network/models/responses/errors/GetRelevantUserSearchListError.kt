package com.example.data.network.models.responses.errors

data class GetRelevantUserSearchListError(
    val code: Int,
    val `data`: GetRelevantUserSearchListErrorData,
    val message: Any,
    val status: String
)

data class GetRelevantUserSearchListErrorData(
    val errors: List<GetRelevantUserSearchListErrorDetail>,
    val type: String
)

data class GetRelevantUserSearchListErrorDetail(
    val detail: String
)