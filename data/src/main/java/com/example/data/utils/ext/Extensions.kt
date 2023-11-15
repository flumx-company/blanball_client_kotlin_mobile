package com.example.data.utils.ext

import com.example.data.backend.models.responses.Configuration
import com.example.data.backend.models.responses.CreationAnEventError
import com.example.data.backend.models.responses.CreationAnEventErrorData
import com.example.data.backend.models.responses.CreationAnEventErrorDetail
import com.example.data.backend.models.responses.CreationAnEventResponse
import com.example.data.backend.models.responses.CreationAnEventResponseData
import com.example.data.backend.models.responses.CreationAnEventResponseForms
import com.example.data.backend.models.responses.CreationAnEventResponsePlace
import com.example.data.backend.models.responses.Data
import com.example.data.backend.models.responses.DataEmailReset
import com.example.data.backend.models.responses.DataError
import com.example.data.backend.models.responses.DataResetCompleteError
import com.example.data.backend.models.responses.DataResetCompleteResponse
import com.example.data.backend.models.responses.DataSendCode
import com.example.data.backend.models.responses.EmailPassDataError
import com.example.data.backend.models.responses.EmailPassResetError
import com.example.data.backend.models.responses.EmailPassResetErrors
import com.example.data.backend.models.responses.GetAllEventResponse
import com.example.data.backend.models.responses.GetAllEventResponseAuthor
import com.example.data.backend.models.responses.GetAllEventResponseData
import com.example.data.backend.models.responses.GetAllEventResponseError
import com.example.data.backend.models.responses.GetAllEventResponseErrorData
import com.example.data.backend.models.responses.GetAllEventResponseErrorDetail
import com.example.data.backend.models.responses.GetAllEventResponsePlace
import com.example.data.backend.models.responses.GetAllEventResponseProfile
import com.example.data.backend.models.responses.GetAllEventResponseResult
import com.example.data.backend.models.responses.GetEventByIdResponse
import com.example.data.backend.models.responses.GetEventByIdResponseAuthor
import com.example.data.backend.models.responses.GetEventByIdResponseCoordinates
import com.example.data.backend.models.responses.GetEventByIdResponseData
import com.example.data.backend.models.responses.GetEventByIdResponseError
import com.example.data.backend.models.responses.GetEventByIdResponseErrorData
import com.example.data.backend.models.responses.GetEventByIdResponseErrorDetail
import com.example.data.backend.models.responses.GetEventByIdResponseForms
import com.example.data.backend.models.responses.GetEventByIdResponsePlace
import com.example.data.backend.models.responses.GetEventByIdResponseProfile
import com.example.data.backend.models.responses.GetMyEventsResponse
import com.example.data.backend.models.responses.GetMyEventsResponseAuthor
import com.example.data.backend.models.responses.GetMyEventsResponseData
import com.example.data.backend.models.responses.GetMyEventsResponseError
import com.example.data.backend.models.responses.GetMyEventsResponseErrorData
import com.example.data.backend.models.responses.GetMyEventsResponseErrorDetail
import com.example.data.backend.models.responses.GetMyEventsResponsePlace
import com.example.data.backend.models.responses.GetMyEventsResponseProfile
import com.example.data.backend.models.responses.GetMyEventsResponseResult
import com.example.data.backend.models.responses.GetMyProfileError
import com.example.data.backend.models.responses.GetMyProfileErrorData
import com.example.data.backend.models.responses.GetMyProfileErrorDetail
import com.example.data.backend.models.responses.GetMyProfileResponse
import com.example.data.backend.models.responses.GetMyProfileResponseConfiguration
import com.example.data.backend.models.responses.GetMyProfileResponseData
import com.example.data.backend.models.responses.GetMyProfileResponsePlace
import com.example.data.backend.models.responses.GetMyProfileResponseProfile
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdAuthorResponse
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdDataResponse
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdDetailData
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdError
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdErrorData
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdPlaceResponse
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdProfileResponse
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdResponse
import com.example.data.backend.models.responses.GetUserPlannedEventsByIdResultResponse
import com.example.data.backend.models.responses.GetUserProfileByIdDetailData
import com.example.data.backend.models.responses.GetUserProfileByIdError
import com.example.data.backend.models.responses.GetUserProfileByIdErrorData
import com.example.data.backend.models.responses.GetUserProfileByIdResponse
import com.example.data.backend.models.responses.GetUserReviewsByIdData
import com.example.data.backend.models.responses.GetUserReviewsByIdResponse
import com.example.data.backend.models.responses.GetUserReviewsByIdResponseAuthor
import com.example.data.backend.models.responses.GetUserReviewsByIdResponseDetailData
import com.example.data.backend.models.responses.GetUserReviewsByIdResponseError
import com.example.data.backend.models.responses.GetUserReviewsByIdResponseErrorData
import com.example.data.backend.models.responses.GetUserReviewsByIdResponseProfile
import com.example.data.backend.models.responses.GetUserReviewsByIdResponseResult
import com.example.data.backend.models.responses.GetUsersListResponse
import com.example.data.backend.models.responses.GetUsersListResponseData
import com.example.data.backend.models.responses.GetUsersListResponseError
import com.example.data.backend.models.responses.GetUsersListResponseErrorData
import com.example.data.backend.models.responses.GetUsersListResponseErrorDetailData
import com.example.data.backend.models.responses.GetUsersListResponseProfile
import com.example.data.backend.models.responses.GetUsersListResponseResult
import com.example.data.backend.models.responses.LoginError
import com.example.data.backend.models.responses.LoginErrors
import com.example.data.backend.models.responses.LoginSuccess
import com.example.data.backend.models.responses.PlayingPlace
import com.example.data.backend.models.responses.PublicProfileDataResponse
import com.example.data.backend.models.responses.PublicProfileResponse
import com.example.data.backend.models.responses.RegistrationData
import com.example.data.backend.models.responses.RegistrationError
import com.example.data.backend.models.responses.RegistrationErrorDetail
import com.example.data.backend.models.responses.RegistrationErrorsData
import com.example.data.backend.models.responses.RegistrationResponse
import com.example.data.backend.models.responses.ResetCompleteError
import com.example.data.backend.models.responses.ResetCompleteErrors
import com.example.data.backend.models.responses.ResetCompleteResponse
import com.example.data.backend.models.responses.SendCodeDataError
import com.example.data.backend.models.responses.SendCodeError
import com.example.data.backend.models.responses.SendCodeErrors
import com.example.data.backend.models.responses.SendCodeResponse
import com.example.data.backend.models.responses.SendEmailPasswordResetSuccess
import com.example.data.backend.models.responses.Tokens
import com.example.data.backend.models.responses.UpdateUserProfileResponse
import com.example.data.backend.models.responses.UpdateUserProfileResponseConfiguration
import com.example.data.backend.models.responses.UpdateUserProfileResponseData
import com.example.data.backend.models.responses.UpdateUserProfileResponseError
import com.example.data.backend.models.responses.UpdateUserProfileResponseErrorData
import com.example.data.backend.models.responses.UpdateUserProfileResponseErrorDetail
import com.example.data.backend.models.responses.UpdateUserProfileResponsePlace
import com.example.data.backend.models.responses.UpdateUserProfileResponseProfile
import com.example.domain.entity.responses.ConfigurationEntity
import com.example.domain.entity.responses.CreationAnEventErrorDetailEntity
import com.example.domain.entity.responses.CreationAnEventErrorEntity
import com.example.domain.entity.responses.CreationAnEventErrorEntityData
import com.example.domain.entity.responses.CreationAnEventResponseEntity
import com.example.domain.entity.responses.CreationAnEventResponseEntityData
import com.example.domain.entity.responses.CreationAnEventResponseEntityForms
import com.example.domain.entity.responses.CreationAnEventResponseEntityPlace
import com.example.domain.entity.responses.DataCompleteResponseEntity
import com.example.domain.entity.responses.DataEmailResetEntity
import com.example.domain.entity.responses.DataSendCodeDomain
import com.example.domain.entity.responses.EmailPassDataErrorEntity
import com.example.domain.entity.responses.EmailPassResetErrorEntity
import com.example.domain.entity.responses.EmailPassResetErrorsEntity
import com.example.domain.entity.responses.EmailResetResponseEntity
import com.example.domain.entity.responses.ErrorResponse
import com.example.domain.entity.responses.GetAllEventEntityResponseError
import com.example.domain.entity.responses.GetAllEventEntityResponseErrorData
import com.example.domain.entity.responses.GetAllEventEntityResponseErrorDetail
import com.example.domain.entity.responses.GetAllEventResponseEntity
import com.example.domain.entity.responses.GetAllEventResponseEntityAuthor
import com.example.domain.entity.responses.GetAllEventResponseEntityData
import com.example.domain.entity.responses.GetAllEventResponseEntityPlace
import com.example.domain.entity.responses.GetAllEventResponseEntityProfile
import com.example.domain.entity.responses.GetAllEventResponseEntityResult
import com.example.domain.entity.responses.GetEventByIdResponseAuthorEntity
import com.example.domain.entity.responses.GetEventByIdResponseCoordinatesEntity
import com.example.domain.entity.responses.GetEventByIdResponseDataEntity
import com.example.domain.entity.responses.GetEventByIdResponseEntity
import com.example.domain.entity.responses.GetEventByIdResponseErrorDataEntity
import com.example.domain.entity.responses.GetEventByIdResponseErrorDetailEntity
import com.example.domain.entity.responses.GetEventByIdResponseErrorEntity
import com.example.domain.entity.responses.GetEventByIdResponseFormsEntity
import com.example.domain.entity.responses.GetEventByIdResponsePlaceEntity
import com.example.domain.entity.responses.GetEventByIdResponseProfileEntity
import com.example.domain.entity.responses.GetMyEventsEntityResponseError
import com.example.domain.entity.responses.GetMyEventsEntityResponseErrorData
import com.example.domain.entity.responses.GetMyEventsEntityResponseErrorDetail
import com.example.domain.entity.responses.GetMyEventsResponseEntity
import com.example.domain.entity.responses.GetMyEventsResponseEntityAuthor
import com.example.domain.entity.responses.GetMyEventsResponseEntityData
import com.example.domain.entity.responses.GetMyEventsResponseEntityPlace
import com.example.domain.entity.responses.GetMyEventsResponseEntityProfile
import com.example.domain.entity.responses.GetMyEventsResponseEntityResult
import com.example.domain.entity.responses.GetMyProfileErrorDataEntity
import com.example.domain.entity.responses.GetMyProfileErrorDetailEntity
import com.example.domain.entity.responses.GetMyProfileErrorEntity
import com.example.domain.entity.responses.GetMyProfileResponseConfigurationEntity
import com.example.domain.entity.responses.GetMyProfileResponseDataEntity
import com.example.domain.entity.responses.GetMyProfileResponseEntity
import com.example.domain.entity.responses.GetMyProfileResponsePlaceEntity
import com.example.domain.entity.responses.GetMyProfileResponseProfileEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdAuthorResponseEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdDataResponseEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdDetailDataEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdErrorDataEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdErrorEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdPlaceResponseEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdProfileResponseEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdResponseEntity
import com.example.domain.entity.responses.GetUserPlannedEventsByIdResultResponseEntity
import com.example.domain.entity.responses.GetUserProfileByIdDetailDataEntity
import com.example.domain.entity.responses.GetUserProfileByIdErrorDataEntity
import com.example.domain.entity.responses.GetUserProfileByIdErrorEntity
import com.example.domain.entity.responses.GetUserProfileByIdResponseEntity
import com.example.domain.entity.responses.GetUserReviewsByIdDataEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseAuthorEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseDetailDataEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseErrorDataEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseErrorEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseProfileEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseResultEntity
import com.example.domain.entity.responses.GetUsersListResponseDataEntity
import com.example.domain.entity.responses.GetUsersListResponseEntity
import com.example.domain.entity.responses.GetUsersListResponseErrorDataEntity
import com.example.domain.entity.responses.GetUsersListResponseErrorDetailDataEntity
import com.example.domain.entity.responses.GetUsersListResponseErrorEntity
import com.example.domain.entity.responses.GetUsersListResponseProfileEntity
import com.example.domain.entity.responses.GetUsersListResponseResultEntity
import com.example.domain.entity.responses.LoginData
import com.example.domain.entity.responses.LoginDataError
import com.example.domain.entity.responses.LoginErrorsDomain
import com.example.domain.entity.responses.LoginResponse
import com.example.domain.entity.responses.LoginTokens
import com.example.domain.entity.responses.PlayingPlaceEntity
import com.example.domain.entity.responses.PublicProfileDataResponseEntity
import com.example.domain.entity.responses.PublicProfileResponseEntity
import com.example.domain.entity.responses.RegistrationDataEntity
import com.example.domain.entity.responses.RegistrationErrorDetailEntity
import com.example.domain.entity.responses.RegistrationErrorEntity
import com.example.domain.entity.responses.RegistrationErrorsDataEntity
import com.example.domain.entity.responses.RegistrationResponseEntity
import com.example.domain.entity.responses.ResetCompleteDataEntity
import com.example.domain.entity.responses.ResetCompleteErrorEntity
import com.example.domain.entity.responses.ResetCompleteErrorsEntity
import com.example.domain.entity.responses.ResetCompleteResponseEntity
import com.example.domain.entity.responses.SendCodeDataErrorEntity
import com.example.domain.entity.responses.SendCodeErrorEntity
import com.example.domain.entity.responses.SendCodeErrorsEntity
import com.example.domain.entity.responses.SendCodeResponseEntity
import com.example.domain.entity.responses.UpdateUserProfileResponseConfigurationEntity
import com.example.domain.entity.responses.UpdateUserProfileResponseDataEntity
import com.example.domain.entity.responses.UpdateUserProfileResponseEntity
import com.example.domain.entity.responses.UpdateUserProfileResponseEntityError
import com.example.domain.entity.responses.UpdateUserProfileResponseEntityErrorData
import com.example.domain.entity.responses.UpdateUserProfileResponseEntityErrorDetail
import com.example.domain.entity.responses.UpdateUserProfileResponsePlaceEntity
import com.example.domain.entity.responses.UpdateUserProfileResponseProfileEntity

