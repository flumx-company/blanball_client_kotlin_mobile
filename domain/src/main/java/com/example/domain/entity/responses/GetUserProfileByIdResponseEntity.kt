package com.example.domain.entity.responses

data class GetUserProfileByIdResponseEntity(
    val code: Int,
    val `data`: PublicProfileDataResponseEntity,
    val message: String? = null,
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
    val role: String? = null,
)

data class ConfigurationEntity(
    val email: Boolean,
    val phone: Boolean,
    val show_reviews: Boolean
)

data class PublicProfileResponseEntity(
    val about_me: String? = null,
    val age: Int? = null,
    val avatar_url: String? = null,
    val birthday: String? = null,
    val created_at: String,
    val gender: String? = null,
    val height: Int? = null,
    val id: Int,
    val last_name: String,
    val name: String,
    val place: PlayingPlaceEntity,
    val position: String? = null,
    val weight: Int? = null,
    val working_leg: String? = null,
)

data class PlayingPlaceEntity(
    val place_name: String
)