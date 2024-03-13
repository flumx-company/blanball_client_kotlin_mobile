package com.example.data.network.models.requests

data class EditMyProfileRequest(
    val configuration: EditMyProfileRequestConfiguration,
    val phone: String,
    val profile: EditMyProfileRequestProfile
)

data class EditMyProfileRequestConfiguration(
    val email: Boolean,
    val phone: Boolean,
    val show_reviews: Boolean
)

data class EditMyProfileRequestProfile(
    val about_me: String?,
    val birthday: String,
    val gender: String,
    val height: Int?,
    val last_name: String,
    val name: String,
    val place: EditMyProfileRequestPlace,
    val position: String?,
    val weight: Int?,
    val working_leg: String?
)

data class EditMyProfileRequestPlace(
    val lat: Double,
    val lon: Double,
    val place_name: String
)