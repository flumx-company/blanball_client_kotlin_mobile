package com.example.domain.entity.responses

data class CreationAnEventResponseEntity(
    val code: Int,
    val `data`: CreationAnEventResponseEntityData,
    val message: String?,
    val status: String
)

data class CreationAnEventResponseEntityData(
    val amount_members: Int,
    val contact_number: String,
    val date_and_time: String,
    val description: String,
    val duration: Int,
    val forms: CreationAnEventResponseEntityForms?,
    val gender: String,
    val hidden: Boolean?,
    val name: String,
    val need_ball: Boolean,
    val need_form: Boolean,
    val place: CreationAnEventResponseEntityPlace,
    val price: Int?,
    val price_description: String?,
    val privacy: Boolean,
    val type: String
)

class CreationAnEventResponseEntityForms

data class CreationAnEventResponseEntityPlace(
    val lat: Double,
    val lon: Double,
    val place_name: String
)