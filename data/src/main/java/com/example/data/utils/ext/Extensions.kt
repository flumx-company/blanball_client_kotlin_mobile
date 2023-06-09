package com.example.data.utils.ext

import com.example.data.backend.models.responses.Configuration
import com.example.data.backend.models.responses.Data
import com.example.data.backend.models.responses.DataEmailReset
import com.example.data.backend.models.responses.DataError
import com.example.data.backend.models.responses.DataResetCompleteError
import com.example.data.backend.models.responses.DataResetCompleteResponse
import com.example.data.backend.models.responses.DataSendCode
import com.example.data.backend.models.responses.EmailPassDataError
import com.example.data.backend.models.responses.EmailPassResetError
import com.example.data.backend.models.responses.EmailPassResetErrors
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
import com.example.domain.entity.responses.ConfigurationEntity
import com.example.domain.entity.responses.DataCompleteResponseEntity
import com.example.domain.entity.responses.DataEmailResetEntity
import com.example.domain.entity.responses.DataSendCodeDomain
import com.example.domain.entity.responses.EmailPassDataErrorEntity
import com.example.domain.entity.responses.EmailPassResetErrorEntity
import com.example.domain.entity.responses.EmailPassResetErrorsEntity
import com.example.domain.entity.responses.EmailResetResponseEntity
import com.example.domain.entity.responses.ErrorResponse
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

internal fun RegistrationError.toRegistrationErrorEntity(): RegistrationErrorEntity {
    return RegistrationErrorEntity(
        this.code,
        this.data.toRegistrationErrorsDataEntity(),
        this.message,
        this.status,
    )
}

internal fun RegistrationErrorsData.toRegistrationErrorsDataEntity(): RegistrationErrorsDataEntity {
    return RegistrationErrorsDataEntity(
        listOf(this.errors[0].toRegistrationErrorDetailEntity()),
        this.type
    )
}

internal fun RegistrationErrorDetail.toRegistrationErrorDetailEntity(): RegistrationErrorDetailEntity {
    return RegistrationErrorDetailEntity(this.detail)
}

internal fun RegistrationResponse.toRegistrationResponseEntity(): RegistrationResponseEntity {
    return RegistrationResponseEntity(
        this.code,
        this.data.toRegistrationDataEntity(),
        this.message,
        this.status
    )
}

internal fun RegistrationData.toRegistrationDataEntity(): RegistrationDataEntity {
    return RegistrationDataEntity(this.access, this.refresh)
}

internal fun ResetCompleteError.toResetCompleteErrorEntity(): ResetCompleteErrorEntity {
    return ResetCompleteErrorEntity(
        this.code,
        this.data.toResetCompleteDataEntity(),
        this.message,
        this.status
    )
}

internal fun DataResetCompleteError.toResetCompleteDataEntity(): ResetCompleteDataEntity {
    return ResetCompleteDataEntity(
        listOf(this.errors[0].toResetCompleteErrorsEntity()),
        this.type
    )
}


internal fun ResetCompleteErrors.toResetCompleteErrorsEntity(): ResetCompleteErrorsEntity {
    return ResetCompleteErrorsEntity(this.detail)
}


internal fun DataResetCompleteResponse.toResetCompleteResponseEntity(): DataCompleteResponseEntity {
    return DataCompleteResponseEntity(this.success)
}

internal fun ResetCompleteResponse.toResetCompleteResponseEntity(): ResetCompleteResponseEntity {
    return ResetCompleteResponseEntity(
        this.code,
        this.data.toResetCompleteResponseEntity(),
        this.message,
        this.status
    )
}

internal fun SendCodeError.toSendCodeErrorEntity(): SendCodeErrorEntity {
    return SendCodeErrorEntity(
        this.code,
        this.data.toSendCodeDataErrorEntity(),
        this.message,
        this.status
    )
}

internal fun SendCodeDataError.toSendCodeDataErrorEntity(): SendCodeDataErrorEntity {
    return SendCodeDataErrorEntity(
        listOf(this.errors[0].toSendCodeErrorsEntity()),
        this.type
    )
}

