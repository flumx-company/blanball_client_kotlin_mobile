package com.example.data.backend.models.requests

data class UpdateUserProfileRequest(
    val configuration: UpdateUserProfileRequestConfiguration,
    val get_planned_events: String,
    val phone: String,
    val profile: UpdateUserProfileRequestProfile
)

data class UpdateUserProfileRequestConfiguration(
    val email: Boolean,
    val phone: Boolean,
    val show_reviews: Boolean
)

data class UpdateUserProfileRequestProfile(
    val about_me: String,
    val birthday: String,
    val gender: String,
    val height: Int,
    val last_name: String,
    val name: String,
    val place: UpdateUserProfileRequestPlace,
    val position: String,
    val weight: Int,
    val working_leg: String
)

data class UpdateUserProfileRequestPlace(
    val lat: Int,
    val lon: Int,
    val place_name: String
)