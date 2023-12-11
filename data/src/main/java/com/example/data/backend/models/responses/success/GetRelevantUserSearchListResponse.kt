package com.example.data.backend.models.responses.success

data class GetRelevantUserSearchListResponse(
    val code: Int,
    val `data`: GetRelevantUserSearchListResponseData,
    val message: String? = null,
    val status: String
)

data class GetRelevantUserSearchListResponseData(
    val current_page: Int,
    val next: Any? = null,
    val page_size: Int,
    val previous: Any? = null,
    val results: List<GetRelevantUserSearchListResponseResult>,
    val success: Boolean,
    val total_count: Int
)

data class GetRelevantUserSearchListResponseResult(
    val id: Int? = null,
    val is_online: Boolean? = null,
    val profile: GetRelevantUserSearchListResponseProfile,
    val raiting: Any? = null,
    val role: String? = null
)

data class GetRelevantUserSearchListResponseProfile(
    val age: Int? = null,
    val avatar_url: String? = null,
    val gender: String? = null,
    val id: Int? = null,
    val last_name: String,
    val name: String,
    val position: String? = null
)