package com.example.domain.entity.responses

data class GetRelevantUserSearchListResponseEntity(
    val code: Int,
    val `data`: GetRelevantUserSearchListResponseEntityData,
    val message: String? = null,
    val status: String
)

data class GetRelevantUserSearchListResponseEntityData(
    val current_page: Int,
    val next: Any? = null,
    val page_size: Int,
    val previous: Any? = null,
    val results: List<GetRelevantUserSearchListResponseEntityResult>,
    val success: Boolean,
    val total_count: Int
)

data class GetRelevantUserSearchListResponseEntityResult(
    val id: Int? = null,
    val is_online: Boolean? = null,
    val profile: GetRelevantUserSearchListResponseEntityProfile,
    val raiting: Any? = null,
    val role: String? = null
)

data class GetRelevantUserSearchListResponseEntityProfile(
    val age: Int? = null,
    val avatar_url: String? = null,
    val gender: String? = null,
    val id: Int? = null,
    val last_name: String,
    val name: String,
    val position: String? = null
)