package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetEventByIdResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: GetEventByIdResponseData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class GetEventByIdResponseData(
    @Json(name = "amount_members") val amount_members: Int,
    @Json(name = "author") val author: GetEventByIdResponseAuthor,
    @Json(name = "contact_number") val contact_number: String,
    @Json(name = "coordinates") val coordinates: GetEventByIdResponseCoordinates,
    @Json(name = "current_fans") val current_fans: List<Any>,
    @Json(name = "current_users") val current_users: List<Any>,
    @Json(name = "date_and_time") val date_and_time: String,
    @Json(name = "description") val description: String,
    @Json(name = "duration") val duration: Int,
    @Json(name = "forms") val forms: GetEventByIdResponseForms,
    @Json(name = "gender") val gender: String,
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "need_ball") val need_ball: Boolean,
    @Json(name = "need_form") val need_form: Boolean,
    @Json(name = "place") val place: GetEventByIdResponsePlace,
    @Json(name = "price") val price: Int,
    @Json(name = "price_description") val price_description: String,
    @Json(name = "privacy") val privacy: Boolean,
    @Json(name = "request_user_role") val request_user_role: String,
    @Json(name = "status") val status: String,
    @Json(name = "type") val type: String
)

data class GetEventByIdResponseAuthor(
    @Json(name = "id") val id: Int,
    @Json(name = "phone") val phone: String,
    @Json(name = "profile") val profile: GetEventByIdResponseProfile
)

data class GetEventByIdResponseCoordinates(
    @Json(name = "coordinates") val coordinates: List<Int>,
    @Json(name = "type") val type: String
)

class GetEventByIdResponseForms  // TODO("Not implemented on the backend")

data class GetEventByIdResponsePlace(
    @Json(name = "lat") val lat: Int,
    @Json(name = "lon") val lon: Int,
    @Json(name = "place_name") val place_name: String
)

data class GetEventByIdResponseProfile(
    @Json(name = "avatar_url") val avatar_url: Any,
    @Json(name = "id") val id: Int,
    @Json(name = "last_name") val last_name: String,
    @Json(name = "name") val name: String
)