internal fun SendCodeErrors.toSendCodeErrorsEntity(): SendCodeErrorsEntity {
    return SendCodeErrorsEntity(this.detail)
}

internal fun SendCodeResponse.toSendCodeResponseEntity(): SendCodeResponseEntity {
    return SendCodeResponseEntity(
        this.code,
        this.data.toDataSendDomain(),
        this.message,
        this.status
    )
}

internal fun DataSendCode.toDataSendDomain(): DataSendCodeDomain {
    return DataSendCodeDomain(this.success)
}

internal fun EmailPassResetError.toEmailPassResetErrorEntity(): EmailPassResetErrorEntity {
    return EmailPassResetErrorEntity(
        this.code,
        this.data.toEmailPassDataErrorEntity(),
        this.message,
        this.status
    )
}

internal fun EmailPassDataError.toEmailPassDataErrorEntity(): EmailPassDataErrorEntity {
    return EmailPassDataErrorEntity(
        listOf(this.errors[0].toEmailPassDataErrorEntity()),
        this.type
    )
}

internal fun EmailPassResetErrors.toEmailPassDataErrorEntity(): EmailPassResetErrorsEntity {
    return EmailPassResetErrorsEntity(this.detail)
}

internal fun SendEmailPasswordResetSuccess.toEmailResetResponse(): EmailResetResponseEntity {
    return EmailResetResponseEntity(
        this.code,
        this.data.toDataEmailReset(),
        this.message,
        this.status
    )
}

internal fun DataEmailReset.toDataEmailReset(): DataEmailResetEntity {
    return DataEmailResetEntity(this.success)
}

internal fun LoginSuccess.toLoginResponse(): LoginResponse {

    return LoginResponse(this.code, this.data.toLoginData(), this.message, this.status)
}

internal fun Data.toLoginData(): LoginData {
    return LoginData(this.email, this.tokens.toLoginTokens())
}

internal fun Tokens.toLoginTokens(): LoginTokens {
    return LoginTokens(this.access, this.refresh)
}

internal fun LoginError.toErrorResponse(): ErrorResponse {
    return ErrorResponse(this.code, this.data.toDataError(), this.message, this.status)
}

internal fun DataError.toDataError(): LoginDataError {
    return LoginDataError(listOf(this.errors[0].toLoginErrors()), this.type)
}

internal fun LoginErrors.toLoginErrors(): LoginErrorsDomain {
    return LoginErrorsDomain(this.detail)
}

internal fun GetUserProfileByIdResponse.toGetUserProfileByIdResponseEntity(): GetUserProfileByIdResponseEntity {
    return GetUserProfileByIdResponseEntity(
        this.code,
        this.data.toPublicProfileDataResponseEntity(),
        this.message,
        this.status
    )
}

