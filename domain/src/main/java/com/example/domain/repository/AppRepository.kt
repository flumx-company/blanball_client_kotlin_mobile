package com.example.domain.repository

import com.example.domain.entity.results.EmailResetResultEntity
import com.example.domain.entity.results.GetUserPlannedEventsByIdResultEntity
import com.example.domain.entity.results.GetUserProfileByIdResultEntity
import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
import com.example.domain.entity.results.LoginResultEntity
import com.example.domain.entity.results.RegistrationResultEntity
import com.example.domain.entity.results.ResetCompleteResultEntity
import com.example.domain.entity.results.SendCodeResultEntity
import com.example.domain.entity.results.FillingTheUserProfileResultEntity

interface AppRepository {
    suspend fun login(email: String, password: String): LoginResultEntity
    suspend fun sendEmailPassReset(email: String): EmailResetResultEntity
    suspend fun sendCode(code: String): SendCodeResultEntity
    suspend fun changePassword(newPassword: String): ResetCompleteResultEntity
    suspend fun registration(
        email: String,
        phone: String,
        password: String,
        re_password: String,
        name: String,
        lastName: String,
        gender: String
    ): RegistrationResultEntity

    suspend fun getUserProfileById(id: Int): GetUserProfileByIdResultEntity
    suspend fun getUserReviewsById(id: Int, page: Int): GetUserReviewsByIdResultEntity
    suspend fun getUserPlannedEventsById(id: Int, page: Int): GetUserPlannedEventsByIdResultEntity
    suspend fun fillingTheUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String,
    ): FillingTheUserProfileResultEntity
}