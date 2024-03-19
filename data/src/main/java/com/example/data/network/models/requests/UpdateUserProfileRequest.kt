package com.example.data.network.models.requests

import com.squareup.moshi.Json

data class UpdateUserProfileRequest(
   @Json (name = "configuration") val configuration: UpdateUserProfileRequestConfiguration,
   @Json (name = "phone") val phone: String,
   @Json (name = "profile") val profile: UpdateUserProfileRequestProfile
)

data class UpdateUserProfileRequestConfiguration(
   @Json (name = "email") val email: Boolean,
   @Json (name = "phone") val phone: Boolean,
   @Json (name = "show_reviews") val show_reviews: Boolean
)

data class UpdateUserProfileRequestProfile(
   @Json (name = "about_me") val about_me: String? = null,
   @Json (name = "birthday") val birthday: String? = null,
   @Json (name = "gender") val gender: String? = null,
   @Json (name = "height") val height: Int? = null,
   @Json (name = "last_name") val last_name: String,
   @Json (name = "name") val name: String,
   @Json (name = "place") val place: UpdateUserProfileRequestPlace? = null,
   @Json (name = "position") val position: String? = null,
   @Json (name = "weight") val weight: Int? = null,
   @Json (name = "working_leg") val working_leg: String? = null
)

data class UpdateUserProfileRequestPlace(
   @Json (name = "lat") val lat: Int,
   @Json (name = "lon") val lon: Int,
   @Json (name = "place_name") val place_name: String
)