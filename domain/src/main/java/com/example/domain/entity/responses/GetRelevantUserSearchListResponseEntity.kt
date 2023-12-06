package com.example.domain.entity.responses

data class GetRelevantUserSearchListResponseEntity(
    val code: Int,
    val `data`: GetRelevantUserSearchListResponseEntityData,
    val message: Any,
    val status: String
)

data class GetRelevantUserSearchListResponseEntityData(
    val current_page: Int,
    val next: Any,
    val page_size: Int,
    val previous: Any,
    val results: List<GetRelevantUserSearchListResponseEntityResult>? = null,
    val success: Boolean,
    val total_count: Int
)

data class GetRelevantUserSearchListResponseEntityResult(
    val id: Int,
    val is_online: Boolean,
    val profile: GetRelevantUserSearchListResponseEntityProfile,
    val raiting: Any,
    val role: String
)

data class GetRelevantUserSearchListResponseEntityProfile(
    val age: Int,
    val avatar_url: Any,
    val gender: String,
    val id: Int,
    val last_name: String,
    val name: String,
    val position: String
)