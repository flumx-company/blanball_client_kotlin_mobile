package com.example.domain.usecases.implementations

import com.example.domain.entity.responses.CreationAnEventResponseEntityForms
import com.example.domain.entity.responses.CreationAnEventResponseEntityPlace
import com.example.domain.entity.results.CreationAnEventResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.CreationAnEventUseCase
import javax.inject.Inject

class CreationAnEventUseCaseImpl @Inject constructor( internal val appRepository: AppRepository) : CreationAnEventUseCase {
    override suspend fun executeCreationAnEvent(
        amount_members: Int,
        contact_number: String,
        current_users: List<Int>?,
        date_and_time: String,
        description: String,
        duration: Int,
        forms: CreationAnEventResponseEntityForms?,
        gender: String,
        hidden: Boolean?,
        name: String,
        need_ball: Boolean,
        need_form: Boolean,
        place: String,
        lon: Int,
        lat: Int,
        price: Int,
        price_description: String,
        privacy: Boolean,
        type: String
    ): CreationAnEventResultEntity {
        return appRepository.createAnNewEvent(
            amount_members = amount_members,
            contact_number = contact_number,
            current_users = current_users,
            date_and_time = date_and_time,
            description = description,
            duration = duration,
            forms = forms,
            gender = gender,
            hidden = hidden,
            name = name,
            need_ball = need_ball,
            need_form = need_form,
            place = place,
            price = price,
            price_description = price_description,
            privacy = privacy,
            type = type,
            lat = lat,
            lon = lon,

        )
    }
}