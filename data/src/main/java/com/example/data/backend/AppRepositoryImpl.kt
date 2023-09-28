    package com.example.data.backend
    
    import com.example.data.backend.models.requests.AuthRequest
    import com.example.data.backend.models.requests.CreationAnEventRequest
    import com.example.data.backend.models.requests.CreationAnEventRequestPlace
    import com.example.data.backend.models.requests.ProfileRegistrationRequest
    import com.example.data.backend.models.requests.RegistrationRequest
    import com.example.data.backend.models.requests.ResetCompleteRequest
    import com.example.data.backend.models.requests.SendEmailPasswordResetRequest
    import com.example.data.backend.models.requests.SendResetCodeRequest
    import com.example.data.backend.models.requests.UpdateUserProfileRequest
    import com.example.data.backend.models.requests.UpdateUserProfileRequestConfiguration
    import com.example.data.backend.models.requests.UpdateUserProfileRequestPlace
    import com.example.data.backend.models.requests.UpdateUserProfileRequestProfile
    import com.example.data.backend.models.responses.CreationAnEventError
    import com.example.data.backend.models.responses.EmailPassResetError
    import com.example.data.backend.models.responses.GetMyProfileError
    import com.example.data.backend.models.responses.GetUserPlannedEventsByIdError
    import com.example.data.backend.models.responses.GetUserProfileByIdError
    import com.example.data.backend.models.responses.GetUserReviewsByIdResponseError
    import com.example.data.backend.models.responses.LoginError
    import com.example.data.backend.models.responses.RegistrationError
    import com.example.data.backend.models.responses.ResetCompleteError
    import com.example.data.backend.models.responses.SendCodeError
    import com.example.data.backend.models.responses.UpdateUserProfileResponseError
    import com.example.data.datastore.tokenmanager.TokenManager
    import com.example.data.datastore.usernamemanager.UserNameManager
    import com.example.data.datastore.userphonemanager.UserPhoneManager
    import com.example.data.datastore.verifycodemanager.VerifyCodeManager
    import com.example.data.utils.ext.toCreationAnEventErrorEntity
    import com.example.data.utils.ext.toCreationAnEventResponseEntity
    import com.example.data.utils.ext.toEmailPassResetErrorEntity
    import com.example.data.utils.ext.toEmailResetResponse
    import com.example.data.utils.ext.toErrorResponse
    import com.example.data.utils.ext.toGetMyProfileErrorEntity
    import com.example.data.utils.ext.toGetMyProfileResponseEntity
    import com.example.data.utils.ext.toGetUserPlannedEventsByIdErrorEntity
    import com.example.data.utils.ext.toGetUserPlannedEventsByIdResponseEntity
    import com.example.data.utils.ext.toGetUserProfileByIdErrorEntity
    import com.example.data.utils.ext.toGetUserProfileByIdResponseEntity
    import com.example.data.utils.ext.toGetUserReviewsByIdResponseEntity
    import com.example.data.utils.ext.toGetUserReviewsByIdResponseErrorEntity
    import com.example.data.utils.ext.toLoginResponse
    import com.example.data.utils.ext.toRegistrationErrorEntity
    import com.example.data.utils.ext.toRegistrationResponseEntity
    import com.example.data.utils.ext.toResetCompleteErrorEntity
    import com.example.data.utils.ext.toResetCompleteResponseEntity
    import com.example.data.utils.ext.toSendCodeErrorEntity
    import com.example.data.utils.ext.toSendCodeResponseEntity
    import com.example.data.utils.ext.toUpdateUserProfileResponseEntity
    import com.example.data.utils.ext.toUpdateUserProfileResponseEntityError
    import com.example.domain.entity.responses.CreationAnEventErrorEntity
    import com.example.domain.entity.responses.CreationAnEventResponseEntityForms
    import com.example.domain.entity.responses.CreationAnEventResponseEntityPlace
    import com.example.domain.entity.responses.EmailPassResetErrorEntity
    import com.example.domain.entity.responses.ErrorResponse
    import com.example.domain.entity.responses.GetMyProfileErrorEntity
    import com.example.domain.entity.responses.GetUserPlannedEventsByIdErrorEntity
    import com.example.domain.entity.responses.GetUserProfileByIdErrorEntity
    import com.example.domain.entity.responses.GetUserReviewsByIdResponseErrorEntity
    import com.example.domain.entity.responses.RegistrationErrorEntity
    import com.example.domain.entity.responses.ResetCompleteErrorEntity
    import com.example.domain.entity.responses.SendCodeErrorEntity
    import com.example.domain.entity.responses.UpdateUserProfileResponseEntityError
    import com.example.domain.entity.results.CreationAnEventResultEntity
    import com.example.domain.entity.results.EmailResetResultEntity
    import com.example.domain.entity.results.FillingTheUserProfileResultEntity
    import com.example.domain.entity.results.GetMyProfileResultEntity
    import com.example.domain.entity.results.GetUserPlannedEventsByIdResultEntity
    import com.example.domain.entity.results.GetUserProfileByIdResultEntity
    import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
    import com.example.domain.entity.results.LoginResultEntity
    import com.example.domain.entity.results.RegistrationResultEntity
    import com.example.domain.entity.results.ResetCompleteResultEntity
    import com.example.domain.entity.results.SendCodeResultEntity
    import com.example.domain.repository.AppRepository
    import com.squareup.moshi.Moshi
    import kotlinx.coroutines.flow.firstOrNull
    import retrofit2.HttpException
    import javax.inject.Inject

    class AppRepositoryImpl @Inject constructor(
        internal val service: MainApiService,
        internal val tokenManager: TokenManager,
        internal val verifyCodeManager: VerifyCodeManager,
        internal val userPhoneManager: UserPhoneManager,
        internal val userNameManager: UserNameManager,
    ) : AppRepository {

        override suspend fun createAnNewEvent(
            amount_members: Int,
            contact_number: String,
            current_users: List<Int>?,
            date_and_time: String,
            description: String,
            duration: Int,
            forms: CreationAnEventResponseEntityForms?,
            gender: String,
            hidden: Boolean,
            name: String,
            need_ball: Boolean,
            need_form: Boolean,
            place: CreationAnEventResponseEntityPlace?,
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
                    place = place?.let {
                        CreationAnEventRequestPlace(
                            lat = it.lat,
                            lon = it.lon,
                            place_name = place.place_name,
                        )
                    },
                    price = price,
                    price_description = price_description,
                    privacy = privacy,
                    type = type,
                )
                val createAnNewEventResponse = service.createAnEvent(request)
                val createAnNewEventDomainResponse = createAnNewEventResponse.toCreationAnEventResponseEntity()
                CreationAnEventResultEntity.Success(createAnNewEventDomainResponse.data)
            } catch (ex: HttpException) {
                val errorResponse =
                    handleHttpError<CreationAnEventError, CreationAnEventErrorEntity>(ex) { it.toCreationAnEventErrorEntity()}
                CreationAnEventResultEntity.Error(errorResponse.data.errors[0])
            }
        }

        override suspend fun getMyProfile(page: Int): GetMyProfileResultEntity {
            return try {
                val getMyProfileResponse = service.getMyProfile(page)
                val getMyProfileDomainResponse = getMyProfileResponse.toGetMyProfileResponseEntity()
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
                            place = UpdateUserProfileRequestPlace(place_name = place_name, lat = 90, lon = 90),
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
                val savedVerifyCode = verifyCodeManager.getVerifyCode().firstOrNull()?.toString() ?: ""
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
                verifyCodeManager.saveVerifyCode(code)
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
                    val errorResponse = handleHttpError<RegistrationError, RegistrationErrorEntity>(ex) { it.toRegistrationErrorEntity() }
                    RegistrationResultEntity.Error(errorResponse.data.errors[0])
                }
            }
    
        private inline fun <reified T, R> handleHttpError(
            ex: HttpException,
            errorMapper: (T) -> R
        ): R {
            val errorBody = ex.response()?.errorBody()?.string()
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(T::class.java)
            val errorDto = errorBody?.let { adapter.fromJson(it) }
            val errorResponse = errorDto?.let { errorMapper(it) }
            return errorResponse ?: error("Unknown error")
        }
    }