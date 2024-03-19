package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class GetEventByIdResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetEventByIdResponseData,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
)

data class GetEventByIdResponseData(
    @Json (name = "amount_members") val amount_members: Int? = null,
    @Json (name = "author") val author: GetEventByIdResponseAuthor,
    @Json (name = "contact_number") val contact_number: String? = null,
    @Json (name = "coordinates") val coordinates: GetEventByIdResponseCoordinates,
    @Json (name = "current_fans") val current_fans: List<GetEventByIdResponseCurrentFan>,
    @Json (name = "current_users") val current_users: List<GetEventByIdResponseCurrentUser>,
    @Json (name = "date_and_time") val date_and_time: String,
    @Json (name = "description") val description: String,
    @Json (name = "duration") val duration: Int,
    @Json (name = "forms") val forms: GetEventByIdResponseForms? = null,
    @Json (name = "gender") val gender: String,
    @Json (name = "id") val id: Int,
    @Json (name = "name") val name: String,
    @Json (name = "need_ball") val need_ball: Boolean,
    @Json (name = "need_form") val need_form: Boolean,
    @Json (name = "place") val place: GetEventByIdResponsePlace,
    @Json (name = "price") val price: Int? = null,
    @Json (name = "price_description") val price_description: String? = null,
    @Json (name = "privacy") val privacy: Boolean,
    @Json (name = "request_user_role") val request_user_role: String? = null,
    @Json (name = "status") val status: String? = null,
    @Json (name = "type") val type: String
)

data class GetEventByIdResponseAuthor(
    @Json (name = "id") val id: Int,
    @Json (name = "phone") val phone: String,
    @Json (name = "profile") val profile: GetEventByIdResponseProfile
)

data class GetEventByIdResponseCoordinates(
    @Json (name = "coordinates") val coordinates: List<Float>? = null,
    @Json (name = "type") val type: String
)

class GetEventByIdResponseForms

data class GetEventByIdResponsePlace(
    @Json (name = "lat") val lat: Double,
    @Json (name = "lon") val lon: Double,
    @Json (name = "place_name") val place_name: String
)

data class GetEventByIdResponseProfile(
    @Json (name = "avatar_url") val avatar_url: String? = null,
    @Json (name = "id") val id: Int,
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String
)

data class GetEventByIdResponseCurrentFan(
    @Json (name = "id") val id: Int,
    @Json (name = "profile") val profile: GetEventByIdResponseFanProfileX,
    @Json (name = "raiting") val raiting: Int? = null,
)

data class GetEventByIdResponseCurrentUser(
    @Json (name = "id") val id: Int,
    @Json (name = "profile") val profile: GetEventByIdResponsePlayerProfileX,
    @Json (name = "raiting") val raiting: Int? = null,
)

data class GetEventByIdResponsePlayerProfileX(
    @Json (name = "avatar_url") val avatar_url: String? = null,
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String,
    @Json (name = "position") val position: String? = null,
    @Json (name = "working_leg") val working_leg: String? = null
)
data class GetEventByIdResponseFanProfileX(
    @Json (name = "avatar_url") val avatar_url: String? = null,
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String,
    @Json (name = "position") val position: String? = null,
    @Json (name = "working_leg") val working_leg: String? = null
)