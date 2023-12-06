package com.example.data.backend.models.responses.success

data class GetRelevantUserSearchListResponse(
    val code: Int,
    val `data`: GetRelevantUserSearchListResponseData,
    val message: Any,
    val status: String
)

data class GetRelevantUserSearchListResponseData(
    val current_page: Int,
    val next: Any,
    val page_size: Int,
    val previous: Any,
    val results: List<GetRelevantUserSearchListResponseResult>? = null,
    val success: Boolean,
    val total_count: Int
)

data class GetRelevantUserSearchListResponseResult(
    val id: Int,
    val is_online: Boolean,
    val profile: GetRelevantUserSearchListResponseProfile,
    val raiting: Any,
    val role: String
)

data class GetRelevantUserSearchListResponseProfile(
    val age: Int,
    val avatar_url: Any,
    val gender: String,
    val id: Int,
    val last_name: String,
    val name: String,
    val position: String
)