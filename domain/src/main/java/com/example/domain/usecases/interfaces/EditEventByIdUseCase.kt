package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.EditEventByIdResultEntity

interface EditEventByIdUseCase {
    suspend fun executeEditEventById(
        id: Int,
        amount_members: Int?,
        contact_number: String?,
        date_and_time: String,
        description: String,
        duration: Int,
        forms: Any?,
        gender: String,
        hidden: Boolean?,
        name: String,
        need_ball: Boolean,
        need_form: Boolean,
        place: String,
        place_name: String,
        lat: Int,
        lon: Int,
        price: Int?,
        price_description: String?,
        privacy: Boolean,
        type: String
    ): EditEventByIdResultEntity
}