internal fun RegistrationError.toRegistrationErrorEntity(): RegistrationErrorEntity =
     RegistrationErrorEntity(
        this.code,
        this.data.toRegistrationErrorsDataEntity(),
        this.message,
        this.status,
    )

internal fun RegistrationErrorsData.toRegistrationErrorsDataEntity(): RegistrationErrorsDataEntity =
     RegistrationErrorsDataEntity(
        listOf(this.errors[0].toRegistrationErrorDetailEntity()),
        this.type
    )

internal fun RegistrationErrorDetail.toRegistrationErrorDetailEntity(): RegistrationErrorDetailEntity =
     RegistrationErrorDetailEntity(this.detail)

internal fun RegistrationResponse.toRegistrationResponseEntity(): RegistrationResponseEntity =
     RegistrationResponseEntity(
        this.code,
        this.data.toRegistrationDataEntity(),
        this.message,
        this.status
    )

internal fun RegistrationData.toRegistrationDataEntity(): RegistrationDataEntity =
     RegistrationDataEntity(this.access, this.refresh)

internal fun ResetCompleteError.toResetCompleteErrorEntity(): ResetCompleteErrorEntity =
     ResetCompleteErrorEntity(
        this.code,
        this.data.toResetCompleteDataEntity(),
        this.message,
        this.status
    )

