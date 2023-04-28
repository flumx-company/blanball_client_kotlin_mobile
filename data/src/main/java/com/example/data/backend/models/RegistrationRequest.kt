package com.example.data.backend.models

import com.squareup.moshi.Json
import javax.annotation.Nullable

data class RegistrationRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "profile") val profile: Profile,
    @Json(name = "re_password") val re_password: String
)

data class Profile(
    @Json(name = "about_me") @Nullable val about_me: String? = null,
    @Json(name = "birthday") @Nullable val birthday: String? = null,
    @Json(name = "gender") val gender: String,
    @Json(name = "height") @Nullable val height: Int? = null,
    @Json(name = "last_name") val last_name: String,
    @Json(name = "name") val name: String,
    @Json(name = "place") @Nullable val place: Place? = null,
    @Json(name = "position") @Nullable val position: String? = null,
    @Json(name = "weight") @Nullable val weight: Int? = null,
    @Json(name = "working_leg") @Nullable val working_leg: String? = null
)

data class Place(
    @Json(name = "lat") @Nullable val lat: Int? = null,
    @Json(name = "lon") @Nullable val lon: Int? = null,
    @Json(name = "place_name") @Nullable val place_name: String? = null
)
