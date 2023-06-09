package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetUsersListResponse(
    @Json (name = "code" ) val code: Int,
    @Json (name = "`data`" ) val `data`: GetUsersListResponseData,
    @Json (name = "message" ) val message: String? = null,
    @Json (name = "status" ) val status: String
)

data class GetUsersListResponseData(
    @Json (name = "current_page" ) val current_page: Int,
    @Json (name = "next" ) val next: String? = null,
    @Json (name = "page_size" ) val page_size: Int,
    @Json (name = "previous" ) val previous: Any? = null,
    @Json (name = "results" ) val results: List<GetUsersListResponseResult>,
    @Json (name = "success" ) val success: Boolean,
    @Json (name = "total_count" ) val total_count: Int
)

data class GetUsersListResponseResult(
    @Json (name = "id" ) val id: Int,
    @Json (name = "is_online" ) val is_online: Boolean,
    @Json (name = "profile" ) val profile: GetUsersListResponseProfile,
    @Json (name = "raiting" ) val raiting: Any? = null,
    @Json (name = "role" ) val role: String? = null,
)

data class GetUsersListResponseProfile(
    @Json (name = "age" ) val age: Any? = null,
    @Json (name = "avatar_url" ) val avatar_url: Any,
    @Json (name = "gender" ) val gender: String? = null,
    @Json (name = "id" ) val id: Int,
    @Json (name = "last_name" ) val last_name: String,
    @Json (name = "name" ) val name: String,
    @Json (name = "position" ) val position: Any? = null
)
