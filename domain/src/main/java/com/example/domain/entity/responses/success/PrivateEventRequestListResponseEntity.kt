package com.example.domain.entity.responses.success

data class GetPrivateRequestListResponseEntity(
    val code: Int,
    val `data`: GetPrivateRequestListResponseEntityData,
    val message: String? = null,
    val status: String
)

data class GetPrivateRequestListResponseEntityData(
    val current_page: Int,
    val next: Any,
    val page_size: Int,
    val previous: Any,
    val results: List<Any>,
    val success: Boolean,
    val total_count: Int
)