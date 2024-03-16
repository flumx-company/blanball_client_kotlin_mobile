package com.example.data.network.models.requests

import com.squareup.moshi.Json

data class EditMyProfileRequest(
    @Json (name = "configuration") val configuration: EditMyProfileRequestConfiguration,
    @Json (name = "phone") val phone: String,
    @Json (name = "profile") val profile: EditMyProfileRequestProfile
)

data class EditMyProfileRequestConfiguration(
    @Json (name = "email") val email: Boolean,
    @Json (name = "phone") val phone: Boolean,
    @Json (name = "show_reviews") val show_reviews: Boolean
)

data class EditMyProfileRequestProfile(
    @Json (name = "about_me") val about_me: String?,
    @Json (name = "birthday") val birthday: String,
    @Json (name = "gender") val gender: String,
    @Json (name = "height") val height: Int?,
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String,
    @Json (name = "place") val place: EditMyProfileRequestPlace,
    @Json (name = "position") val position: String?,
    @Json (name = "weight") val weight: Int?,
    @Json (name = "working_leg") val working_leg: String?
)

data class EditMyProfileRequestPlace(
    @Json (name = "lat") val lat: Double,
    @Json (name = "lon") val lon: Double,
    @Json (name = "place_name") val place_name: String
)