internal fun PublicProfileDataResponse.toPublicProfileDataResponseEntity(): PublicProfileDataResponseEntity {
    return PublicProfileDataResponseEntity(
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
}

internal fun Configuration.toConfigurationEntity(): ConfigurationEntity {
    return ConfigurationEntity(this.email, this.phone, this.show_reviews)
}

internal fun PublicProfileResponse.toPublicProfileResponseEntity(): PublicProfileResponseEntity {
    return PublicProfileResponseEntity(
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
        this.place.toPlayingPlaceEntity(),
        this.position,
        this.weight,
        this.working_leg
    )
}

internal fun PlayingPlace.toPlayingPlaceEntity(): PlayingPlaceEntity {
    return PlayingPlaceEntity(this.place_name)
}

internal fun GetUserProfileByIdError.toGetUserProfileByIdErrorEntity(): GetUserProfileByIdErrorEntity {
    return GetUserProfileByIdErrorEntity(
        this.code,
        this.data.toGetUserProfileByIdErrorDataEntity(),
        this.message,
        this.status
    )
}

internal fun GetUserProfileByIdErrorData.toGetUserProfileByIdErrorDataEntity(): GetUserProfileByIdErrorDataEntity {
    return GetUserProfileByIdErrorDataEntity(
        listOf(
            this.errors[0].toGetUserProfileByIdDetailDataEntity()
        ), this.type
    )
}

internal fun GetUserProfileByIdDetailData.toGetUserProfileByIdDetailDataEntity(): GetUserProfileByIdDetailDataEntity {
    return GetUserProfileByIdDetailDataEntity(this.detail)
}

internal fun GetUserReviewsByIdResponse.toGetUserReviewsByIdResponseEntity(): GetUserReviewsByIdResponseEntity {
    return GetUserReviewsByIdResponseEntity(
        this.code,
        this.data.toGetUserReviewsByIdDataEntity(),
        this.message,
        this.status
    )
}

internal fun GetUserReviewsByIdData.toGetUserReviewsByIdDataEntity(): GetUserReviewsByIdDataEntity {
    return GetUserReviewsByIdDataEntity(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results?.map { it.toGetUserReviewsByIdResponseResultEntity() },
        this.success,
        this.total_count,
    )
}

internal fun GetUserReviewsByIdResponseResult.toGetUserReviewsByIdResponseResultEntity(): GetUserReviewsByIdResponseResultEntity {
    return GetUserReviewsByIdResponseResultEntity(
        this.author.toGetUserReviewsByIdResponseAuthorEntity(),
        this.id,
        this.stars,
        this.text,
        this.time_created
    )
}

internal fun GetUserReviewsByIdResponseAuthor.toGetUserReviewsByIdResponseAuthorEntity(): GetUserReviewsByIdResponseAuthorEntity {
    return GetUserReviewsByIdResponseAuthorEntity(
        this.id,
        this.profile.toGetUserReviewsByIdResponseProfile(),
    )
}

internal fun GetUserReviewsByIdResponseProfile.toGetUserReviewsByIdResponseProfile(): GetUserReviewsByIdResponseProfileEntity {
    return GetUserReviewsByIdResponseProfileEntity(
        this.last_name,
        this.name,
    )
}

internal fun GetUserReviewsByIdResponseError.toGetUserReviewsByIdResponseErrorEntity(): GetUserReviewsByIdResponseErrorEntity {
    return GetUserReviewsByIdResponseErrorEntity(
        this.code,
        this.data.toGetUserReviewsByIdResponseErrorDataEntity(),
        this.message,
        this.status
    )
}

internal fun GetUserReviewsByIdResponseErrorData.toGetUserReviewsByIdResponseErrorDataEntity(): GetUserReviewsByIdResponseErrorDataEntity {
    return GetUserReviewsByIdResponseErrorDataEntity(
        listOf(
            this.errors[0].toGetUserReviewsByIdResponseDetailDataEntity()
        ),
        this.type,
    )
}

internal fun GetUserReviewsByIdResponseDetailData.toGetUserReviewsByIdResponseDetailDataEntity(): GetUserReviewsByIdResponseDetailDataEntity {
    return GetUserReviewsByIdResponseDetailDataEntity(this.detail)
}

internal fun GetUserPlannedEventsByIdResponse.toGetUserPlannedEventsByIdResponseEntity(): GetUserPlannedEventsByIdResponseEntity {
    return GetUserPlannedEventsByIdResponseEntity(
        this.code, this.data.toGetUserPlannedEventsByIdDataResponseEntity(), this.message, this.status
    )
}

internal fun GetUserPlannedEventsByIdDataResponse.toGetUserPlannedEventsByIdDataResponseEntity(): GetUserPlannedEventsByIdDataResponseEntity {
    return GetUserPlannedEventsByIdDataResponseEntity(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results?.map { it.toGetUserPlannedEventsByIdResultResponseEntity() },
        this.success,
        this.total_count
    )
}

internal fun GetUserPlannedEventsByIdResultResponse.toGetUserPlannedEventsByIdResultResponseEntity(): GetUserPlannedEventsByIdResultResponseEntity {
    return GetUserPlannedEventsByIdResultResponseEntity(
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
}

internal fun GetUserPlannedEventsByIdAuthorResponse.toGetUserPlannedEventsByIdAuthorResponseEntity(): GetUserPlannedEventsByIdAuthorResponseEntity {
    return GetUserPlannedEventsByIdAuthorResponseEntity(
        this.id,
        this.phone,
        this.profile.toGetUserPlannedEventsByIdProfileResponseEntity(),
    )
}

internal fun GetUserPlannedEventsByIdProfileResponse.toGetUserPlannedEventsByIdProfileResponseEntity(): GetUserPlannedEventsByIdProfileResponseEntity {
    return GetUserPlannedEventsByIdProfileResponseEntity(
        this.avatar_url, this.id, this.last_name, this.name
    )
}

internal fun GetUserPlannedEventsByIdPlaceResponse.toGetUserPlannedEventsByIdPlaceResponseEntity(): GetUserPlannedEventsByIdPlaceResponseEntity {
    return GetUserPlannedEventsByIdPlaceResponseEntity(this.lat, this.lon, this.place_name)
}

internal fun GetUserPlannedEventsByIdError.toGetUserPlannedEventsByIdErrorEntity(): GetUserPlannedEventsByIdErrorEntity {
    return GetUserPlannedEventsByIdErrorEntity(
        this.code, this.data.toGetUserPlannedByIdErrorData(), this.message, this.status
    )
}

internal fun GetUserPlannedEventsByIdErrorData.toGetUserPlannedByIdErrorData(): GetUserPlannedEventsByIdErrorDataEntity {
    return GetUserPlannedEventsByIdErrorDataEntity(
        listOf(
            this.errors[0].toGetUserPlannedEventsByIdDetailDataEntity()
        ), this.type
    )
}

internal fun GetUserPlannedEventsByIdDetailData.toGetUserPlannedEventsByIdDetailDataEntity(): GetUserPlannedEventsByIdDetailDataEntity {
    return GetUserPlannedEventsByIdDetailDataEntity(this.detail)
}

internal fun GetUsersListResponse.toGetUsersListResponseEntity(): GetUsersListResponseEntity {
    return GetUsersListResponseEntity(
        this.code,
        this.data.toGetUsersListResponseDataEntity(),
        this.message,
        this.status
    )
}

internal fun GetUsersListResponseData.toGetUsersListResponseDataEntity(): GetUsersListResponseDataEntity {
    return GetUsersListResponseDataEntity(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results?.map { it.toGetUsersListResponseResultEntity() },
        this.success,
        this.total_count,
    )
}

internal fun GetUsersListResponseResult.toGetUsersListResponseResultEntity(): GetUsersListResponseResultEntity {
    return GetUsersListResponseResultEntity(
        this.id,
        this.is_online,
        this.profile.toGetUsersListResponseProfileEntity(),
        this.raiting,
        this.role
    )
}

internal fun GetUsersListResponseProfile.toGetUsersListResponseProfileEntity(): GetUsersListResponseProfileEntity {
    return GetUsersListResponseProfileEntity(
        this.age,
        this.avatar_url,
        this.gender,
        this.id,
        this.last_name,
        this.name,
        this.position
    )
}

internal fun GetUsersListResponseError.toGetUsersListResponseErrorEntity(): GetUsersListResponseErrorEntity {
    return GetUsersListResponseErrorEntity(
        this.code,
        this.data.toGetUsersListResponseErrorDataEntity(),
        this.message,
        this.status
    )
}

internal fun GetUsersListResponseErrorData.toGetUsersListResponseErrorDataEntity(): GetUsersListResponseErrorDataEntity {
    return GetUsersListResponseErrorDataEntity(
        listOf(this.errors[0].toGetUsersListResponseErrorDetailDataEntity()),
        this.type,
    )
}

internal fun GetUsersListResponseErrorDetailData.toGetUsersListResponseErrorDetailDataEntity(): GetUsersListResponseErrorDetailDataEntity {
    return  GetUsersListResponseErrorDetailDataEntity(
        this.detail)
}