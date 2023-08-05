package com.example.domain.entity.responses

data class GetMyProfileResponseEntity(
     val code: Int,
     val `data`: GetMyProfileResponseDataEntity,
     val message: Any,
     val status: String
)

data class GetMyProfileResponseDataEntity(
     val configuration: GetMyProfileResponseConfigurationEntity,
     val email: String,
     val id: Int,
     val is_online: Boolean,
     val is_verified: Boolean,
     val phone: String,
     val profile: GetMyProfileResponseProfileEntity,
     val raiting: Any,
     val role: String
)

data class GetMyProfileResponseConfigurationEntity(
     val email: Boolean,
     val phone: Boolean,
     val show_reviews: Boolean
)

data class GetMyProfileResponseProfileEntity(
     val about_me: String,
     val age: Int,
     val avatar_url: Any,
     val birthday: Any,
     val created_at: String,
     val gender: String,
     val height: Int,
     val id: Int,
     val last_name: String,
     val name: String,
     val place: GetMyProfileResponsePlaceEntity,
     val position: String,
     val weight: Int,
     val working_leg: String
)

data class GetMyProfileResponsePlaceEntity(
     val place_name: String
)