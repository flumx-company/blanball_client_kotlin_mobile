package com.example.domain.entity.responses

data class EditMyProfileResponseEntity(
    val code: Int,
    val `data`: EditMyProfileResponseEntityData,
    val message: String? = null,
    val status: String
)

data class EditMyProfileResponseEntityData(
    val configuration: EditMyProfileResponseEntityConfiguration? = null,
    val phone: String? = null,
    val profile: EditMyProfileResponseEntityProfile? = null
)

data class EditMyProfileResponseEntityConfiguration(
    val email: Boolean,
    val phone: Boolean? = null,
    val show_reviews: Boolean
)

data class EditMyProfileResponseEntityProfile(
    val about_me: String,
    val birthday: String,
    val gender: String,
    val height: Int,
    val last_name: String? = null,
    val name: String? = null,
    val place: EditMyProfileResponseEntityPlace? = null,
    val position: String,
    val weight: Int,
    val working_leg: String
)

data class EditMyProfileResponseEntityPlace(
    val lat: Int? = null,
    val lon: Int? = null,
    val place_name: String? = null
)