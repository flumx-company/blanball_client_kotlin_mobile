package com.example.domain.entity.responses.success

data class GetUsersListResponseEntity(
    val code: Int,
    val `data`: GetUsersListResponseDataEntity,
    val message: String? = null,
    val status: String
)

data class GetUsersListResponseDataEntity(
    val current_page: Int,
    val next: String? = null,
    val page_size: Int,
    val previous: String? = null,
    val results: List<GetUsersListResponseResultEntity>? = null,
    val success: Boolean,
    val total_count: Int
)

data class GetUsersListResponseResultEntity(
    val id: Int,
    val is_online: Boolean,
    val profile: GetUsersListResponseProfileEntity,
    val raiting: Any? = null,
    val role: String? = null,
)

data class GetUsersListResponseProfileEntity(
    val age: Any? = null,
    val avatar_url: String? = null,
    val gender: String? = null,
    val id: Int,
    val last_name: String,
    val name: String,
    val position: Any? = null
)