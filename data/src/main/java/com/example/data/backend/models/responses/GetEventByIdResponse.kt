package com.example.data.backend.models.responses

data class GetEventByIdResponse(
    val code: Int,
    val `data`: GetEventByIdResponseData,
    val message: String? = null,
    val status: String
)

data class GetEventByIdResponseData(
    val amount_members: Int,
    val author: GetEventByIdResponseAuthor,
    val contact_number: String,
    val coordinates: GetEventByIdResponseCoordinates,
    val current_fans: List<Any>,
    val current_users: List<Any>,
    val date_and_time: String,
    val description: String,
    val duration: Int,
    val forms: GetEventByIdResponseForms,
    val gender: String,
    val id: Int,
    val name: String,
    val need_ball: Boolean,
    val need_form: Boolean,
    val place: GetEventByIdResponsePlace,
    val price: Int,
    val price_description: String,
    val privacy: Boolean,
    val request_user_role: String,
    val status: String,
    val type: String
)

data class GetEventByIdResponseAuthor(
    val id: Int,
    val phone: String,
    val profile: GetEventByIdResponseProfile
)

data class GetEventByIdResponseCoordinates(
    val coordinates: List<Int>,
    val type: String
)

class GetEventByIdResponseForms

data class GetEventByIdResponsePlace(
    val lat: Int,
    val lon: Int,
    val place_name: String
)

data class GetEventByIdResponseProfile(
    val avatar_url: Any,
    val id: Int,
    val last_name: String,
    val name: String
)