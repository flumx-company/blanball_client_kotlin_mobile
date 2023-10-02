package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class CreationAnEventResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: CreationAnEventResponseData,
    @Json (name = "message") val message: String,
    @Json (name = "status") val status: String
)

data class CreationAnEventResponseData(
    @Json (name = "amount_members") val amount_members: Int,
    @Json (name = "contact_number") val contact_number: String,
    @Json (name = "date_and_time") val date_and_time: String,
    @Json (name = "description") val description: String,
    @Json (name = "duration") val duration: Int,
    @Json (name = "forms") val forms: CreationAnEventResponseForms,
    @Json (name = "gender") val gender: String,
    @Json (name = "hidden") val hidden: Boolean?,
    @Json (name = "name") val name: String,
    @Json (name = "need_ball") val need_ball: Boolean,
    @Json (name = "need_form") val need_form: Boolean,
    @Json (name = "place") val place: CreationAnEventResponsePlace,
    @Json (name = "price") val price: Int,
    @Json (name = "price_description") val price_description: String,
    @Json (name = "privacy") val privacy: Boolean,
    @Json (name = "type") val type: String
)

class CreationAnEventResponseForms

data class CreationAnEventResponsePlace(
    @Json (name = "lat") val lat: Int,
    @Json (name = "lon") val lon: Int,
    @Json (name = "place_name") val place_name: String
)