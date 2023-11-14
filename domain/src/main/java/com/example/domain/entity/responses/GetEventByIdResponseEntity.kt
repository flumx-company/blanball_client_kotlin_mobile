package com.example.domain.entity.responses

data class GetEventByIdResponseEntity(
    val code: Int,
    val `data`: GetEventByIdResponseDataEntity,
    val message: String? = null,
    val status: String
)

data class GetEventByIdResponseDataEntity(
    val amount_members: Int,
    val author: GetEventByIdResponseAuthorEntity,
    val contact_number: String,
    val coordinates: GetEventByIdResponseCoordinatesEntity,
    val current_fans: List<Any>,
    val current_users: List<Any>,
    val date_and_time: String,
    val description: String,
    val duration: Int,
    val forms: GetEventByIdResponseFormsEntity,
    val gender: String,
    val id: Int,
    val name: String,
    val need_ball: Boolean,
    val need_form: Boolean,
    val place: GetEventByIdResponsePlaceEntity,
    val price: Int,
    val price_description: String,
    val privacy: Boolean,
    val request_user_role: String,
    val status: String,
    val type: String
)

data class GetEventByIdResponseAuthorEntity(
    val id: Int,
    val phone: String,
    val profile: GetEventByIdResponseProfileEntity
)

data class GetEventByIdResponseCoordinatesEntity(
    val coordinates: List<Int>,
    val type: String
)

class GetEventByIdResponseFormsEntity // TODO("Not implemented on the backend")

data class GetEventByIdResponsePlaceEntity(
    val lat: Int,
    val lon: Int,
    val place_name: String
)

data class GetEventByIdResponseProfileEntity(
    val avatar_url: Any,
    val id: Int,
    val last_name: String,
    val name: String
)