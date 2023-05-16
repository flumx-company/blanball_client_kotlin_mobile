package com.example.domain.entity.responses

data class GetUserProfileByIdResponseEntity(
    val code: Int,
    val `data`: PublicProfileDataResponseEntity,
    val message: Any? = null,
    val status: String
)

data class PublicProfileDataResponseEntity(
    val configuration: ConfigurationEntity,
    val email: String,
    val id: Int,
    val is_online: Boolean,
    val is_verified: Boolean,
    val phone: String,
    val profile: PublicProfileResponseEntity,
    val raiting: Any? = null,
    val role: String
)

data class ConfigurationEntity(
    val email: Boolean,
    val phone: Boolean,
    val show_reviews: Boolean
)

data class PublicProfileResponseEntity(
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
    val place: PlayingPlaceEntity,
    val position: String,
    val weight: Int,
    val working_leg: String
)

data class PlayingPlaceEntity(
    val place_name: String
)