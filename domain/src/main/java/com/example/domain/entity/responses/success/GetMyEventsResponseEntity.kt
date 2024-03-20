package com.example.domain.entity.responses.success

data class GetMyEventsResponseEntity(
    val code: Int,
    val `data`: GetMyEventsResponseEntityData,
    val message: String? = null,
    val status: String
)

data class GetMyEventsResponseEntityData(
    val current_page: Int,
    val next: String?,
    val page_size: Int,
    val previous: Any? = null,
    val results: List<GetMyEventsResponseEntityResult>,
    val success: Boolean,
    val total_count: Int
)

data class GetMyEventsResponseEntityResult(
    val amount_members: Int? = null,
    val author: GetMyEventsResponseEntityAuthor,
    val count_current_fans: Int? = null,
    val count_current_users: Int? = null,
    val date_and_time: String,
    val description: String,
    val duration: Int,
    val forms: Any? = null,
    val gender: String,
    val id: Int,
    val name: String,
    val need_ball: Boolean,
    val need_form: Boolean,
    val pk_user_role: Any? = null,
    val place: GetMyEventsResponseEntityPlace,
    val price: Int? = null,
    val privacy: Boolean,
    val request_user_role: Any? = null,
    val status: String? = null,
    val type: String
)

data class GetMyEventsResponseEntityAuthor(
    val id: Int? = null,
    val phone: String,
    val profile: GetMyEventsResponseEntityProfile
)

data class GetMyEventsResponseEntityPlace(
    val lat: Double,
    val lon: Double,
    val place_name: String
)

data class GetMyEventsResponseEntityProfile(
    val avatar_url: Any? = null,
    val id: Int,
    val last_name: String,
    val name: String
)
