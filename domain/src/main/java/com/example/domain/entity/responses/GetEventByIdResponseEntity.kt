package com.example.domain.entity.responses

data class GetEventByIdResponseEntity(
    val code: Int,
    val `data`: GetEventByIdResponseDataEntity,
    val message: String? = null,
    val status: String
)

data class GetEventByIdResponseDataEntity(
    val amount_members: Int? = null,
    val author: GetEventByIdResponseAuthorEntity,
    val contact_number: String? = null,
    val coordinates: GetEventByIdResponseCoordinatesEntity,
    val current_fans: List<GetEventByIdResponseCurrentUserEntity>,
    val current_users: List<GetEventByIdResponseCurrentFanEntity>,
    val date_and_time: String,
    val description: String,
    val duration: Int,
    val forms: GetEventByIdResponseFormsEntity? = null,
    val gender: String,
    val id: Int,
    val name: String,
    val need_ball: Boolean,
    val need_form: Boolean,
    val place: GetEventByIdResponsePlaceEntity,
    val price: Int? = null,
    val price_description: String? = null,
    val privacy: Boolean,
    val request_user_role: String? = null,
    val status: String? = null,
    val type: String
)

data class GetEventByIdResponseAuthorEntity(
    val id: Int,
    val phone: String,
    val profile: GetEventByIdResponseProfileEntity
)

data class GetEventByIdResponseCoordinatesEntity(
    val coordinates: List<Float>? = null,
    val type: String
)

class GetEventByIdResponseFormsEntity

data class GetEventByIdResponsePlaceEntity(
    val lat: Double,
    val lon: Double,
    val place_name: String
)

data class GetEventByIdResponseProfileEntity(
    val avatar_url: String? = null,
    val id: Int,
    val last_name: String,
    val name: String
)

data class GetEventByIdResponseCurrentFanEntity(
    val id: Int,
    val profile: GetEventByIdResponseProfileXEntity,
    val raiting: Int? = null,
)

data class GetEventByIdResponseCurrentUserEntity(
    val id: Int,
    val profile: GetEventByIdResponseProfileXEntity,
    val raiting: Int? = null
)

data class GetEventByIdResponseProfileXEntity(
    val avatar_url: String? = null,
    val last_name: String,
    val name: String,
    val position: String? = null,
    val working_leg: String? = null
)