package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class UpdateUserProfileResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: UpdateUserProfileResponseData,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
)

data class UpdateUserProfileResponseData(
    @Json (name = "configuration") val configuration: UpdateUserProfileResponseConfiguration,
    @Json (name = "phone") val phone: String,
    @Json (name = "profile") val profile: UpdateUserProfileResponseProfile
)

data class UpdateUserProfileResponseConfiguration(
    @Json (name = "email") val email: Boolean,
    @Json (name = "phone") val phone: Boolean,
    @Json (name = "show_reviews") val show_reviews: Boolean
)

data class UpdateUserProfileResponseProfile(
    @Json (name = "birthday") val birthday: String,
    @Json (name = "height") val height: Int,
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String,
    @Json (name = "place") val place: UpdateUserProfileResponsePlace,
    @Json (name = "position") val position: String,
    @Json (name = "weight") val weight: Int,
    @Json (name = "working_leg") val working_leg: String
)

data class UpdateUserProfileResponsePlace(
    @Json (name = "lat") val lat: Int,
    @Json (name = "lon") val lon: Int,
    @Json (name = "place_name") val place_name: String
)