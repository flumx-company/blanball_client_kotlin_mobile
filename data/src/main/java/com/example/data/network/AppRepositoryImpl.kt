package com.example.data.network

import android.util.Log
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.verifycodemanager.ResetPassVerifyCodeManager
import com.example.data.network.models.requests.AcceptOrDiscardParticipationRequest
import com.example.data.network.models.requests.AuthRequest
import com.example.data.network.models.requests.CreationAnEventRequest
import com.example.data.network.models.requests.CreationAnEventRequestForms
import com.example.data.network.models.requests.CreationAnEventRequestPlace
import com.example.data.network.models.requests.EditEventByIdRequest
import com.example.data.network.models.requests.EditEventByIdRequestForms
import com.example.data.network.models.requests.EditEventByIdRequestPlace
import com.example.data.network.models.requests.EditMyProfileRequest
import com.example.data.network.models.requests.EditMyProfileRequestConfiguration
import com.example.data.network.models.requests.EditMyProfileRequestPlace
import com.example.data.network.models.requests.EditMyProfileRequestProfile
import com.example.data.network.models.requests.JoinToEventAsFunRequest
import com.example.data.network.models.requests.JoinToEventAsPlayerRequest
import com.example.data.network.models.requests.LeaveTheEventAsFanRequest
import com.example.data.network.models.requests.LeaveTheEventAsPlayerRequest
import com.example.data.network.models.requests.PostEmailVerifyCodeRequest
import com.example.data.network.models.requests.ProfileRegistrationRequest
import com.example.data.network.models.requests.RegistrationRequest
import com.example.data.network.models.requests.ResetCompleteRequest
import com.example.data.network.models.requests.SendEmailPasswordResetRequest
import com.example.data.network.models.requests.SendResetCodeRequest
import com.example.data.network.models.requests.UpdateUserProfileRequest
import com.example.data.network.models.requests.UpdateUserProfileRequestConfiguration
import com.example.data.network.models.requests.UpdateUserProfileRequestPlace
import com.example.data.network.models.requests.UpdateUserProfileRequestProfile
import com.example.data.network.models.responses.errors.AcceptOrDiscardParticipationError
import com.example.data.network.models.responses.errors.CreationAnEventError
import com.example.data.network.models.responses.errors.EditEventByIdResponseError
import com.example.data.network.models.responses.errors.EditMyProfileError
import com.example.data.network.models.responses.errors.EmailPassResetError
import com.example.data.network.models.responses.errors.GetAllEventResponseError
import com.example.data.network.models.responses.errors.GetEventByIdResponseError
import com.example.data.network.models.responses.errors.GetIsTechnicalWorkStatusError
import com.example.data.network.models.responses.errors.GetMyEventsResponseError
import com.example.data.network.models.responses.errors.GetMyProfileError
import com.example.data.network.models.responses.errors.GetPrivateRequestListResponseError
import com.example.data.network.models.responses.errors.GetRelevantUserSearchListError
import com.example.data.network.models.responses.errors.GetUkraineCitiesListError
import com.example.data.network.models.responses.errors.GetUserPlannedEventsByIdError
import com.example.data.network.models.responses.errors.GetUserProfileByIdError
import com.example.data.network.models.responses.errors.GetUserReviewsByIdResponseError
import com.example.data.network.models.responses.errors.GetUsersListResponseError
import com.example.data.network.models.responses.errors.JoinToEventAsFunError
import com.example.data.network.models.responses.errors.JoinToEventAsPlayerError
import com.example.data.network.models.responses.errors.LeaveTheEventAsFunError
import com.example.data.network.models.responses.errors.LeaveTheEventAsPlayerError
import com.example.data.network.models.responses.errors.LoginError
import com.example.data.network.models.responses.errors.PostEmailVerifyCodeError
import com.example.data.network.models.responses.errors.RegistrationError
import com.example.data.network.models.responses.errors.ResetCompleteError
import com.example.data.network.models.responses.errors.SendCodeError
import com.example.data.network.models.responses.errors.SendVerifyCodeToUserEmailError
import com.example.data.network.models.responses.errors.UpdateUserProfileResponseError
import com.example.data.utils.ext.handleHttpError
import com.example.data.utils.ext.toAcceptOrDiscardParticipationErrorEntity
import com.example.data.utils.ext.toAcceptOrDiscardParticipationResponseEntity
import com.example.data.utils.ext.toCreationAnEventErrorEntity
import com.example.data.utils.ext.toCreationAnEventResponseEntity
import com.example.data.utils.ext.toEditEventByIdResponseEntity
import com.example.data.utils.ext.toEditEventByIdResponseErrorEntity
import com.example.data.utils.ext.toEditMyProfileErrorEntity
import com.example.data.utils.ext.toEditMyProfileResponseEntity
import com.example.data.utils.ext.toEmailPassResetErrorEntity
import com.example.data.utils.ext.toEmailResetResponse
import com.example.data.utils.ext.toErrorResponse
import com.example.data.utils.ext.toGetAllEventEntityResponseError
import com.example.data.utils.ext.toGetAllEventResponseEntity
import com.example.data.utils.ext.toGetEventByIdResponse
import com.example.data.utils.ext.toGetEventByIdResponseErrorEntity
import com.example.data.utils.ext.toGetIsTechnicalWorkStatusErrorEntity
import com.example.data.utils.ext.toGetIsTechnicalWorkStatusResponseEntity
import com.example.data.utils.ext.toGetMyEventsEntityResponseError
import com.example.data.utils.ext.toGetMyEventsResponseEntity
import com.example.data.utils.ext.toGetMyProfileErrorEntity
import com.example.data.utils.ext.toGetMyProfileResponseEntity
import com.example.data.utils.ext.toGetPrivateRequestListResponseEntity
import com.example.data.utils.ext.toGetPrivateRequestListResponseErrorEntity
import com.example.data.utils.ext.toGetRelevantUserSearchListErrorEntity
import com.example.data.utils.ext.toGetRelevantUserSearchListResponseEntity
import com.example.data.utils.ext.toGetUkraineCitiesListErrorEntity
import com.example.data.utils.ext.toGetUkraineCitiesListResponseEntity
import com.example.data.utils.ext.toGetUserPlannedEventsByIdErrorEntity
import com.example.data.utils.ext.toGetUserPlannedEventsByIdResponseEntity
import com.example.data.utils.ext.toGetUserProfileByIdErrorEntity
import com.example.data.utils.ext.toGetUserProfileByIdResponseEntity
import com.example.data.utils.ext.toGetUserReviewsByIdResponseEntity
import com.example.data.utils.ext.toGetUserReviewsByIdResponseErrorEntity
import com.example.data.utils.ext.toGetUsersListResponseEntity
import com.example.data.utils.ext.toGetUsersListResponseErrorEntity
import com.example.data.utils.ext.toJoinToEventAsFunErrorEntity
import com.example.data.utils.ext.toJoinToEventAsFunResponseEntity
import com.example.data.utils.ext.toJoinToEventAsPlayerErrorEntity
import com.example.data.utils.ext.toJoinToEventAsPlayerResponseEntity
import com.example.data.utils.ext.toLeaveTheEventAsFanResponseEntity
import com.example.data.utils.ext.toLeaveTheEventAsFunErrorEntity
import com.example.data.utils.ext.toLeaveTheEventAsPlayerErrorEntity
import com.example.data.utils.ext.toLeaveTheEventAsPlayerResponseEntity
import com.example.data.utils.ext.toLoginResponse
import com.example.data.utils.ext.toPostEmailVerifyCodeErrorEntity
import com.example.data.utils.ext.toPostEmailVerifyCodeResponseEntity
import com.example.data.utils.ext.toRegistrationErrorEntity
import com.example.data.utils.ext.toRegistrationResponseEntity
import com.example.data.utils.ext.toResetCompleteErrorEntity
import com.example.data.utils.ext.toResetCompleteResponseEntity
import com.example.data.utils.ext.toSendCodeErrorEntity
import com.example.data.utils.ext.toSendCodeResponseEntity
import com.example.data.utils.ext.toSendVerifyCodeToUserEmailErrorEntity
import com.example.data.utils.ext.toSendVerifyCodeToUserEmailResponseEntity
import com.example.data.utils.ext.toUpdateUserProfileResponseEntity
import com.example.data.utils.ext.toUpdateUserProfileResponseEntityError
import com.example.domain.entity.responses.errors.AcceptOrDiscardParticipationErrorEntity
import com.example.domain.entity.responses.errors.CreationAnEventErrorEntity
import com.example.domain.entity.responses.errors.EditEventByIdResponseErrorEntity
import com.example.domain.entity.responses.errors.EditMyProfileErrorEntity
import com.example.domain.entity.responses.errors.GetAllEventEntityResponseError
import com.example.domain.entity.responses.errors.GetEventByIdResponseErrorEntity
import com.example.domain.entity.responses.errors.GetIsTechnicalWorkStatusErrorEntity
import com.example.domain.entity.responses.errors.GetMyEventsEntityResponseError
import com.example.domain.entity.responses.errors.GetMyProfileErrorEntity
import com.example.domain.entity.responses.errors.GetPrivateRequestListResponseErrorEntity
import com.example.domain.entity.responses.errors.GetRelevantUserSearchListErrorEntity
import com.example.domain.entity.responses.errors.GetUkraineCitiesListErrorEntity
import com.example.domain.entity.responses.errors.GetUserPlannedEventsByIdErrorEntity
import com.example.domain.entity.responses.errors.GetUserProfileByIdErrorEntity
import com.example.domain.entity.responses.errors.GetUserReviewsByIdResponseErrorEntity
import com.example.domain.entity.responses.errors.GetUsersListResponseErrorEntity
import com.example.domain.entity.responses.errors.JoinToEventAsFunErrorEntity
import com.example.domain.entity.responses.errors.JoinToEventAsPlayerErrorEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsFunErrorEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsPlayerErrorEntity
import com.example.domain.entity.responses.errors.PostEmailVerifyCodeErrorEntity
import com.example.domain.entity.responses.errors.RegistrationErrorEntity
import com.example.domain.entity.responses.errors.SendVerifyCodeToUserEmailErrorEntity
import com.example.domain.entity.responses.errors.UpdateUserProfileResponseEntityError
import com.example.domain.entity.responses.success.CreationAnEventResponseEntityForms
import com.example.domain.entity.responses.success.EmailPassResetErrorEntity
import com.example.domain.entity.responses.success.ErrorResponse
import com.example.domain.entity.responses.success.ResetCompleteErrorEntity
import com.example.domain.entity.responses.success.SendCodeErrorEntity
import com.example.domain.entity.results.AcceptOrDiscardParticipationResult
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
import com.example.domain.entity.results.JoinToEventAsFanResult
import com.example.domain.entity.results.JoinToEventAsPlayerResult
import com.example.domain.entity.results.LeaveTheEventAsFanResult
import com.example.domain.entity.results.LeaveTheEventAsPlayerResult
import com.example.domain.entity.results.LoginResult
import com.example.domain.entity.results.PostEmailVerifyCodeResult
import com.example.domain.entity.results.RegistrationResult
import com.example.domain.entity.results.ResetCompleteResult
import com.example.domain.entity.results.SendCodeResult
import com.example.domain.entity.results.SendVerifyCodeToUserEmailResult
import com.example.domain.repository.AppRepository
import com.example.domain.utils.NavigationManager
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.HttpException
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    internal val service: MainApiService,
    internal val tokenManager: TokenManager,
    internal val resetPassVerifyCodeManager: ResetPassVerifyCodeManager,
    internal val userPhoneManager: UserPhoneManager,
    internal val userNameManager: UserNameManager,
    private val navigationManager: NavigationManager,
) : AppRepository {

    override suspend fun getUkraineCitiesList(): GetUkraineCitiesListResult {
        return try {
            val getUkraineCitiesListResponse = service.getUkraineCitiesList()
            val getUkraineCitiesListResponseDomain =
                getUkraineCitiesListResponse.toGetUkraineCitiesListResponseEntity()
            GetUkraineCitiesListResult.Success(data = getUkraineCitiesListResponseDomain)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUkraineCitiesListError, GetUkraineCitiesListErrorEntity>(ex) {
                    it.toGetUkraineCitiesListErrorEntity()
                }
            GetUkraineCitiesListResult.Error(error = errorResponse.data.errors[0])
        }
    }

    override suspend fun editMyProfile(
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
        place_name: String,
    ): EditMyProfileResult {
        return try {
            val editMyProfileResponse = service.ediMyProfile(
                editMyProfileRequest = EditMyProfileRequest(
                    configuration = EditMyProfileRequestConfiguration(
                        email = emailRequestConfiguration,
                        phone = phoneRequestConfiguration,
                        show_reviews = showReviewsRequestConfiguration,
                    ),
                    phone = phone,
                    profile = EditMyProfileRequestProfile(
                        about_me = about_me,
                        birthday = birthday,
                        gender = gender,
                        height = height,
                        last_name = last_name,
                        name = name,
                        place = EditMyProfileRequestPlace(
                            lat = lat,
                            lon = lon,
                            place_name = place_name
                        ),
                        position = position,
                        weight = weight,
                        working_leg = working_leg,
                    )
                )
            )
            val editMyProfileResponseDomain = editMyProfileResponse.toEditMyProfileResponseEntity()
            EditMyProfileResult.Success(data = editMyProfileResponseDomain)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<EditMyProfileError, EditMyProfileErrorEntity>(ex) {
                    it.toEditMyProfileErrorEntity()
                }
            EditMyProfileResult.Error(error = errorResponse.data.errors[0])
        }
    }

    override suspend fun getRelevantUserSearchList(
        search: String,
        page: Int,
        skipids: String,
    ): GetRelevantUserSearchListResult {
        return try {
            val getRelevantUserSearchResponse = service.getRelevantUserSearchList(
                search = search,
                page = page,
                skipids = skipids
            )
            val getRelevantUserSearchResponseDomain =
                getRelevantUserSearchResponse.toGetRelevantUserSearchListResponseEntity()
            GetRelevantUserSearchListResult.Success(data = getRelevantUserSearchResponseDomain.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetRelevantUserSearchListError, GetRelevantUserSearchListErrorEntity>(
                    ex
                ) {
                    it.toGetRelevantUserSearchListErrorEntity()
                }
            GetRelevantUserSearchListResult.Error(error = errorResponse.data.errors[0])
        }
    }

    override suspend fun getIsTechnicalWorkStatus(): GetIsTechnicalWorkStatusResult {
        return try {
            val getIsTechnicalWorkStatusResponse = service.getIsTechnicalWorkStatus()
            val getIsTechnicalWorkStatusResponseDomain =
                getIsTechnicalWorkStatusResponse.toGetIsTechnicalWorkStatusResponseEntity()
            GetIsTechnicalWorkStatusResult.Success(data = getIsTechnicalWorkStatusResponseDomain.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetIsTechnicalWorkStatusError, GetIsTechnicalWorkStatusErrorEntity>(
                    ex
                ) {
                    it.toGetIsTechnicalWorkStatusErrorEntity()
                }
            GetIsTechnicalWorkStatusResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun postEmailVerifyCode(code: String): PostEmailVerifyCodeResult {
        return try {
            val postEmailVerifyCodeResponse = service.postEmailVerifyCode(
                postEmailVerifyCodeRequest = PostEmailVerifyCodeRequest(verify_code = code)
            )
            val postEmailVerifyCodeResponseDomain =
                postEmailVerifyCodeResponse.toPostEmailVerifyCodeResponseEntity()
            PostEmailVerifyCodeResult.Success(data = postEmailVerifyCodeResponseDomain.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<PostEmailVerifyCodeError, PostEmailVerifyCodeErrorEntity>(ex) {
                    it.toPostEmailVerifyCodeErrorEntity()
                }
            PostEmailVerifyCodeResult.Error(error = errorResponse.data.errors[0])
        }
    }

    override suspend fun sendVerifyCodeToUserEmail(page: Int): SendVerifyCodeToUserEmailResult {
        return try {
            val sendVerifyCodeToUserEmailResponse = service.sendVerifyCodeToUserEmail(page = page)
            val sendVerifyCodeToUserEmailResponseDomain =
                sendVerifyCodeToUserEmailResponse.toSendVerifyCodeToUserEmailResponseEntity()
            SendVerifyCodeToUserEmailResult.Success(data = sendVerifyCodeToUserEmailResponseDomain.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<SendVerifyCodeToUserEmailError, SendVerifyCodeToUserEmailErrorEntity>(
                    ex
                ) {
                    it.toSendVerifyCodeToUserEmailErrorEntity()
                }
            SendVerifyCodeToUserEmailResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun editEventById(
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
        type: String,
    ): EditEventByIdResult {
        return try {
            val editEventByIdResponse = service.editEventById(
                id = id,
                editEventByIdRequest = EditEventByIdRequest(
                    amount_members = amount_members,
                    contact_number = contact_number,
                    date_and_time = date_and_time,
                    description = description,
                    duration = duration,
                    gender = gender,
                    hidden = hidden,
                    name = name,
                    forms = EditEventByIdRequestForms(),
                    need_ball = need_ball,
                    need_form = need_form,
                    place = EditEventByIdRequestPlace(
                        lat = lat,
                        lon = lon,
                        place_name = place_name,
                    ),
                    price = price,
                    price_description = price_description,
                    privacy = privacy,
                    type = type,
                )
            )
            val editEventByIdDomainResponse =
                editEventByIdResponse.toEditEventByIdResponseEntity()
            EditEventByIdResult.Success(editEventByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<EditEventByIdResponseError, EditEventByIdResponseErrorEntity>(ex) {
                    it.toEditEventByIdResponseErrorEntity()
                }
            EditEventByIdResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getEventById(id: Int): GetEventByIdResult {
        return try {
            val getEventByIdResponse = service.getEventById(id = id)
            val getEventByIdResponseDomainResponse =
                getEventByIdResponse.toGetEventByIdResponse()
            GetEventByIdResult.Success(getEventByIdResponseDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetEventByIdResponseError, GetEventByIdResponseErrorEntity>(ex) {
                    it.toGetEventByIdResponseErrorEntity()
                }
            GetEventByIdResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUsersList(
        page: Int,
        gender: String?,
        age_min: Int?,
        age_max: Int?,
        ordering: String?,
        position: String?,
    ): GetUsersListResult {
        return try {
            val getUsersListResponse = service.getUsersList(
                page = page,
                profile__gender = gender,
                profile__age_min = age_min,
                profile__age_max = age_max,
                ordering = ordering,
                profile__position = position
            )
            val getUsersListResponseDomainResponse =
                getUsersListResponse.toGetUsersListResponseEntity()
            GetUsersListResult.Success(getUsersListResponseDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUsersListResponseError, GetUsersListResponseErrorEntity>(ex) { it.toGetUsersListResponseErrorEntity() }
            GetUsersListResult.Error(errorResponse.data.errors[0])
        }
    }


    override suspend fun createAnNewEvent(
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
        lon: Double,
        lat: Double,
        price: Int?,
        price_description: String?,
        privacy: Boolean,
        type: String,
    ): CreationAnEventResult {
        return try {
            val request = CreationAnEventRequest(
                amount_members = amount_members,
                contact_number = contact_number,
                current_users = current_users,
                date_and_time = date_and_time,
                description = description,
                duration = duration,
                forms = CreationAnEventRequestForms(),
                gender = gender,
                hidden = hidden,
                name = name,
                need_ball = need_ball,
                need_form = need_form,
                place = CreationAnEventRequestPlace(
                    lat = lat,
                    lon = lon,
                    place_name = place
                ),
                price = price,
                price_description = price_description,
                privacy = privacy,
                type = type,
            )
            val createAnNewEventResponse = service.createAnEvent(request)
            val createAnNewEventDomainResponse =
                createAnNewEventResponse.toCreationAnEventResponseEntity()
            CreationAnEventResult.Success(createAnNewEventDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<CreationAnEventError, CreationAnEventErrorEntity>(ex) { it.toCreationAnEventErrorEntity() }
            CreationAnEventResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getMyEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetMyEventsResult {
        return try {
            val getMyEventsResponse = service.getMyEvents(
                page = page,
                typeOfSport = typeOfSport,
                gender = gender,
                date_and_time = time_and_date,
                ordering = ordering,
                date_and_time_before = filterDateAndTimeBefore,
                date_and_time_after = filterDateAndTimeAfter,
            )
            val getMyEventsDomainResponse = getMyEventsResponse.toGetMyEventsResponseEntity()
            GetMyEventsResult.Success(getMyEventsDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetMyEventsResponseError, GetMyEventsEntityResponseError>(ex) { it.toGetMyEventsEntityResponseError() }
            GetMyEventsResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getAllEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetAllEventsResult {
        return try {
            val getAllEventResponse = service.getAllEvents(
                page = page,
                typeOfSport = typeOfSport,
                gender = gender,
                date_and_time = time_and_date,
                ordering = ordering,
                date_and_time_before = filterDateAndTimeBefore,
                date_and_time_after = filterDateAndTimeAfter,
            )
            val getAllEventsDomainResponse = getAllEventResponse.toGetAllEventResponseEntity()
            GetAllEventsResult.Success(getAllEventsDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetAllEventResponseError, GetAllEventEntityResponseError>(ex) { it.toGetAllEventEntityResponseError() }
            GetAllEventsResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getMyProfile(page: Int): GetMyProfileResult {
        return try {
            val getMyProfileResponse = service.getMyProfile(page)
            val getMyProfileDomainResponse = getMyProfileResponse.toGetMyProfileResponseEntity()
            userPhoneManager.safeUserPhone(getMyProfileDomainResponse.data.phone.toString())
            GetMyProfileResult.Success(getMyProfileDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetMyProfileError, GetMyProfileErrorEntity>(ex) { it.toGetMyProfileErrorEntity() }
            GetMyProfileResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun fillingTheUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String,
    ): FillingTheUserProfileResult {
        return try {
            val savedUserPhone = userPhoneManager.getUserPhone()
            val savedFullName = userNameManager.getUserName().firstOrNull().toString()
            val nameAndLastName = savedFullName.split(" ")
            val updateUserProfileResponse = service.updateUserProfile(
                UpdateUserProfileRequest(
                    UpdateUserProfileRequestConfiguration(
                        email = false,
                        phone = false,
                        show_reviews = false
                    ),
                    phone = savedUserPhone.firstOrNull().toString(),
                    profile = UpdateUserProfileRequestProfile(
                        birthday = birthday,
                        height = height,
                        weight = weight,
                        position = position,
                        working_leg = working_leg,
                        place = UpdateUserProfileRequestPlace(
                            place_name = place_name,
                            lat = 90,
                            lon = 90
                        ),
                        name = nameAndLastName[0],
                        last_name = nameAndLastName[1],
                    )
                )
            )
            val updateUserProfileDomainResponse =
                updateUserProfileResponse.toUpdateUserProfileResponseEntity()
            FillingTheUserProfileResult.Success(updateUserProfileDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<UpdateUserProfileResponseError, UpdateUserProfileResponseEntityError>(
                    ex
                ) { it.toUpdateUserProfileResponseEntityError() }
            FillingTheUserProfileResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUserPlannedEventsById(
        id: Int,
        page: Int,
    ): GetUserPlannedEventsByIdResult {
        return try {
            val getUserPlannedByIdResponse = service.getListOfUsersPlannedEvents(id, page)
            val getUserPlannedByIdDomainResponse =
                getUserPlannedByIdResponse.toGetUserPlannedEventsByIdResponseEntity()
            GetUserPlannedEventsByIdResult.Success(getUserPlannedByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUserPlannedEventsByIdError, GetUserPlannedEventsByIdErrorEntity>(
                    ex
                ) { it.toGetUserPlannedEventsByIdErrorEntity() }
            GetUserPlannedEventsByIdResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUserReviewsById(id: Int, page: Int): GetUserReviewsByIdResult {
        return try {
            val getUserReviewsByIdResponse = service.getUserReviewsById(id, page)
            val getUserReviewsByIdDomainResponse =
                getUserReviewsByIdResponse.toGetUserReviewsByIdResponseEntity()
            GetUserReviewsByIdResult.Success(getUserReviewsByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUserReviewsByIdResponseError, GetUserReviewsByIdResponseErrorEntity>(
                    ex
                ) { it.toGetUserReviewsByIdResponseErrorEntity() }
            GetUserReviewsByIdResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUserProfileById(id: Int): GetUserProfileByIdResult {
        return try {
            val getUserProfileByIdResponse = service.getUserProfileById(id)
            val getUserProfileByIdDomainResponse =
                getUserProfileByIdResponse.toGetUserProfileByIdResponseEntity()
            GetUserProfileByIdResult.Success(getUserProfileByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUserProfileByIdError, GetUserProfileByIdErrorEntity>(ex) { it.toGetUserProfileByIdErrorEntity() }
            GetUserProfileByIdResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun changePassword(newPassword: String): ResetCompleteResult {
        return try {
            val savedVerifyCode =
                resetPassVerifyCodeManager.getResetPassVerifyCode().firstOrNull()?.toString() ?: ""
            val request = ResetCompleteRequest(newPassword, savedVerifyCode)
            val resetCompleteResponse = service.resetComplete(request)
            val resetCompleteDomainResponse = resetCompleteResponse.toResetCompleteResponseEntity()
            ResetCompleteResult.Success(resetCompleteDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<ResetCompleteError, ResetCompleteErrorEntity>(ex) { it.toResetCompleteErrorEntity() }
            ResetCompleteResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun sendCode(code: String): SendCodeResult {
        return try {
            val request = SendResetCodeRequest(code)
            val sendResetCodeResponse = service.validateResetCode(request)
            val sendResetCodeDomainResponse = sendResetCodeResponse.toSendCodeResponseEntity()
            resetPassVerifyCodeManager.saveResetPassVerifyCode(code)
            SendCodeResult.Success(sendResetCodeDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<SendCodeError, SendCodeErrorEntity>(ex) { it.toSendCodeErrorEntity() }
            SendCodeResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun sendEmailPassReset(email: String): EmailResetResult {
        return try {
            val request = SendEmailPasswordResetRequest(email)
            val sendEmailResetResponse = service.sendEmailPasswordReset(request)
            val sendEmailResetDomainResponse = sendEmailResetResponse.toEmailResetResponse()
            EmailResetResult.Success(sendEmailResetDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<EmailPassResetError, EmailPassResetErrorEntity>(ex) { it.toEmailPassResetErrorEntity() }
            EmailResetResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val authRequest = AuthRequest(email, password)
            val loginSuccess = service.loginAuthorization(authRequest)
            val loginResponse = loginSuccess.toLoginResponse()
            LoginResult.Success(loginResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<LoginError, ErrorResponse>(ex) { it.toErrorResponse() }
            LoginResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun registration(
        email: String,
        phone: String,
        password: String,
        re_password: String,
        name: String,
        lastName: String,
        gender: String,
    ): RegistrationResult {
        return try {
            val request = RegistrationRequest(
                email =
                email,
                password = password, phone = phone,
                profile =
                ProfileRegistrationRequest(name = name, last_name = lastName, gender = gender),
                re_password = re_password,
            )
            val registrationSuccess = service.userRegistration(request)
            userNameManager.safeUserName("$name $lastName")
            userPhoneManager.safeUserPhone(request.phone)
            val registrationResponse = registrationSuccess.toRegistrationResponseEntity()
            RegistrationResult.Success(registrationResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<RegistrationError, RegistrationErrorEntity>(ex) { it.toRegistrationErrorEntity() }
            RegistrationResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun joinToEventAsFan(eventId: Int): JoinToEventAsFanResult {
        return try {
            val joinToEventAsFunResponse = service.joinToEventAsFun(
                joinToEventAsFunRequest = JoinToEventAsFunRequest(
                    event_id = eventId
                )
            )
            val joinToEventAsFunResponseDomain =
                joinToEventAsFunResponse.toJoinToEventAsFunResponseEntity()
            JoinToEventAsFanResult.Success(joinToEventAsFunResponseDomain)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<JoinToEventAsFunError, JoinToEventAsFunErrorEntity>(ex) { it.toJoinToEventAsFunErrorEntity() }
            JoinToEventAsFanResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun joinToEventAsPlayer(eventId: Int): JoinToEventAsPlayerResult {
        return try {
            val joinToEventAsPlayerResponse = service.joinToEventAsPlayer(
                joinToEventAsPlayerRequest = JoinToEventAsPlayerRequest(
                    event_id = eventId
                )
            )
            val joinToEventAsPlayerResponseDomain =
                joinToEventAsPlayerResponse.toJoinToEventAsPlayerResponseEntity()
            JoinToEventAsPlayerResult.Success(joinToEventAsPlayerResponseDomain)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<JoinToEventAsPlayerError, JoinToEventAsPlayerErrorEntity>(ex) { it.toJoinToEventAsPlayerErrorEntity() }
            JoinToEventAsPlayerResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun leaveTheEventAsPlayer(eventId: Int): LeaveTheEventAsPlayerResult {
        return try {
            val leaveTheEventAsPlayerResponse = service.leaveTheEventAsPlayer(
                leaveTheEventAsPlayerRequest = LeaveTheEventAsPlayerRequest(
                    event_id = eventId
                )
            )
            val leaveTheEventAsPlayerResponseDomain =
                leaveTheEventAsPlayerResponse.toLeaveTheEventAsPlayerResponseEntity()
            LeaveTheEventAsPlayerResult.Success(leaveTheEventAsPlayerResponseDomain)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<LeaveTheEventAsPlayerError, LeaveTheEventAsPlayerErrorEntity>(ex) { it.toLeaveTheEventAsPlayerErrorEntity() }
            LeaveTheEventAsPlayerResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun leaveTheEventAsFan(eventId: Int): LeaveTheEventAsFanResult {
        return try {
            val leaveTheEventAsFunResponse = service.leaveTheEventAsFan(
                leaveTheEventAsFanRequest = LeaveTheEventAsFanRequest(
                    event_id = eventId
                )
            )
            LeaveTheEventAsFanResult.Success(success = leaveTheEventAsFunResponse.toLeaveTheEventAsFanResponseEntity())
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<LeaveTheEventAsFunError, LeaveTheEventAsFunErrorEntity>(ex) { it.toLeaveTheEventAsFunErrorEntity() }
            LeaveTheEventAsFanResult.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getPrivateEventRequestList(eventId: Int): GetPrivateEventRequestListResult {
        return try {
            val getPrivateEventRequestListResponse =
                service.getPrivateEventRequestList(id = eventId)
            Log.d("Result2", getPrivateEventRequestListResponse.data.results.toString())
            GetPrivateEventRequestListResult.Success(success = getPrivateEventRequestListResponse.toGetPrivateRequestListResponseEntity())
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetPrivateRequestListResponseError, GetPrivateRequestListResponseErrorEntity>(
                    ex
                ) { it.toGetPrivateRequestListResponseErrorEntity() }
            GetPrivateEventRequestListResult.Error(error = errorResponse.data.errors[0])
        }
    }

    override suspend fun acceptOrDiscardParticipation(isAcceptEventRequest: Boolean, ids: List<Int>): AcceptOrDiscardParticipationResult {
        return try {
            val acceptOrDiscardParticipationResponse = service.acceptOrDiscardParticipation(
                acceptOrDiscardParticipationRequest = AcceptOrDiscardParticipationRequest(
                    ids = ids,
                    type = isAcceptEventRequest,
                )
            )
            AcceptOrDiscardParticipationResult.Success(data = acceptOrDiscardParticipationResponse.toAcceptOrDiscardParticipationResponseEntity())
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<AcceptOrDiscardParticipationError, AcceptOrDiscardParticipationErrorEntity>(ex){
                    it.toAcceptOrDiscardParticipationErrorEntity()
                }
            AcceptOrDiscardParticipationResult.Error(error = errorResponse.data.errors[0])
        }
    }
}