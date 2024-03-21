package com.example.domain.usecases.implementations

import com.example.domain.entity.results.EditEventByIdResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.EditEventByIdUseCase
import javax.inject.Inject

class EditEventByIdUseCaseImpl @Inject constructor(val appRepository: AppRepository) :
    EditEventByIdUseCase {
    override suspend fun executeEditEventById(
        id: Int,
        amount_members: Int?,
        contact_number: String?,
        date_and_time: String,
        description: String,
        duration: Int,
        gender: String,
        hidden: Boolean?,
        name: String,
        need_ball: Boolean,
        need_form: Boolean,
        place_name: String,
        lat: Double,
        lon: Double,
        price: Int?,
        price_description: String?,
        privacy: Boolean,
        type: String
    ): EditEventByIdResult {
        return appRepository.editEventById(
            id = id,
            amount_members = amount_members,
            contact_number = contact_number,
            date_and_time = date_and_time,
            description = description,
            duration = duration,
            gender = gender,
            hidden = hidden,
            name = name,
            need_ball = need_ball,
            need_form = need_form,
            price = price,
            price_description = price_description,
            privacy = privacy,
            type = type,
            lat = lat,
            lon = lon,
            place_name = place_name,
        )
    }
}