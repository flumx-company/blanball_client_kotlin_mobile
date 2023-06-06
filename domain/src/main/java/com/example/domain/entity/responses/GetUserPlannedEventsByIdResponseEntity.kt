package com.example.domain.entity.responses

data class GetUserPlannedEventsByIdResponseEntity(
    val code: Int,
    val `data`: GetUserPlannedEventsByIdDataResponseEntity,
    val message: String? = null,
    val status: String
)

data class GetUserPlannedEventsByIdDataResponseEntity(
    val current_page: Int,
    val next: String? = null,
    val page_size: Int,
    val previous: String? = null,
    val results: List<GetUserPlannedEventsByIdResultResponseEntity>?,
    val success: Boolean,
    val total_count: Int
)

data class GetUserPlannedEventsByIdResultResponseEntity(
    val amount_members: Int,
    val author: GetUserPlannedEventsByIdAuthorResponseEntity,
    val count_current_fans: Int,
    val count_current_users: Int,
    val date_and_time: String,
    val description: String,
    val duration: Int,
    val gender: String,
    val id: Int,
    val name: String,
    val need_ball: Boolean,
    val need_form: Boolean,
    val pk_user_role: String,
    val place: GetUserPlannedEventsByIdPlaceResponseEntity,
    val price: Int? = null,
    val privacy: Boolean,
    val request_user_role: String? = null,
    val status: String,
    val type: String
)

data class GetUserPlannedEventsByIdAuthorResponseEntity(
    val id: Int,
    val phone: String,
    val profile: GetUserPlannedEventsByIdProfileResponseEntity
)

data class GetUserPlannedEventsByIdPlaceResponseEntity(
    val lat: Int,
    val lon: Int,
    val place_name: String
)

data class GetUserPlannedEventsByIdProfileResponseEntity(
    val avatar_url: Any? = null,
    val id: Int,
    val last_name: String,
    val name: String
)
