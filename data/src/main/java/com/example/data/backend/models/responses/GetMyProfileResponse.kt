package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetMyProfileResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetMyProfileResponseData,
    @Json (name = "message") val message: Any,
    @Json (name = "status") val status: String
)

data class GetMyProfileResponseData(
    @Json (name = "configuration") val configuration: GetMyProfileResponseConfiguration,
    @Json (name = "email") val email: String,
    @Json (name = "id") val id: Int,
    @Json (name = "is_online") val is_online: Boolean,
    @Json (name = "is_verified") val is_verified: Boolean,
    @Json (name = "phone") val phone: String,
    @Json (name = "profile") val profile: GetMyProfileResponseProfile,
    @Json (name = "raiting") val raiting: Any,
    @Json (name = "role") val role: String
)

data class GetMyProfileResponseConfiguration(
    @Json (name = "email") val email: Boolean,
    @Json (name = "phone") val phone: Boolean,
    @Json (name = "show_reviews") val show_reviews: Boolean
)

data class GetMyProfileResponseProfile(
    @Json (name = "about_me") val about_me: String,
    @Json (name = "age") val age: Int,
    @Json (name = "avatar_url") val avatar_url: Any,
    @Json (name = "birthday") val birthday: Any,
    @Json (name = "created_at") val created_at: String,
    @Json (name = "gender") val gender: String,
    @Json (name = "height") val height: Int,
    @Json (name = "id") val id: Int,
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String,
    @Json (name = "place") val place: GetMyProfileResponsePlace,
    @Json (name = "position") val position: String,
    @Json (name = "weight") val weight: Int,
    @Json (name = "working_leg") val working_leg: String
)

data class GetMyProfileResponsePlace(
    @Json (name = "place_name") val place_name: String
)