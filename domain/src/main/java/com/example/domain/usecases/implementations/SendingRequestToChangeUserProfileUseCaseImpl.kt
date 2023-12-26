package com.example.domain.usecases.implementations

import com.example.domain.entity.results.EditMyProfileResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.SendingRequestToChangeUserProfileUseCase
import javax.inject.Inject

class SendingRequestToChangeUserProfileUseCaseImpl
@Inject constructor(internal val appRepository: AppRepository) :
    SendingRequestToChangeUserProfileUseCase {
    override suspend fun executeEditUserProfileRequest(
        phone: String,
        email: Boolean,
        emailRequestConfiguration: Boolean,
        phoneRequestConfiguration: Boolean,
        showReviewsRequestConfiguration: Boolean,
        about_me: String,
        birthday: String,
        gender: String,
        height: Int?,
        last_name: String,
        name: String,
        position: String?,
        weight: Int?,
        working_leg: String?,
        lat: Double,
        lon: Double,
        place_name: String
    ): EditMyProfileResultEntity {
        return appRepository.editMyProfile(
            phone = phone,
            email = email,
            emailRequestConfiguration = emailRequestConfiguration,
            phoneRequestConfiguration = phoneRequestConfiguration,
            showReviewsRequestConfiguration = showReviewsRequestConfiguration,
            about_me = about_me,
            birthday = birthday,
            gender = gender,
            height = height,
            last_name = last_name,
            name = name,
            position = position,
            weight = weight,
            working_leg = working_leg,
            lat = lat,
            lon = lon,
            place_name = place_name
        )
    }
}