internal fun DataResetCompleteError.toResetCompleteDataEntity(): ResetCompleteDataEntity =
     ResetCompleteDataEntity(
        listOf(this.errors[0].toResetCompleteErrorsEntity()),
        this.type
    )


internal fun ResetCompleteErrors.toResetCompleteErrorsEntity(): ResetCompleteErrorsEntity =
     ResetCompleteErrorsEntity(this.detail)


internal fun DataResetCompleteResponse.toResetCompleteResponseEntity(): DataCompleteResponseEntity =
     DataCompleteResponseEntity(this.success)

internal fun ResetCompleteResponse.toResetCompleteResponseEntity(): ResetCompleteResponseEntity =
     ResetCompleteResponseEntity(
        this.code,
        this.data.toResetCompleteResponseEntity(),
        this.message,
        this.status
    )

internal fun SendCodeError.toSendCodeErrorEntity(): SendCodeErrorEntity =
     SendCodeErrorEntity(
        this.code,
        this.data.toSendCodeDataErrorEntity(),
        this.message,
        this.status
    )

internal fun SendCodeDataError.toSendCodeDataErrorEntity(): SendCodeDataErrorEntity =
     SendCodeDataErrorEntity(
        listOf(this.errors[0].toSendCodeErrorsEntity()),
        this.type
    )

