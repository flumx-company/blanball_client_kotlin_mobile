package com.example.data.network.models.requests

import com.squareup.moshi.Json

data class EditEventByIdRequest(
    @Json (name = "contact_number") val contact_number: String? = null,
    @Json (name = "amount_members") val amount_members: Int? = null,
    @Json (name = "date_and_time") val date_and_time: String,
    @Json (name = "description") val description: String,
    @Json (name = "duration") val duration: Int,
    @Json (name = "forms") val forms: EditEventByIdRequestForms,
    @Json (name = "gender") val gender: String,
    @Json (name = "hidden") val hidden: Boolean? = null,
    @Json (name = "name") val name: String,
    @Json (name = "need_ball") val need_ball: Boolean,
    @Json (name = "need_form") val need_form: Boolean,
    @Json (name = "place") val place: EditEventByIdRequestPlace,
    @Json (name = "price") val price: Int? = null,
    @Json (name = "price_description") val price_description: String? = null,
    @Json (name = "privacy") val privacy: Boolean,
    @Json (name = "type") val type: String
)

class EditEventByIdRequestForms{}

data class EditEventByIdRequestPlace(
    @Json (name = "lat") val lat: Double,
    @Json (name = "lon") val lon: Double,
    @Json (name = "place_name") val place_name: String
)