package com.example.data.backend

import com.example.data.backend.models.requests.AuthRequest
import com.example.data.backend.models.requests.CreationAnEventRequest
import com.example.data.backend.models.requests.CreationAnEventRequestPlace
import com.example.data.backend.models.requests.EditEventByIdRequest
import com.example.data.backend.models.requests.EditEventByIdRequestPlace
import com.example.data.backend.models.requests.PostEmailVerifyCodeRequest
import com.example.data.backend.models.requests.ProfileRegistrationRequest
import com.example.data.backend.models.requests.RegistrationRequest
import com.example.data.backend.models.requests.ResetCompleteRequest
import com.example.data.backend.models.requests.SendEmailPasswordResetRequest
import com.example.data.backend.models.requests.SendResetCodeRequest
import com.example.data.backend.models.requests.UpdateUserProfileRequest
import com.example.data.backend.models.requests.UpdateUserProfileRequestConfiguration
import com.example.data.backend.models.requests.UpdateUserProfileRequestPlace
import com.example.data.backend.models.requests.UpdateUserProfileRequestProfile
import com.example.data.backend.models.responses.errors.CreationAnEventError
import com.example.data.backend.models.responses.errors.EditEventByIdResponseError
import com.example.data.backend.models.responses.errors.EmailPassResetError
import com.example.data.backend.models.responses.errors.GetAllEventResponseError
import com.example.data.backend.models.responses.errors.GetEventByIdResponseError
import com.example.data.backend.models.responses.errors.GetIsTechnicalWorkStatusError
import com.example.data.backend.models.responses.errors.GetMyEventsResponseError
import com.example.data.backend.models.responses.errors.GetMyProfileError
import com.example.data.backend.models.responses.errors.GetRelevantUserSearchListError
import com.example.data.backend.models.responses.errors.GetUserPlannedEventsByIdError
import com.example.data.backend.models.responses.errors.GetUserProfileByIdError
import com.example.data.backend.models.responses.errors.GetUserReviewsByIdResponseError
import com.example.data.backend.models.responses.errors.GetUsersListResponseError
import com.example.data.backend.models.responses.errors.LoginError
import com.example.data.backend.models.responses.errors.PostEmailVerifyCodeError
import com.example.data.backend.models.responses.errors.RegistrationError
import com.example.data.backend.models.responses.errors.ResetCompleteError
import com.example.data.backend.models.responses.errors.SendCodeError
import com.example.data.backend.models.responses.errors.SendVerifyCodeToUserEmailError
import com.example.data.backend.models.responses.errors.UpdateUserProfileResponseError
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.verifycodemanager.ResetPassVerifyCodeManager
import com.example.data.utils.ext.handleHttpError
import com.example.data.utils.ext.toCreationAnEventErrorEntity
import com.example.data.utils.ext.toCreationAnEventResponseEntity
import com.example.data.utils.ext.toEditEventByIdResponseEntity
import com.example.data.utils.ext.toEditEventByIdResponseErrorEntity
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
import com.example.data.utils.ext.toGetRelevantUserSearchListErrorEntity
import com.example.data.utils.ext.toGetRelevantUserSearchListResponseEntity
import com.example.data.utils.ext.toGetUserPlannedEventsByIdErrorEntity
import com.example.data.utils.ext.toGetUserPlannedEventsByIdResponseEntity
import com.example.data.utils.ext.toGetUserProfileByIdErrorEntity
import com.example.data.utils.ext.toGetUserProfileByIdResponseEntity
import com.example.data.utils.ext.toGetUserReviewsByIdResponseEntity
import com.example.data.utils.ext.toGetUserReviewsByIdResponseErrorEntity
import com.example.data.utils.ext.toGetUsersListResponseEntity
import com.example.data.utils.ext.toGetUsersListResponseErrorEntity
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
import com.example.domain.entity.responses.errors.CreationAnEventErrorEntity
import com.example.domain.entity.responses.CreationAnEventResponseEntityForms
import com.example.domain.entity.responses.errors.EditEventByIdResponseErrorEntity
import com.example.domain.entity.responses.EmailPassResetErrorEntity
import com.example.domain.entity.responses.ErrorResponse
import com.example.domain.entity.responses.errors.GetAllEventEntityResponseError
import com.example.domain.entity.responses.errors.GetEventByIdResponseErrorEntity
import com.example.domain.entity.responses.errors.GetIsTechnicalWorkStatusErrorEntity
import com.example.domain.entity.responses.errors.GetMyEventsEntityResponseError
import com.example.domain.entity.responses.errors.GetMyProfileErrorEntity
import com.example.domain.entity.responses.errors.GetRelevantUserSearchListErrorEntity
import com.example.domain.entity.responses.errors.GetUserPlannedEventsByIdErrorEntity
import com.example.domain.entity.responses.errors.GetUserProfileByIdErrorEntity
import com.example.domain.entity.responses.errors.GetUserReviewsByIdResponseErrorEntity
import com.example.domain.entity.responses.errors.GetUsersListResponseErrorEntity
import com.example.domain.entity.responses.errors.PostEmailVerifyCodeErrorEntity
import com.example.domain.entity.responses.errors.RegistrationErrorEntity
import com.example.domain.entity.responses.ResetCompleteErrorEntity
import com.example.domain.entity.responses.SendCodeErrorEntity
import com.example.domain.entity.responses.errors.SendVerifyCodeToUserEmailErrorEntity
import com.example.domain.entity.responses.errors.UpdateUserProfileResponseEntityError
import com.example.domain.entity.results.CreationAnEventResultEntity
import com.example.domain.entity.results.EditEventByIdResultEntity
import com.example.domain.entity.results.EmailResetResultEntity
import com.example.domain.entity.results.FillingTheUserProfileResultEntity
import com.example.domain.entity.results.GetAllEventsResultEntity
import com.example.domain.entity.results.GetEventByIdResultEntity
import com.example.domain.entity.results.GetIsTechnicalWorkStatusResultEntity
import com.example.domain.entity.results.GetMyEventsResultEntity
import com.example.domain.entity.results.GetMyProfileResultEntity
import com.example.domain.entity.results.GetRelevantUserSearchListResultEntity
import com.example.domain.entity.results.GetUserPlannedEventsByIdResultEntity
import com.example.domain.entity.results.GetUserProfileByIdResultEntity
import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
import com.example.domain.entity.results.GetUsersListResultEntity
import com.example.domain.entity.results.LoginResultEntity
import com.example.domain.entity.results.PostEmailVerifyCodeResultEntity
import com.example.domain.entity.results.RegistrationResultEntity
import com.example.domain.entity.results.ResetCompleteResultEntity
import com.example.domain.entity.results.SendCodeResultEntity
import com.example.domain.entity.results.SendVerifyCodeToUserEmailResultEntity
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.HttpException
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    internal val service: MainApiService,
    internal val tokenManager: TokenManager,
    internal val resetPassVerifyCodeManager: ResetPassVerifyCodeManager,
    internal val userPhoneManager: UserPhoneManager,
    internal val userNameManager: UserNameManager,
) : AppRepository {


    override suspend fun getRelevantUserSearchList(
        search: String,
        page: Int,
        skipids: String
    ): GetRelevantUserSearchListResultEntity {
        return try {
            val getRelevantUserSearchResponse = service.getRelevantUserSearchList(
                search = search,
                page = page,
                skipids = skipids
            )
            val getRelevantUserSearchResponseDomain =
                getRelevantUserSearchResponse.toGetRelevantUserSearchListResponseEntity()
            GetRelevantUserSearchListResultEntity.Success(data = getRelevantUserSearchResponseDomain.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetRelevantUserSearchListError, GetRelevantUserSearchListErrorEntity>(ex) {
                    it.toGetRelevantUserSearchListErrorEntity()
                }
            GetRelevantUserSearchListResultEntity.Error(error = errorResponse.data.errors[0])
        }
    }

    override suspend fun getIsTechnicalWorkStatus(): GetIsTechnicalWorkStatusResultEntity {
        return try {
            val getIsTechnicalWorkStatusResponse = service.getIsTechnicalWorkStatus()
            val getIsTechnicalWorkStatusResponseDomain =
                getIsTechnicalWorkStatusResponse.toGetIsTechnicalWorkStatusResponseEntity()
            GetIsTechnicalWorkStatusResultEntity.Success(data = getIsTechnicalWorkStatusResponseDomain.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetIsTechnicalWorkStatusError, GetIsTechnicalWorkStatusErrorEntity>(
                    ex
                ) {
                    it.toGetIsTechnicalWorkStatusErrorEntity()
                }
            GetIsTechnicalWorkStatusResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    //        override suspend fun editMyProfile(
//            phone: String,
//            email: Boolean,
//            emailRequestConfiguration: Boolean,
//            phoneRequestConfiguration: Boolean,
//            showReviewsRequestConfiguration: Boolean,
//            about_me: String,
//            birthday: String,
//            gender: String,
//            height: Int,
//            last_name: String,
//            name: String,
//            position: String,
//            weight: Int,
//            working_leg: String,
//            lat: Int,
//            lon: Int,
//            place_name: String
//        ): EditMyProfileResultEntity {
//            return try {
//                val editMyProfileByIdResponse = service.ediMyProfile(
//                    EditMyProfileRequest(
//                        phone = phone,
//                        configuration = EditMyProfileRequestConfiguration(
//                            email = emailRequestConfiguration,
//                            phone = phoneRequestConfiguration,
//                            show_reviews = showReviewsRequestConfiguration,
//                        ),
//                        profile = EditMyProfileRequestProfile(
//                            about_me = about_me,
//                            birthday = birthday,
//                            gender = gender,
//                            height = height,
//                            last_name = last_name,
//                            name = name,
//                            place = EditMyProfileRequestPlace(
//                                lat = lat,
//                                lon = lon,
//                                place_name = place_name,
//                            ),
//                            position = position,
//                            weight = weight,
//                            working_leg = working_leg,
//                        ),
//                    )
//                )
//                val editEventByIdDomainResponse =
//                    editEventByIdResponse.toEditEventByIdResponseEntity()
//                EditEventByIdResultEntity.Success(editEventByIdDomainResponse.data)
//            } catch (ex: HttpException) {
//                val errorResponse =
//                    handleHttpError<EditEventByIdResponseError, EditEventByIdResponseErrorEntity>(ex) {
//                        it.toEditEventByIdResponseErrorEntity()
//                    }
//                EditEventByIdResultEntity.Error(errorResponse.data.errors[0])
//            }
//        }

    override suspend fun postEmailVerifyCode(code: String): PostEmailVerifyCodeResultEntity {
        return try {
            val postEmailVerifyCodeResponse = service.postEmailVerifyCode(
                postEmailVerifyCodeRequest = PostEmailVerifyCodeRequest(verify_code = code)
            )
            val postEmailVerifyCodeResponseDomain =
                postEmailVerifyCodeResponse.toPostEmailVerifyCodeResponseEntity()
            PostEmailVerifyCodeResultEntity.Success(data = postEmailVerifyCodeResponseDomain.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<PostEmailVerifyCodeError, PostEmailVerifyCodeErrorEntity>(ex) {
                    it.toPostEmailVerifyCodeErrorEntity()
                }
            PostEmailVerifyCodeResultEntity.Error(error = errorResponse.data.errors[0])
        }
    }

    override suspend fun sendVerifyCodeToUserEmail(page: Int): SendVerifyCodeToUserEmailResultEntity {
        return try {
            val sendVerifyCodeToUserEmailResponse = service.sendVerifyCodeToUserEmail(page = page)
            val sendVerifyCodeToUserEmailResponseDomain =
                sendVerifyCodeToUserEmailResponse.toSendVerifyCodeToUserEmailResponseEntity()
            SendVerifyCodeToUserEmailResultEntity.Success(data = sendVerifyCodeToUserEmailResponseDomain.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<SendVerifyCodeToUserEmailError, SendVerifyCodeToUserEmailErrorEntity>(
                    ex
                ) {
                    it.toSendVerifyCodeToUserEmailErrorEntity()
                }
            SendVerifyCodeToUserEmailResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun editEventById(
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
        place_name: String,
        lat: Int,
        lon: Int,
        place: String,
        price: Int?,
        price_description: String?,
        privacy: Boolean,
        type: String
    ): EditEventByIdResultEntity {
        return try {
            val editEventByIdResponse = service.editEventById(
                id = id,
                editEventByIdRequest = EditEventByIdRequest(
                    amount_members = amount_members,
                    contact_number = contact_number,
                    date_and_time = date_and_time,
                    description = description,
                    duration = duration,
                    forms = forms,
                    gender = gender,
                    hidden = hidden,
                    name = name,
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
            EditEventByIdResultEntity.Success(editEventByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<EditEventByIdResponseError, EditEventByIdResponseErrorEntity>(ex) {
                    it.toEditEventByIdResponseErrorEntity()
                }
            EditEventByIdResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getEventById(id: Int): GetEventByIdResultEntity {
        return try {
            val getEventByIdResponse = service.getEventById(id = id)
            val getEventByIdResponseDomainResponse =
                getEventByIdResponse.toGetEventByIdResponse()
            GetEventByIdResultEntity.Success(getEventByIdResponseDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetEventByIdResponseError, GetEventByIdResponseErrorEntity>(ex) {
                    it.toGetEventByIdResponseErrorEntity()
                }
            GetEventByIdResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUsersList(
        page: Int,
        gender: String?,
        age_min: Int?,
        age_max: Int?,
        ordering: String?,
        position: String?,
    ): GetUsersListResultEntity {
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
            GetUsersListResultEntity.Success(getUsersListResponseDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUsersListResponseError, GetUsersListResponseErrorEntity>(ex) { it.toGetUsersListResponseErrorEntity() }
            GetUsersListResultEntity.Error(errorResponse.data.errors[0])
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
        lon: Int,
        lat: Int,
        price: Int,
        price_description: String,
        privacy: Boolean,
        type: String
    ): CreationAnEventResultEntity {
        return try {
            val request = CreationAnEventRequest(
                amount_members = amount_members,
                contact_number = contact_number,
                current_users = current_users,
                date_and_time = date_and_time,
                description = description,
                duration = duration,
                forms = null,
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
            CreationAnEventResultEntity.Success(createAnNewEventDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<CreationAnEventError, CreationAnEventErrorEntity>(ex) { it.toCreationAnEventErrorEntity() }
            CreationAnEventResultEntity.Error(errorResponse.data.errors[0])
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
    ): GetMyEventsResultEntity {
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
            GetMyEventsResultEntity.Success(getMyEventsDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetMyEventsResponseError, GetMyEventsEntityResponseError>(ex) { it.toGetMyEventsEntityResponseError() }
            GetMyEventsResultEntity.Error(errorResponse.data.errors[0])
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
    ): GetAllEventsResultEntity {
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
            GetAllEventsResultEntity.Success(getAllEventsDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetAllEventResponseError, GetAllEventEntityResponseError>(ex) { it.toGetAllEventEntityResponseError() }
            GetAllEventsResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getMyProfile(page: Int): GetMyProfileResultEntity {
        return try {
            val getMyProfileResponse = service.getMyProfile(page)
            val getMyProfileDomainResponse = getMyProfileResponse.toGetMyProfileResponseEntity()
            userPhoneManager.safeUserPhone(getMyProfileDomainResponse.data.phone.toString())
            GetMyProfileResultEntity.Success(getMyProfileDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetMyProfileError, GetMyProfileErrorEntity>(ex) { it.toGetMyProfileErrorEntity() }
            GetMyProfileResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun fillingTheUserProfile(
        birthday: String,
        height: Int,
        weight: Int,
        position: String,
        working_leg: String,
        place_name: String
    ): FillingTheUserProfileResultEntity {
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
            FillingTheUserProfileResultEntity.Success(updateUserProfileDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<UpdateUserProfileResponseError, UpdateUserProfileResponseEntityError>(
                    ex
                ) { it.toUpdateUserProfileResponseEntityError() }
            FillingTheUserProfileResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUserPlannedEventsById(
        id: Int,
        page: Int
    ): GetUserPlannedEventsByIdResultEntity {
        return try {
            val getUserPlannedByIdResponse = service.getListOfUsersPlannedEvents(id, page)
            val getUserPlannedByIdDomainResponse =
                getUserPlannedByIdResponse.toGetUserPlannedEventsByIdResponseEntity()
            GetUserPlannedEventsByIdResultEntity.Success(getUserPlannedByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUserPlannedEventsByIdError, GetUserPlannedEventsByIdErrorEntity>(
                    ex
                ) { it.toGetUserPlannedEventsByIdErrorEntity() }
            GetUserPlannedEventsByIdResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUserReviewsById(id: Int, page: Int): GetUserReviewsByIdResultEntity {
        return try {
            val getUserReviewsByIdResponse = service.getUserReviewsById(id, page)
            val getUserReviewsByIdDomainResponse =
                getUserReviewsByIdResponse.toGetUserReviewsByIdResponseEntity()
            GetUserReviewsByIdResultEntity.Success(getUserReviewsByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUserReviewsByIdResponseError, GetUserReviewsByIdResponseErrorEntity>(
                    ex
                ) { it.toGetUserReviewsByIdResponseErrorEntity() }
            GetUserReviewsByIdResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUserProfileById(id: Int): GetUserProfileByIdResultEntity {
        return try {
            val getUserProfileByIdResponse = service.getUserProfileById(id)
            val getUserProfileByIdDomainResponse =
                getUserProfileByIdResponse.toGetUserProfileByIdResponseEntity()
            GetUserProfileByIdResultEntity.Success(getUserProfileByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUserProfileByIdError, GetUserProfileByIdErrorEntity>(ex) { it.toGetUserProfileByIdErrorEntity() }
            GetUserProfileByIdResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun changePassword(newPassword: String): ResetCompleteResultEntity {
        return try {
            val savedVerifyCode =
                resetPassVerifyCodeManager.getResetPassVerifyCode().firstOrNull()?.toString() ?: ""
            val request = ResetCompleteRequest(newPassword, savedVerifyCode)
            val resetCompleteResponse = service.resetComplete(request)
            val resetCompleteDomainResponse = resetCompleteResponse.toResetCompleteResponseEntity()
            ResetCompleteResultEntity.Success(resetCompleteDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<ResetCompleteError, ResetCompleteErrorEntity>(ex) { it.toResetCompleteErrorEntity() }
            ResetCompleteResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun sendCode(code: String): SendCodeResultEntity {
        return try {
            val request = SendResetCodeRequest(code)
            val sendResetCodeResponse = service.validateResetCode(request)
            val sendResetCodeDomainResponse = sendResetCodeResponse.toSendCodeResponseEntity()
            resetPassVerifyCodeManager.saveResetPassVerifyCode(code)
            SendCodeResultEntity.Success(sendResetCodeDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<SendCodeError, SendCodeErrorEntity>(ex) { it.toSendCodeErrorEntity() }
            SendCodeResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun sendEmailPassReset(email: String): EmailResetResultEntity {
        return try {
            val request = SendEmailPasswordResetRequest(email)
            val sendEmailResetResponse = service.sendEmailPasswordReset(request)
            val sendEmailResetDomainResponse = sendEmailResetResponse.toEmailResetResponse()
            EmailResetResultEntity.Success(sendEmailResetDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<EmailPassResetError, EmailPassResetErrorEntity>(ex) { it.toEmailPassResetErrorEntity() }
            EmailResetResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun login(email: String, password: String): LoginResultEntity {
        return try {
            val authRequest = AuthRequest(email, password)
            val loginSuccess = service.loginAuthorization(authRequest)
            val loginResponse = loginSuccess.toLoginResponse()
            tokenManager.saveAccessToken(loginResponse.data.tokens.access)
            tokenManager.saveRefreshToken(loginResponse.data.tokens.refresh)
            LoginResultEntity.Success(loginResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<LoginError, ErrorResponse>(ex) { it.toErrorResponse() }
            LoginResultEntity.Error(errorResponse.data.errors[0])
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
    ): RegistrationResultEntity {
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
            RegistrationResultEntity.Success(registrationResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<RegistrationError, RegistrationErrorEntity>(ex) { it.toRegistrationErrorEntity() }
            RegistrationResultEntity.Error(errorResponse.data.errors[0])
        }
    }
}