package com.example.domain.entity.responses


data class UpdateUserProfileResponseEntity(
    val code: Int,
    val `data`: UpdateUserProfileResponseDataEntity,
    val message: String? = null,
    val status: String
)

data class UpdateUserProfileResponseDataEntity(
    val configuration: UpdateUserProfileResponseConfigurationEntity,
    val phone: String,
    val profile: UpdateUserProfileResponseProfileEntity
)

data class UpdateUserProfileResponseConfigurationEntity(
    val email: Boolean,
    val phone: Boolean,
    val show_reviews: Boolean
)

data class UpdateUserProfileResponseProfileEntity(
    val birthday: String,
    val height: Int,
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