internal fun SendCodeErrors.toSendCodeErrorsEntity(): SendCodeErrorsEntity =
     SendCodeErrorsEntity(this.detail)

internal fun SendCodeResponse.toSendCodeResponseEntity(): SendCodeResponseEntity =
     SendCodeResponseEntity(
        this.code,
        this.data.toDataSendDomain(),
        this.message,
        this.status
    )

internal fun DataSendCode.toDataSendDomain(): DataSendCodeDomain =
     DataSendCodeDomain(this.success)

internal fun EmailPassResetError.toEmailPassResetErrorEntity(): EmailPassResetErrorEntity =
     EmailPassResetErrorEntity(
        this.code,
        this.data.toEmailPassDataErrorEntity(),
        this.message,
        this.status
    )

internal fun EmailPassDataError.toEmailPassDataErrorEntity(): EmailPassDataErrorEntity =
     EmailPassDataErrorEntity(
        listOf(this.errors[0].toEmailPassDataErrorEntity()),
        this.type
    )

internal fun EmailPassResetErrors.toEmailPassDataErrorEntity(): EmailPassResetErrorsEntity =
     EmailPassResetErrorsEntity(this.detail)

internal fun SendEmailPasswordResetSuccess.toEmailResetResponse(): EmailResetResponseEntity =
     EmailResetResponseEntity(
        this.code,
        this.data.toDataEmailReset(),
        this.message,
        this.status
    )

internal fun DataEmailReset.toDataEmailReset(): DataEmailResetEntity =
     DataEmailResetEntity(this.success)

internal fun LoginSuccess.toLoginResponse(): LoginResponse =

     LoginResponse(this.code, this.data.toLoginData(), this.message, this.status)

internal fun Data.toLoginData(): LoginData =
     LoginData(this.email, this.tokens.toLoginTokens())

internal fun Tokens.toLoginTokens(): LoginTokens =
     LoginTokens(this.access, this.refresh)

internal fun LoginError.toErrorResponse(): ErrorResponse =
     ErrorResponse(this.code, this.data.toDataError(), this.message, this.status)

internal fun DataError.toDataError(): LoginDataError =
     LoginDataError(listOf(this.errors[0].toLoginErrors()), this.type)

internal fun LoginErrors.toLoginErrors(): LoginErrorsDomain =
     LoginErrorsDomain(this.detail)

internal fun GetUserProfileByIdResponse.toGetUserProfileByIdResponseEntity(): GetUserProfileByIdResponseEntity =
     GetUserProfileByIdResponseEntity(
        this.code,
        this.data.toPublicProfileDataResponseEntity(),
        this.message,
        this.status
    )

internal fun PublicProfileDataResponse.toPublicProfileDataResponseEntity(): PublicProfileDataResponseEntity =
     PublicProfileDataResponseEntity(
        this.configuration.toConfigurationEntity(),
        this.email,
        this.id,
        this.is_online,
        this.is_verified,
        this.phone,
        this.profile.toPublicProfileResponseEntity(),
        this.raiting,
        this.role
    )

internal fun Configuration.toConfigurationEntity(): ConfigurationEntity =
     ConfigurationEntity(this.email, this.phone, this.show_reviews)

internal fun PublicProfileResponse.toPublicProfileResponseEntity(): PublicProfileResponseEntity =
     PublicProfileResponseEntity(
        this.about_me,
        this.age,
        this.avatar_url,
        this.birthday,
        this.created_at,
        this.gender,
        this.height,
        this.id,
        this.last_name,
        this.name,
        this.place?.toPlayingPlaceEntity(),
        this.position,
        this.weight,
        this.working_leg
    )

internal fun PlayingPlace.toPlayingPlaceEntity(): PlayingPlaceEntity =
     PlayingPlaceEntity(this.place_name)

internal fun GetUserProfileByIdError.toGetUserProfileByIdErrorEntity(): GetUserProfileByIdErrorEntity =
     GetUserProfileByIdErrorEntity(
        this.code,
        this.data.toGetUserProfileByIdErrorDataEntity(),
        this.message,
        this.status
    )

internal fun GetUserProfileByIdErrorData.toGetUserProfileByIdErrorDataEntity(): GetUserProfileByIdErrorDataEntity =
     GetUserProfileByIdErrorDataEntity(
        listOf(
            this.errors[0].toGetUserProfileByIdDetailDataEntity()
        ), this.type
    )

internal fun GetUserProfileByIdDetailData.toGetUserProfileByIdDetailDataEntity(): GetUserProfileByIdDetailDataEntity =
     GetUserProfileByIdDetailDataEntity(this.detail)

internal fun GetUserReviewsByIdResponse.toGetUserReviewsByIdResponseEntity(): GetUserReviewsByIdResponseEntity =
     GetUserReviewsByIdResponseEntity(
        this.code,
        this.data.toGetUserReviewsByIdDataEntity(),
        this.message,
        this.status
    )

internal fun GetUserReviewsByIdData.toGetUserReviewsByIdDataEntity(): GetUserReviewsByIdDataEntity =
     GetUserReviewsByIdDataEntity(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results?.map { it.toGetUserReviewsByIdResponseResultEntity() },
        this.success,
        this.total_count,
    )

internal fun GetUserReviewsByIdResponseResult.toGetUserReviewsByIdResponseResultEntity(): GetUserReviewsByIdResponseResultEntity =
     GetUserReviewsByIdResponseResultEntity(
        this.author.toGetUserReviewsByIdResponseAuthorEntity(),
        this.id,
        this.stars,
        this.text,
        this.time_created
    )

