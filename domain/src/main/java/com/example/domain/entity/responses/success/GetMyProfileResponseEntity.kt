package com.example.domain.entity.responses.success

data class GetMyProfileResponseEntity(
    val code: Int,
    val `data`: GetMyProfileResponseDataEntity,
    val message: String? = null,
    val status: String
)

data class GetMyProfileResponseDataEntity(
    val configuration: GetMyProfileResponseConfigurationEntity,
    val email: String? = null,
    val id: Int,
    val is_online: Boolean,
    val is_verified: Boolean,
    val phone: String? = null,
    val profile: GetMyProfileResponseProfileEntity,
    val raiting: Any? = null,
    val role: String? = null
)

data class GetMyProfileResponseConfigurationEntity(
     val email: Boolean,
     val phone: Boolean,
     val show_reviews: Boolean
)

data class GetMyProfileResponseProfileEntity(
    val about_me: String? = null,
    val age: Int? = null,
    val avatar_url: String? = null,
    val birthday: String? = null,
    val created_at: String? = null,
    val gender: String? = null,
    val height: Int? = null,
    val id: Int,
    val last_name: String,
    val name: String,
    val place: GetMyProfileResponsePlaceEntity? = null,
    val position: String? = null,
    val weight: Int? = null,
    val working_leg: String? = null,
)

data class GetMyProfileResponsePlaceEntity(
     val place_name: String? = null
)