package com.example.domain.entity.responses.errors

data class GetRelevantUserSearchListErrorEntity(
    val code: Int,
    val `data`: GetRelevantUserSearchListErrorEntityData,
    val message: Any,
    val status: String
)

data class GetRelevantUserSearchListErrorEntityData(
    val errors: List<GetRelevantUserSearchListErrorEntityDetail>,
    val type: String
)

data class GetRelevantUserSearchListErrorEntityDetail(
    val detail: String
)