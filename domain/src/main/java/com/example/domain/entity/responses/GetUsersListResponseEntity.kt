package com.example.domain.entity.responses


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
    val previous: Any? = null,
    val results: List<GetUsersListResponseResultEntity>,
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
    val avatar_url: Any,
    val gender: String? = null,
    val id: Int,
    val last_name: String,
    val name: String,
    val position: Any? = null
)