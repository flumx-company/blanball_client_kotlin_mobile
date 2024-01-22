package com.example.domain.usecases.interfaces

import com.example.domain.entity.responses.CreationAnEventResponseEntityForms
import com.example.domain.entity.results.CreationAnEventResultEntity

interface CreationAnEventUseCase {
    suspend fun executeCreationAnEvent(
        amount_members: Int,
        contact_number: String,
        current_users: List<Int>? = null,
        date_and_time: String,
        description: String,
        duration: Int,
        forms: CreationAnEventResponseEntityForms? = null,
        gender: String,
        hidden: Boolean?,
        name: String,
        need_ball: Boolean,
        need_form: Boolean,
        place: String,
        lon: Double,
        lat: Double,
        price: Int?,
        price_description: String?,
        privacy: Boolean,
        type: String,
    ): CreationAnEventResultEntity
}