package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetUserProfileByIdResponse(
     @Json (name = "code") val code: Int,
     @Json (name = "`data`") val `data`: PublicProfileDataResponse,
     @Json (name = "message") val message: Any? = null,
     @Json (name = "status") val status: String
)

data class PublicProfileDataResponse(
     @Json (name = "configuration") val configuration: Configuration,
     @Json (name = "email") val email: String,
     @Json (name = "id") val id: Int,
     @Json (name = "is_online") val is_online: Boolean,
     @Json (name = "is_verified") val is_verified: Boolean,
     @Json (name = "phone") val phone: String,
     @Json (name = "profile") val profile: PublicProfileResponse,
     @Json (name = "raiting") val raiting: Any? = null,
     @Json (name = "role") val role: String
)

data class Configuration(
     @Json (name = "email") val email: Boolean,
     @Json (name = "phone") val phone: Boolean,
     @Json (name = "show_reviews") val show_reviews: Boolean
)

data class PublicProfileResponse(
     @Json (name = "about_me") val about_me: String,
     @Json (name = "age") val age: Int,
     @Json (name = "avatar_url") val avatar_url: String? = null,
     @Json (name = "birthday") val birthday: String,
     @Json (name = "created_at") val created_at: String,
     @Json (name = "gender") val gender: String,
     @Json (name = "height") val height: Int,
     @Json (name = "id") val id: Int,
     @Json (name = "last_name") val last_name: String,
     @Json (name = "name") val name: String,
     @Json (name = "place") val place: PlayingPlace,
     @Json (name = "position") val position: String,
     @Json (name = "weight") val weight: Int,
     @Json (name = "working_leg") val working_leg: String
)

data class PlayingPlace(
     @Json (name = "place_name") val place_name: String
)