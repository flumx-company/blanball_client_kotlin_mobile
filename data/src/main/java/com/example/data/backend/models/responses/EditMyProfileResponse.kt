package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class EditMyProfileResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: EditMyProfileResponseData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class EditMyProfileResponseData(
    @Json(name = "configuration") val configuration: EditMyProfileResponseConfiguration? = null,
    @Json(name = "phone") val phone: String? = null,
    @Json(name = "profile") val profile: EditMyProfileResponseProfile? = null
)

data class EditMyProfileResponseConfiguration(
    @Json(name = "email") val email: Boolean,
    @Json(name = "phone") val phone: Boolean? = null,
    @Json(name = "show_reviews") val show_reviews: Boolean
)

data class EditMyProfileResponseProfile(
    @Json(name = "about_me") val about_me: String,
    @Json(name = "birthday") val birthday: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "height") val height: Int,
    @Json(name = "last_name") val last_name: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "place") val place: EditMyProfileResponsePlace,
    @Json(name = "position") val position: String,
    @Json(name = "weight") val weight: Int,
    @Json(name = "working_leg") val working_leg: String
)

data class EditMyProfileResponsePlace(
    @Json(name = "lat") val lat: Int? = null,
    @Json(name = "lon") val lon: Int? = null,
    @Json(name = "place_name") val place_name: String? = null
)