internal fun GetUserReviewsByIdResponseAuthor.toGetUserReviewsByIdResponseAuthorEntity(): GetUserReviewsByIdResponseAuthorEntity =
     GetUserReviewsByIdResponseAuthorEntity(
        this.id,
        this.profile.toGetUserReviewsByIdResponseProfile(),
    )

internal fun GetUserReviewsByIdResponseProfile.toGetUserReviewsByIdResponseProfile(): GetUserReviewsByIdResponseProfileEntity =
     GetUserReviewsByIdResponseProfileEntity(
        this.last_name,
        this.name,
    )

internal fun GetUserReviewsByIdResponseError.toGetUserReviewsByIdResponseErrorEntity(): GetUserReviewsByIdResponseErrorEntity =
     GetUserReviewsByIdResponseErrorEntity(
        this.code,
        this.data.toGetUserReviewsByIdResponseErrorDataEntity(),
        this.message,
        this.status
    )

internal fun GetUserReviewsByIdResponseErrorData.toGetUserReviewsByIdResponseErrorDataEntity(): GetUserReviewsByIdResponseErrorDataEntity =
     GetUserReviewsByIdResponseErrorDataEntity(
        listOf(
            this.errors[0].toGetUserReviewsByIdResponseDetailDataEntity()
        ),
        this.type,
    )

internal fun GetUserReviewsByIdResponseDetailData.toGetUserReviewsByIdResponseDetailDataEntity(): GetUserReviewsByIdResponseDetailDataEntity =
     GetUserReviewsByIdResponseDetailDataEntity(this.detail)


internal fun GetUserPlannedEventsByIdResponse.toGetUserPlannedEventsByIdResponseEntity(): GetUserPlannedEventsByIdResponseEntity =
     GetUserPlannedEventsByIdResponseEntity(
        this.code, this.data.toGetUserPlannedEventsByIdDataResponseEntity(), this.message, this.status
    )

internal fun GetUserPlannedEventsByIdDataResponse.toGetUserPlannedEventsByIdDataResponseEntity(): GetUserPlannedEventsByIdDataResponseEntity =
     GetUserPlannedEventsByIdDataResponseEntity(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results?.map { it.toGetUserPlannedEventsByIdResultResponseEntity() } ,
        this.success,
        this.total_count
    )

internal fun GetUserPlannedEventsByIdResultResponse.toGetUserPlannedEventsByIdResultResponseEntity(): GetUserPlannedEventsByIdResultResponseEntity =
     GetUserPlannedEventsByIdResultResponseEntity(
        this.amount_members,
        this.author.toGetUserPlannedEventsByIdAuthorResponseEntity(),
        this.count_current_fans,
        this.count_current_users,
        this.date_and_time,
        this.description,
        this.duration,
        this.gender,
        this.id,
        this.name,
        this.need_ball,
        this.need_form,
        this.pk_user_role,
        this.place?.toGetUserPlannedEventsByIdPlaceResponseEntity()!!,
        this.price,
        this.privacy,
        this.request_user_role,
        this.status,
        this.type,
    )

internal fun GetUserPlannedEventsByIdAuthorResponse.toGetUserPlannedEventsByIdAuthorResponseEntity(): GetUserPlannedEventsByIdAuthorResponseEntity =
     GetUserPlannedEventsByIdAuthorResponseEntity(
        this.id,
        this.phone,
        this.profile.toGetUserPlannedEventsByIdProfileResponseEntity(),
    )

internal fun GetUserPlannedEventsByIdProfileResponse.toGetUserPlannedEventsByIdProfileResponseEntity(): GetUserPlannedEventsByIdProfileResponseEntity =
     GetUserPlannedEventsByIdProfileResponseEntity(
        this.avatar_url, this.id, this.last_name, this.name
    )

internal fun GetUserPlannedEventsByIdPlaceResponse.toGetUserPlannedEventsByIdPlaceResponseEntity(): GetUserPlannedEventsByIdPlaceResponseEntity =
     GetUserPlannedEventsByIdPlaceResponseEntity(this.lat, this.lon, this.place_name)

internal fun GetUserPlannedEventsByIdError.toGetUserPlannedEventsByIdErrorEntity(): GetUserPlannedEventsByIdErrorEntity =
     GetUserPlannedEventsByIdErrorEntity(
        this.code, this.data.toGetUserPlannedByIdErrorData(), this.message, this.status
    )

internal fun GetUserPlannedEventsByIdErrorData.toGetUserPlannedByIdErrorData(): GetUserPlannedEventsByIdErrorDataEntity =
     GetUserPlannedEventsByIdErrorDataEntity(
        listOf(
            this.errors[0].toGetUserPlannedEventsByIdDetailDataEntity()
        ), this.type
    )

internal fun GetUserPlannedEventsByIdDetailData.toGetUserPlannedEventsByIdDetailDataEntity(): GetUserPlannedEventsByIdDetailDataEntity =
     GetUserPlannedEventsByIdDetailDataEntity(this.detail)

internal fun UpdateUserProfileResponse.toUpdateUserProfileResponseEntity(): UpdateUserProfileResponseEntity =
     UpdateUserProfileResponseEntity(
        this.code,
        this.data.toUpdateUserProfileResponseDataEntity(),
        this.message,
        this.status
    )

internal fun UpdateUserProfileResponseData.toUpdateUserProfileResponseDataEntity(): UpdateUserProfileResponseDataEntity =
     UpdateUserProfileResponseDataEntity(
        this.configuration.toUpdateUserProfileResponseConfigurationEntity(),
        this.phone,
        this.profile.toUpdateUserProfileResponseProfileEntity(),
    )

