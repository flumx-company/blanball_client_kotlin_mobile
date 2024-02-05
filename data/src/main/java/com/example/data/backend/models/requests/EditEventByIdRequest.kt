package com.example.data.backend.models.requests

data class EditEventByIdRequest(
    val contact_number: String? = null,
    val amount_members: Int? = null,
    val date_and_time: String,
    val description: String,
    val duration: Int,
    val forms: EditEventByIdRequestForms,
    val gender: String,
    val hidden: Boolean? = null,
    val name: String,
    val need_ball: Boolean,
    val need_form: Boolean,
    val place: EditEventByIdRequestPlace,
    val price: Int? = null,
    val price_description: String? = null,
    val privacy: Boolean,
    val type: String
)

class EditEventByIdRequestForms{}

data class EditEventByIdRequestPlace(
    val lat: Double,
    val lon: Double,
    val place_name: String
)