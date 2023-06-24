package com.example.data.backend.models.requests

data class UpdateUserProfileRequest(
    val configuration: UpdateUserProfileRequestConfiguration,
    val phone: String,
    val profile: UpdateUserProfileRequestProfile
)

data class UpdateUserProfileRequestConfiguration(
    val email: Boolean,
    val phone: Boolean,
    val show_reviews: Boolean
)

data class UpdateUserProfileRequestProfile(
    val about_me: String? = null,
    val birthday: String? = null,
    val gender: String? = null,
    val height: Int? = null,
    val last_name: String,
    val name: String,
    val place: UpdateUserProfileRequestPlace? = null,
    val position: String? = null,
    val weight: Int? = null,
    val working_leg: String? = null
)

data class UpdateUserProfileRequestPlace(
    val lat: Int,
    val lon: Int,
    val place_name: String
)