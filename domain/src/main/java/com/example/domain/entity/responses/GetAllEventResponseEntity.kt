package com.example.domain.entity.responses

data class GetAllEventResponseEntity(
    val code: Int,
    val `data`: GetAllEventResponseEntityData,
    val message: String? = null,
    val status: String
)

data class GetAllEventResponseEntityData(
    val current_page: Int,
    val next: String?,
    val page_size: Int,
    val previous: Any? = null,
    val results: List<GetAllEventResponseEntityResult>,
    val success: Boolean,
    val total_count: Int
)

data class GetAllEventResponseEntityResult(
    val amount_members: Int? = null,
    val author: GetAllEventResponseEntityAuthor,
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
    val place: GetAllEventResponseEntityPlace,
    val price: Int? = null,
    val privacy: Boolean,
    val request_user_role: Any? = null,
    val status: String? = null,
    val type: String
)

data class GetAllEventResponseEntityAuthor(
    val id: Int? = null,
    val phone: String,
    val profile: GetAllEventResponseEntityProfile
)

data class GetAllEventResponseEntityPlace(
    val lat: Double,
    val lon: Double,
    val place_name: String
)

data class GetAllEventResponseEntityProfile(
    val avatar_url: Any? = null,
    val id: Int,
    val last_name: String,
    val name: String
)