internal fun UpdateUserProfileResponseConfiguration.toUpdateUserProfileResponseConfigurationEntity(): UpdateUserProfileResponseConfigurationEntity =
     UpdateUserProfileResponseConfigurationEntity(
        this.email,
        this.phone,
        this.show_reviews
    )

internal fun UpdateUserProfileResponseProfile.toUpdateUserProfileResponseProfileEntity(): UpdateUserProfileResponseProfileEntity =
     UpdateUserProfileResponseProfileEntity(
        this.birthday,
        this.height,
        this.last_name,
        this.name,
        this.place.toUpdateUserProfileResponsePlaceEntity(),
        this.position,
        this.weight,
        this.working_leg,
    )

internal fun UpdateUserProfileResponsePlace.toUpdateUserProfileResponsePlaceEntity(): UpdateUserProfileResponsePlaceEntity =
     UpdateUserProfileResponsePlaceEntity(
        this.lat,
        this.lon,
        this.place_name
    )

internal fun UpdateUserProfileResponseError.toUpdateUserProfileResponseEntityError(): UpdateUserProfileResponseEntityError =
     UpdateUserProfileResponseEntityError(
        this.code,
        this.data.toUpdateUserProfileResponseEntityErrorData(),
        this.message,
        this.status,
    )

internal fun UpdateUserProfileResponseErrorData.toUpdateUserProfileResponseEntityErrorData(): UpdateUserProfileResponseEntityErrorData =
     UpdateUserProfileResponseEntityErrorData(
        listOf(this.errors[0].toUpdateUserProfileResponseEntityErrorDetail()),
        this.type,
    )

internal fun UpdateUserProfileResponseErrorDetail.toUpdateUserProfileResponseEntityErrorDetail(): UpdateUserProfileResponseEntityErrorDetail =
     UpdateUserProfileResponseEntityErrorDetail(
        this.detail
    )

internal fun GetMyProfileResponse.toGetMyProfileResponseEntity (): GetMyProfileResponseEntity =
    GetMyProfileResponseEntity(
        this.code,
        this.data.toGetMyProfileResponseDataEntity(),
        this.message,
        this.status,
    )

internal fun GetMyProfileResponseData.toGetMyProfileResponseDataEntity (): GetMyProfileResponseDataEntity =
    GetMyProfileResponseDataEntity(
        this.configuration.toGetMyProfileResponseConfigurationEntity(),
        this.email,
        this.id,
        this.is_online,
        this.is_verified,
        this.phone,
        this.profile.toGetMyProfileResponseProfileEntity(),
        this.raiting,
        this.role,
    )

internal fun GetMyProfileResponseConfiguration.toGetMyProfileResponseConfigurationEntity (): GetMyProfileResponseConfigurationEntity =
    GetMyProfileResponseConfigurationEntity(
        this.email,
        this.phone,
        this.show_reviews
    )

internal fun GetMyProfileResponseProfile.toGetMyProfileResponseProfileEntity (): GetMyProfileResponseProfileEntity =
    GetMyProfileResponseProfileEntity(
        this.about_me,
        this.age,
        this.avatar_url,
        this.birthday,
        this.created_at,
        this.gender,
        this.height,
        this.id,
        this.last_name,
        this.name,
        this.place?.toGetMyProfileResponsePlaceEntity(),
        this.position,
        this.weight,
        this.working_leg
    )

internal fun GetMyProfileResponsePlace.toGetMyProfileResponsePlaceEntity (): GetMyProfileResponsePlaceEntity =
    GetMyProfileResponsePlaceEntity(
       this.place_name
    )

internal fun GetMyProfileError.toGetMyProfileErrorEntity (): GetMyProfileErrorEntity =
    GetMyProfileErrorEntity(
        this.code,
        this.data.toGetMyProfileErrorDataEntity(),
        this.message,
        this.status
    )

internal fun GetMyProfileErrorData.toGetMyProfileErrorDataEntity(): GetMyProfileErrorDataEntity =
    GetMyProfileErrorDataEntity(
        listOf(this.errors[0].toGetMyProfileErrorDetailEntity()),
        this.type
    )

internal fun GetMyProfileErrorDetail.toGetMyProfileErrorDetailEntity(): GetMyProfileErrorDetailEntity =
    GetMyProfileErrorDetailEntity(
        this.detail
    )

internal fun GetAllEventResponse.toGetAllEventResponseEntity(): GetAllEventResponseEntity =
    GetAllEventResponseEntity(
        this.code,
        this.data.toGetAllEventResponseEntityData(),
        this.message,
        this.status,
    )

internal fun GetAllEventResponseData.toGetAllEventResponseEntityData(): GetAllEventResponseEntityData =
    GetAllEventResponseEntityData(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results.map { it.toGetAllEventResponseEntityResult() },
        this.success,
        this.total_count,
    )

internal fun GetAllEventResponseResult.toGetAllEventResponseEntityResult(): GetAllEventResponseEntityResult =
    GetAllEventResponseEntityResult(
        this.amount_members,
        this.author.toGetAllEventResponseEntityAuthor(),
        this.count_current_fans,
        this.count_current_users,
        this.date_and_time,
        this.description,
        this.duration,
        this.forms,
        this.gender,
        this.id,
        this.name,
        this.need_ball,
        this.need_form,
        this.pk_user_role,
        this.place.toGetAllEventResponseEntityPlace(),
        this.price,
        this.privacy,
        this.request_user_role,
        this.status,
        this.type,
    )

internal fun GetAllEventResponseAuthor.toGetAllEventResponseEntityAuthor(): GetAllEventResponseEntityAuthor =
    GetAllEventResponseEntityAuthor(
        this.id,
        this.phone,
        this.profile.toGetAllEventResponseEntityProfile(),
    )

