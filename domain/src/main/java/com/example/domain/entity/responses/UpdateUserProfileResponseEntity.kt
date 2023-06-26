package com.example.domain.entity.responses

data class UpdateUserProfileResponseEntity(
    val configuration: UpdateUserProfileResponseConfigurationEntity,
    val get_planned_events: String? = null,
    val phone: String,
    val profile: UpdateUserProfileResponseProfileEntity
)

data class UpdateUserProfileResponseConfigurationEntity(
    val email: Boolean,
    val phone: Boolean,
    val show_reviews: Boolean
)

data class UpdateUserProfileResponseProfileEntity(
    val about_me: String,
    val birthday: String,
    val gender: String,
    val height: Int,
    val id: Int,
    val last_name: String,
    val name: String,
    val place: UpdateUserProfileResponsePlaceEntity,
    val position: String,
    val weight: Int,
    val working_leg: String
)

data class UpdateUserProfileResponsePlaceEntity(
    val lat: Int,
    val lon: Int,
    val place_name: String
)