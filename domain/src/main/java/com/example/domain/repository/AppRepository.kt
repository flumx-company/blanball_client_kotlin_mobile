package com.example.domain.repository

import com.example.domain.entity.responses.success.CreationAnEventResponseEntityForms
import com.example.domain.entity.results.CreationAnEventResultEntity
import com.example.domain.entity.results.EditEventByIdResultEntity
import com.example.domain.entity.results.EditMyProfileResultEntity
import com.example.domain.entity.results.EmailResetResultEntity
import com.example.domain.entity.results.FillingTheUserProfileResultEntity
import com.example.domain.entity.results.GetAllEventsResultEntity
import com.example.domain.entity.results.GetEventByIdResultEntity
import com.example.domain.entity.results.GetIsTechnicalWorkStatusResultEntity
import com.example.domain.entity.results.GetMyEventsResultEntity
import com.example.domain.entity.results.GetMyProfileResultEntity
import com.example.domain.entity.results.GetRelevantUserSearchListResultEntity
import com.example.domain.entity.results.GetUkraineCitiesListResultEntity
import com.example.domain.entity.results.GetUserPlannedEventsByIdResultEntity
import com.example.domain.entity.results.GetUserProfileByIdResultEntity
import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
import com.example.domain.entity.results.GetUsersListResultEntity
import com.example.domain.entity.results.JoinToEventAsFunResultEntity
import com.example.domain.entity.results.JoinToEventAsPlayerResultEntity
import com.example.domain.entity.results.LoginResultEntity
import com.example.domain.entity.results.PostEmailVerifyCodeResultEntity
import com.example.domain.entity.results.RegistrationResultEntity
import com.example.domain.entity.results.ResetCompleteResultEntity
import com.example.domain.entity.results.SendCodeResultEntity
import com.example.domain.entity.results.SendVerifyCodeToUserEmailResultEntity

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
        gender: String,
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

    suspend fun getUsersList(
        page: Int,
        gender: String?,
        age_min: Int?,
        age_max: Int?,
        ordering: String?,
        position: String?
    ): GetUsersListResultEntity

    suspend fun getMyProfile(page: Int): GetMyProfileResultEntity

    suspend fun createAnNewEvent(
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

    suspend fun getAllEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetAllEventsResultEntity

    suspend fun getMyEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetMyEventsResultEntity

    suspend fun getEventById(
        id: Int
    ): GetEventByIdResultEntity

    suspend fun editEventById(
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
    ): EditEventByIdResultEntity

    suspend fun sendVerifyCodeToUserEmail(
        page: Int
    ): SendVerifyCodeToUserEmailResultEntity

    suspend fun postEmailVerifyCode(
        code: String
    ): PostEmailVerifyCodeResultEntity

    suspend fun editMyProfile(
        phone: String,
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
    ): EditMyProfileResultEntity

    suspend fun getIsTechnicalWorkStatus(): GetIsTechnicalWorkStatusResultEntity

    suspend fun getRelevantUserSearchList(
        search: String,
        page: Int,
        skipids: String,
    ): GetRelevantUserSearchListResultEntity

    suspend fun getUkraineCitiesList(): GetUkraineCitiesListResultEntity

    suspend fun joinToEventAsFun(eventId: Int): JoinToEventAsFunResultEntity

    suspend fun joinToEventAsPlayer(eventId: Int): JoinToEventAsPlayerResultEntity
}