internal fun GetAllEventResponseProfile.toGetAllEventResponseEntityProfile(): GetAllEventResponseEntityProfile =
    GetAllEventResponseEntityProfile(
        this.avatar_url,
        this.id,
        this.last_name,
        this.name
    )

internal fun GetAllEventResponsePlace.toGetAllEventResponseEntityPlace(): GetAllEventResponseEntityPlace =
    GetAllEventResponseEntityPlace(
        this.lat,
        this.lon,
        this.place_name,
    )

internal fun GetAllEventResponseError.toGetAllEventEntityResponseError(): GetAllEventEntityResponseError =
    GetAllEventEntityResponseError(
        this.code,
        this.data.toGetAllEventEntityResponseErrorData(),
        this.message,
        this.status,
    )

internal fun GetAllEventResponseErrorData.toGetAllEventEntityResponseErrorData(): GetAllEventEntityResponseErrorData =
    GetAllEventEntityResponseErrorData(
        listOf(this.errors[0].toGetAllEventEntityResponseErrorDetail()),
        this.type,
    )

internal fun GetAllEventResponseErrorDetail.toGetAllEventEntityResponseErrorDetail(): GetAllEventEntityResponseErrorDetail =
    GetAllEventEntityResponseErrorDetail(
        this.detail
    )

internal fun CreationAnEventResponse.toCreationAnEventResponseEntity(): CreationAnEventResponseEntity =
    CreationAnEventResponseEntity(
        this.code,
        this.data.toCreationAnEventResponseEntityData(),
        this.message,
        this.status
    )

internal fun CreationAnEventResponseData.toCreationAnEventResponseEntityData(): CreationAnEventResponseEntityData =
    CreationAnEventResponseEntityData(
        this.amount_members,
        this.contact_number,
        this.date_and_time,
        this.description,
        this.duration,
        this.forms?.toCreationAnEventResponseEntityForms(),
        this.gender,
        this.hidden,
        this.name,
        this.need_ball,
        this.need_form,
        this.place.toCreationAnEventResponseEntityPlace(),
        this.price,
        this.price_description,
        this.privacy,
        this.type
    )

internal fun CreationAnEventResponseForms.toCreationAnEventResponseEntityForms(): CreationAnEventResponseEntityForms =
    CreationAnEventResponseEntityForms(
        // TODO("Not implemented on the backend")
    )

internal fun CreationAnEventResponsePlace.toCreationAnEventResponseEntityPlace(): CreationAnEventResponseEntityPlace =
    CreationAnEventResponseEntityPlace(
        this.lat,
        this.lon,
        this.place_name
    )

internal fun CreationAnEventError.toCreationAnEventErrorEntity(): CreationAnEventErrorEntity =
    CreationAnEventErrorEntity(
        this.code,
        this.data.toCreationAnEventErrorEntityData(),
        this.message,
        this.status
    )

internal fun CreationAnEventErrorData.toCreationAnEventErrorEntityData(): CreationAnEventErrorEntityData =
    CreationAnEventErrorEntityData(
        listOf(this.errors[0].toCreationAnEventErrorDetailEntity()),
        this.type
    )

internal fun CreationAnEventErrorDetail.toCreationAnEventErrorDetailEntity(): CreationAnEventErrorDetailEntity =
    CreationAnEventErrorDetailEntity(
        this.detail
    )

internal fun GetMyEventsResponse.toGetMyEventsResponseEntity(): GetMyEventsResponseEntity =
    GetMyEventsResponseEntity(
        this.code,
        this.data.toGetMyEventsResponseEntityData(),
        this.message,
        this.status,
    )

internal fun GetMyEventsResponseData.toGetMyEventsResponseEntityData(): GetMyEventsResponseEntityData =
    GetMyEventsResponseEntityData(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results.map { it.toGetMyEventsResponseEntityResult() },
        this.success,
        this.total_count,
    )

internal fun GetMyEventsResponseResult.toGetMyEventsResponseEntityResult(): GetMyEventsResponseEntityResult =
    GetMyEventsResponseEntityResult(
        this.amount_members,
        this.author.toGetMyEventsResponseEntityAuthor(),
        this.count_current_fans,
        this.count_current_users,
        this.date_and_time,
        this.description,
        this.duration,
        this.forms,
        this.gender,
        this.id,
        this.name,
        this.need_ball,
        this.need_form,
        this.pk_user_role,
        this.place.toGetMyEventsResponseEntityPlace(),
        this.price,
        this.privacy,
        this.request_user_role,
        this.status,
        this.type,
    )

internal fun GetMyEventsResponseAuthor.toGetMyEventsResponseEntityAuthor(): GetMyEventsResponseEntityAuthor =
    GetMyEventsResponseEntityAuthor(
        this.id,
        this.phone,
        this.profile.toGetMyEventsResponseEntityProfile(),
    )

internal fun GetMyEventsResponseProfile.toGetMyEventsResponseEntityProfile(): GetMyEventsResponseEntityProfile =
    GetMyEventsResponseEntityProfile(
        this.avatar_url,
        this.id,
        this.last_name,
        this.name
    )

internal fun GetMyEventsResponsePlace.toGetMyEventsResponseEntityPlace(): GetMyEventsResponseEntityPlace =
    GetMyEventsResponseEntityPlace(
        this.lat,
        this.lon,
        this.place_name,
    )

internal fun GetMyEventsResponseError.toGetMyEventsEntityResponseError(): GetMyEventsEntityResponseError =
    GetMyEventsEntityResponseError(
        this.code,
        this.data.toGetMyEventsEntityResponseErrorData(),
        this.message,
        this.status,
    )

