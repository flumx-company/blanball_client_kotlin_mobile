package com.example.data.backend.models.responses

data class GetUserProfileByIdResponse(
    val code: Int,
    val `data`: PublicProfileDataResponse,
    val message: Any,
    val status: String
)

data class PublicProfileDataResponse(
    val configuration: Configuration,
    val email: String,
    val id: Int,
    val is_online: Boolean,
    val is_verified: Boolean,
    val phone: String,
    val profile: PublicProfileResponse,
    val raiting: Any,
    val role: String
)

data class Configuration(
    val email: Boolean,
    val phone: Boolean,
    val show_reviews: Boolean
)

data class PublicProfileResponse(
    val about_me: String,
    val age: Int,
    val avatar_url: String,
    val birthday: String,
    val created_at: String,
    val gender: String,
    val height: Int,
    val id: Int,
    val last_name: String,
    val name: String,
    val place: PlayingPlace,
    val position: String,
    val weight: Int,
    val working_leg: String
)

data class PlayingPlace(
    val place_name: String
)