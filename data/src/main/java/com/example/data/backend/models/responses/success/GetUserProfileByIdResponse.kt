package com.example.data.backend.models.responses.success

import com.squareup.moshi.Json

data class GetUserProfileByIdResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: PublicProfileDataResponse,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
)

data class PublicProfileDataResponse(
    @Json (name = "configuration") val configuration: Configuration,
    @Json (name = "email") val email: String? = null,
    @Json (name = "id") val id: Int,
    @Json (name = "is_online") val is_online: Boolean,
    @Json (name = "is_verified") val is_verified: Boolean,
    @Json (name = "phone") val phone: String? = null,
    @Json (name = "profile") val profile: PublicProfileResponse,
    @Json (name = "raiting") val raiting: Any? = null,
    @Json (name = "role") val role: String? = null
)

data class Configuration(
     @Json (name = "email") val email: Boolean,
     @Json (name = "phone") val phone: Boolean,
     @Json (name = "show_reviews") val show_reviews: Boolean
)

data class PublicProfileResponse(
    @Json (name = "about_me") val about_me: String? = null,
    @Json (name = "age") val age: Int? = null,
    @Json (name = "avatar_url") val avatar_url: String? = null,
    @Json (name = "birthday") val birthday: String? = null,
    @Json (name = "created_at") val created_at: String,
    @Json (name = "gender") val gender: String? = null,
    @Json (name = "height") val height: Int? = null,
    @Json (name = "id") val id: Int,
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String,
    @Json (name = "place") val place: PlayingPlace? = null,
    @Json (name = "position") val position: String? = null,
    @Json (name = "weight") val weight: Int? = null,
    @Json (name = "working_leg") val working_leg: String? = null,
)

data class PlayingPlace(
     @Json (name = "place_name") val place_name: String? = null,
)