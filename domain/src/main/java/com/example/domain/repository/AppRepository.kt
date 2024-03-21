package com.example.domain.repository

import com.example.domain.entity.responses.success.CreationAnEventResponseEntityForms
import com.example.domain.entity.results.CreationAnEventResult
import com.example.domain.entity.results.EditEventByIdResult
import com.example.domain.entity.results.EditMyProfileResult
import com.example.domain.entity.results.EmailResetResult
import com.example.domain.entity.results.FillingTheUserProfileResult
import com.example.domain.entity.results.GetAllEventsResult
import com.example.domain.entity.results.GetEventByIdResult
import com.example.domain.entity.results.GetIsTechnicalWorkStatusResult
import com.example.domain.entity.results.GetMyEventsResult
import com.example.domain.entity.results.GetMyProfileResult
import com.example.domain.entity.results.GetPrivateEventRequestListResult
import com.example.domain.entity.results.GetRelevantUserSearchListResult
import com.example.domain.entity.results.GetUkraineCitiesListResult
import com.example.domain.entity.results.GetUserPlannedEventsByIdResult
import com.example.domain.entity.results.GetUserProfileByIdResult
import com.example.domain.entity.results.GetUserReviewsByIdResult
import com.example.domain.entity.results.GetUsersListResult
import com.example.domain.entity.results.JoinToEventAsFunResultEntity
import com.example.domain.entity.results.JoinToEventAsPlayerResultEntity
import com.example.domain.entity.results.LoginResult
import com.example.domain.entity.results.PostEmailVerifyCodeResult
import com.example.domain.entity.results.RegistrationResult
import com.example.domain.entity.results.ResetCompleteResult
import com.example.domain.entity.results.SendCodeResult
import com.example.domain.entity.results.SendVerifyCodeToUserEmailResult

interface AppRepository {
    suspend fun login(email: String, password: String): LoginResult

    suspend fun sendEmailPassReset(email: String): EmailResetResult

    suspend fun sendCode(code: String): SendCodeResult

    suspend fun changePassword(newPassword: String): ResetCompleteResult

    suspend fun registration(
        email: String,
        phone: String,
        password: String,
        re_password: String,
        name: String,
        lastName: String,
        gender: String,
    ): RegistrationResult

    suspend fun getUserProfileById(id: Int): GetUserProfileByIdResult

    suspend fun getUserReviewsById(id: Int, page: Int): GetUserReviewsByIdResult

    suspend fun getUserPlannedEventsById(id: Int, page: Int): GetUserPlannedEventsByIdResult

    suspend fun fillingTheUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String,
    ): FillingTheUserProfileResult

    suspend fun getUsersList(
        page: Int,
        gender: String?,
        age_min: Int?,
        age_max: Int?,
        ordering: String?,
        position: String?
    ): GetUsersListResult

    suspend fun getMyProfile(page: Int): GetMyProfileResult

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
    ): CreationAnEventResult

    suspend fun getAllEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetAllEventsResult

    suspend fun getMyEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetMyEventsResult

    suspend fun getEventById(
        id: Int
    ): GetEventByIdResult

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
    ): EditEventByIdResult

    suspend fun sendVerifyCodeToUserEmail(
        page: Int
    ): SendVerifyCodeToUserEmailResult

    suspend fun postEmailVerifyCode(
        code: String
    ): PostEmailVerifyCodeResult

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
    ): EditMyProfileResult

    suspend fun getIsTechnicalWorkStatus(): GetIsTechnicalWorkStatusResult

    suspend fun getRelevantUserSearchList(
        search: String,
        page: Int,
        skipids: String,
    ): GetRelevantUserSearchListResult

    suspend fun getUkraineCitiesList(): GetUkraineCitiesListResult

    suspend fun joinToEventAsFan(eventId: Int): JoinToEventAsFanResultEntity

    suspend fun joinToEventAsPlayer(eventId: Int): JoinToEventAsPlayerResultEntity

    suspend fun leaveTheEventAsFan(eventId: Int): LeaveTheEventAsFanResultEntity

    suspend fun leaveTheEventAsPlayer(eventId: Int): LeaveTheEventAsPlayerResultEntity

    suspend fun getPrivateEventRequestList(eventId: Int): GetPrivateEventRequestListResult
}