internal fun GetMyEventsResponseErrorData.toGetMyEventsEntityResponseErrorData(): GetMyEventsEntityResponseErrorData =
    GetMyEventsEntityResponseErrorData(
        listOf(this.errors[0].toGetMyEventsEntityResponseErrorDetail()),
        this.type,
    )

internal fun GetMyEventsResponseErrorDetail.toGetMyEventsEntityResponseErrorDetail(): GetMyEventsEntityResponseErrorDetail =
    GetMyEventsEntityResponseErrorDetail(
        this.detail
    )

internal fun GetUsersListResponse.toGetUsersListResponseEntity(): GetUsersListResponseEntity =
    GetUsersListResponseEntity(
        this.code,
        this.data.toGetUsersListResponseDataEntity(),
        this.message,
        this.status
    )

internal fun GetUsersListResponseData.toGetUsersListResponseDataEntity(): GetUsersListResponseDataEntity =
    GetUsersListResponseDataEntity(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results?.map { it.toGetUsersListResponseResultEntity() },
        this.success,
        this.total_count,
    )

internal fun GetUsersListResponseResult.toGetUsersListResponseResultEntity(): GetUsersListResponseResultEntity =
    GetUsersListResponseResultEntity(
        this.id,
        this.is_online,
        this.profile.toGetUsersListResponseProfileEntity(),
        this.raiting,
        this.role
    )


internal fun GetUsersListResponseProfile.toGetUsersListResponseProfileEntity(): GetUsersListResponseProfileEntity =
    GetUsersListResponseProfileEntity(
        this.age,
        this.avatar_url,
        this.gender,
        this.id,
        this.last_name,
        this.name,
        this.position
    )

internal fun GetUsersListResponseError.toGetUsersListResponseErrorEntity(): GetUsersListResponseErrorEntity =
    GetUsersListResponseErrorEntity(
        this.code,
        this.data.toGetUsersListResponseErrorDataEntity(),
        this.message,
        this.status
    )

internal fun GetUsersListResponseErrorData.toGetUsersListResponseErrorDataEntity(): GetUsersListResponseErrorDataEntity =
    GetUsersListResponseErrorDataEntity(
        listOf(this.errors[0].toGetUsersListResponseErrorDetailDataEntity()),
        this.type,
    )

internal fun GetUsersListResponseErrorDetailData.toGetUsersListResponseErrorDetailDataEntity(): GetUsersListResponseErrorDetailDataEntity =
    GetUsersListResponseErrorDetailDataEntity(
        this.detail
    )

internal fun GetEventByIdResponse.toGetEventByIdResponse(): GetEventByIdResponseEntity =
    GetEventByIdResponseEntity(
        this.code,
        this.data.toGetEventByIdResponseDataEntity(),
        this.message,
        this.status
    )

internal fun GetEventByIdResponseData.toGetEventByIdResponseDataEntity(): GetEventByIdResponseDataEntity =
    GetEventByIdResponseDataEntity(
        this.amount_members,
        this.author.toGetEventByIdResponseAuthor(),
        this.contact_number,
        this.coordinates.toGetEventByIdResponseCoordinatesEntity(),
        this.current_fans,
        this.current_users,
        this.date_and_time,
        this.description,
        this.duration,
        this.forms.toGetEventByIdResponseFormsEntity(),
        this.gender,
        this.id,
        this.name,
        this.need_ball,
        this.need_form,
        this.place.toGetEventByIdResponsePlaceEntity(),
        this.price,
        this.price_description,
        this.privacy,
        this.request_user_role,
        this.status,
        this.type
    )

internal fun GetEventByIdResponseAuthor.toGetEventByIdResponseAuthor(): GetEventByIdResponseAuthorEntity =
    GetEventByIdResponseAuthorEntity(
        this.id,
        this.phone,
        this.profile.toGetEventByIdResponseProfileEntity(),
    )

internal fun GetEventByIdResponseCoordinates.toGetEventByIdResponseCoordinatesEntity(): GetEventByIdResponseCoordinatesEntity =
    GetEventByIdResponseCoordinatesEntity(
        this.coordinates,
        this.type
    )

internal fun GetEventByIdResponseForms.toGetEventByIdResponseFormsEntity(): GetEventByIdResponseFormsEntity =
    GetEventByIdResponseFormsEntity(
        // TODO("Not implemented on the backend")
    )

internal fun GetEventByIdResponsePlace.toGetEventByIdResponsePlaceEntity(): GetEventByIdResponsePlaceEntity =
    GetEventByIdResponsePlaceEntity(
        this.lat,
        this.lon,
        this.place_name,
    )

internal fun GetEventByIdResponseProfile.toGetEventByIdResponseProfileEntity(): GetEventByIdResponseProfileEntity =
    GetEventByIdResponseProfileEntity(
        this.avatar_url,
        this.id,
        this.last_name,
        this.name
    )

internal fun GetEventByIdResponseError.toGetEventByIdResponseErrorEntity(): GetEventByIdResponseErrorEntity =
    GetEventByIdResponseErrorEntity(
        this.code,
        this.data.toGetEventByIdResponseErrorDataEntity(),
        this.message,
        this.status,
    )

internal fun GetEventByIdResponseErrorData.toGetEventByIdResponseErrorDataEntity(): GetEventByIdResponseErrorDataEntity =
    GetEventByIdResponseErrorDataEntity(
        listOf(this.errors[0].toGetEventByIdResponseErrorDetailEntity()),
        this.type,
    )

internal fun GetEventByIdResponseErrorDetail.toGetEventByIdResponseErrorDetailEntity(): GetEventByIdResponseErrorDetailEntity =
    GetEventByIdResponseErrorDetailEntity(
        this.detail
    )