package com.example.data.utils.ext

import com.example.data.network.models.responses.errors.CreationAnEventError
import com.example.data.network.models.responses.errors.CreationAnEventErrorData
import com.example.data.network.models.responses.errors.CreationAnEventErrorDetail
import com.example.data.network.models.responses.errors.DataError
import com.example.data.network.models.responses.errors.DataResetCompleteError
import com.example.data.network.models.responses.errors.EditEventByIdResponseError
import com.example.data.network.models.responses.errors.EditEventByIdResponseErrorData
import com.example.data.network.models.responses.errors.EditEventByIdResponseErrorDetail
import com.example.data.network.models.responses.errors.EditMyProfileError
import com.example.data.network.models.responses.errors.EditMyProfileErrorData
import com.example.data.network.models.responses.errors.EditMyProfileErrorDetail
import com.example.data.network.models.responses.errors.EmailPassDataError
import com.example.data.network.models.responses.errors.EmailPassResetError
import com.example.data.network.models.responses.errors.EmailPassResetErrors
import com.example.data.network.models.responses.errors.GetAllEventResponseError
import com.example.data.network.models.responses.errors.GetAllEventResponseErrorData
import com.example.data.network.models.responses.errors.GetAllEventResponseErrorDetail
import com.example.data.network.models.responses.errors.GetEventByIdResponseError
import com.example.data.network.models.responses.errors.GetEventByIdResponseErrorData
import com.example.data.network.models.responses.errors.GetEventByIdResponseErrorDetail
import com.example.data.network.models.responses.errors.GetIsTechnicalWorkStatusError
import com.example.data.network.models.responses.errors.GetIsTechnicalWorkStatusErrorData
import com.example.data.network.models.responses.errors.GetIsTechnicalWorkStatusErrorDetail
import com.example.data.network.models.responses.errors.GetMyEventsResponseError
import com.example.data.network.models.responses.errors.GetMyEventsResponseErrorData
import com.example.data.network.models.responses.errors.GetMyEventsResponseErrorDetail
import com.example.data.network.models.responses.errors.GetMyProfileError
import com.example.data.network.models.responses.errors.GetMyProfileErrorData
import com.example.data.network.models.responses.errors.GetMyProfileErrorDetail
import com.example.data.network.models.responses.errors.GetPrivateRequestListResponseError
import com.example.data.network.models.responses.errors.GetPrivateRequestListResponseErrorData
import com.example.data.network.models.responses.errors.GetPrivateRequestListResponseErrorDetail
import com.example.data.network.models.responses.errors.GetRelevantUserSearchListError
import com.example.data.network.models.responses.errors.GetRelevantUserSearchListErrorData
import com.example.data.network.models.responses.errors.GetRelevantUserSearchListErrorDetail
import com.example.data.network.models.responses.errors.GetUkraineCitiesListDataError
import com.example.data.network.models.responses.errors.GetUkraineCitiesListError
import com.example.data.network.models.responses.errors.GetUkraineCitiesListErrorDetail
import com.example.data.network.models.responses.errors.GetUserPlannedEventsByIdDetailData
import com.example.data.network.models.responses.errors.GetUserPlannedEventsByIdError
import com.example.data.network.models.responses.errors.GetUserPlannedEventsByIdErrorData
import com.example.data.network.models.responses.errors.GetUserProfileByIdDetailData
import com.example.data.network.models.responses.errors.GetUserProfileByIdError
import com.example.data.network.models.responses.errors.GetUserProfileByIdErrorData
import com.example.data.network.models.responses.errors.GetUserReviewsByIdResponseDetailData
import com.example.data.network.models.responses.errors.GetUserReviewsByIdResponseError
import com.example.data.network.models.responses.errors.GetUserReviewsByIdResponseErrorData
import com.example.data.network.models.responses.errors.GetUsersListResponseError
import com.example.data.network.models.responses.errors.GetUsersListResponseErrorData
import com.example.data.network.models.responses.errors.GetUsersListResponseErrorDetailData
import com.example.data.network.models.responses.errors.JoinToEventAsFunDetailData
import com.example.data.network.models.responses.errors.JoinToEventAsFunError
import com.example.data.network.models.responses.errors.JoinToEventAsFunErrorData
import com.example.data.network.models.responses.errors.JoinToEventAsPlayerDetailData
import com.example.data.network.models.responses.errors.JoinToEventAsPlayerError
import com.example.data.network.models.responses.errors.JoinToEventAsPlayerErrorData
import com.example.data.network.models.responses.errors.LeaveTheEventAsFunDetailData
import com.example.data.network.models.responses.errors.LeaveTheEventAsFunError
import com.example.data.network.models.responses.errors.LeaveTheEventAsFunErrorData
import com.example.data.network.models.responses.errors.LeaveTheEventAsPlayerDetailData
import com.example.data.network.models.responses.errors.LeaveTheEventAsPlayerError
import com.example.data.network.models.responses.errors.LeaveTheEventAsPlayerErrorData
import com.example.data.network.models.responses.errors.LoginError
import com.example.data.network.models.responses.errors.LoginErrors
import com.example.data.network.models.responses.errors.PostEmailVerifyCodeDataError
import com.example.data.network.models.responses.errors.PostEmailVerifyCodeError
import com.example.data.network.models.responses.errors.PostEmailVerifyCodeErrorDetail
import com.example.data.network.models.responses.errors.RegistrationError
import com.example.data.network.models.responses.errors.RegistrationErrorDetail
import com.example.data.network.models.responses.errors.RegistrationErrorsData
import com.example.data.network.models.responses.errors.ResetCompleteError
import com.example.data.network.models.responses.errors.ResetCompleteErrors
import com.example.data.network.models.responses.errors.SendCodeDataError
import com.example.data.network.models.responses.errors.SendCodeError
import com.example.data.network.models.responses.errors.SendCodeErrors
import com.example.data.network.models.responses.errors.SendVerifyCodeToUserEmailError
import com.example.data.network.models.responses.errors.SendVerifyCodeToUserEmailErrorData
import com.example.data.network.models.responses.errors.SendVerifyCodeToUserEmailErrorDataDetail
import com.example.data.network.models.responses.errors.UpdateUserProfileResponseError
import com.example.data.network.models.responses.errors.UpdateUserProfileResponseErrorData
import com.example.data.network.models.responses.errors.UpdateUserProfileResponseErrorDetail
import com.example.data.network.models.responses.success.Configuration
import com.example.data.network.models.responses.success.CreationAnEventResponse
import com.example.data.network.models.responses.success.CreationAnEventResponseData
import com.example.data.network.models.responses.success.CreationAnEventResponseForms
import com.example.data.network.models.responses.success.CreationAnEventResponsePlace
import com.example.data.network.models.responses.success.Data
import com.example.data.network.models.responses.success.DataEmailReset
import com.example.data.network.models.responses.success.DataResetCompleteResponse
import com.example.data.network.models.responses.success.DataSendCode
import com.example.data.network.models.responses.success.EditEventByIdResponse
import com.example.data.network.models.responses.success.EditEventByIdResponseData
import com.example.data.network.models.responses.success.EditMyProfileResponse
import com.example.data.network.models.responses.success.EditMyProfileResponseConfiguration
import com.example.data.network.models.responses.success.EditMyProfileResponseData
import com.example.data.network.models.responses.success.EditMyProfileResponseProfile
import com.example.data.network.models.responses.success.GetAllEventResponse
import com.example.data.network.models.responses.success.GetAllEventResponseAuthor
import com.example.data.network.models.responses.success.GetAllEventResponseData
import com.example.data.network.models.responses.success.GetAllEventResponsePlace
import com.example.data.network.models.responses.success.GetAllEventResponseProfile
import com.example.data.network.models.responses.success.GetAllEventResponseResult
import com.example.data.network.models.responses.success.GetEventByIdResponse
import com.example.data.network.models.responses.success.GetEventByIdResponseAuthor
import com.example.data.network.models.responses.success.GetEventByIdResponseCoordinates
import com.example.data.network.models.responses.success.GetEventByIdResponseCurrentFan
import com.example.data.network.models.responses.success.GetEventByIdResponseCurrentUser
import com.example.data.network.models.responses.success.GetEventByIdResponseData
import com.example.data.network.models.responses.success.GetEventByIdResponseFanProfileX
import com.example.data.network.models.responses.success.GetEventByIdResponseForms
import com.example.data.network.models.responses.success.GetEventByIdResponsePlace
import com.example.data.network.models.responses.success.GetEventByIdResponsePlayerProfileX
import com.example.data.network.models.responses.success.GetEventByIdResponseProfile
import com.example.data.network.models.responses.success.GetIsTechnicalWorkStatusResponse
import com.example.data.network.models.responses.success.GetIsTechnicalWorkStatusResponseData
import com.example.data.network.models.responses.success.GetMyEventsResponse
import com.example.data.network.models.responses.success.GetMyEventsResponseAuthor
import com.example.data.network.models.responses.success.GetMyEventsResponseData
import com.example.data.network.models.responses.success.GetMyEventsResponsePlace
import com.example.data.network.models.responses.success.GetMyEventsResponseProfile
import com.example.data.network.models.responses.success.GetMyEventsResponseResult
import com.example.data.network.models.responses.success.GetMyProfileResponse
import com.example.data.network.models.responses.success.GetMyProfileResponseConfiguration
import com.example.data.network.models.responses.success.GetMyProfileResponseData
import com.example.data.network.models.responses.success.GetMyProfileResponsePlace
import com.example.data.network.models.responses.success.GetMyProfileResponseProfile
import com.example.data.network.models.responses.success.GetPrivateRequestListResponse
import com.example.data.network.models.responses.success.GetPrivateRequestListResponseData
import com.example.data.network.models.responses.success.GetPrivateRequestListResponseEvent
import com.example.data.network.models.responses.success.GetPrivateRequestListResponseProfile
import com.example.data.network.models.responses.success.GetPrivateRequestListResponseResult
import com.example.data.network.models.responses.success.GetPrivateRequestListResponseSender
import com.example.data.network.models.responses.success.GetRelevantUserSearchListResponse
import com.example.data.network.models.responses.success.GetRelevantUserSearchListResponseData
import com.example.data.network.models.responses.success.GetRelevantUserSearchListResponseProfile
import com.example.data.network.models.responses.success.GetRelevantUserSearchListResponseResult
import com.example.data.network.models.responses.success.GetUkraineCitiesListResponse
import com.example.data.network.models.responses.success.GetUkraineCitiesListResponseData
import com.example.data.network.models.responses.success.GetUkraineCitiesListResponseItem
import com.example.data.network.models.responses.success.GetUserPlannedEventsByIdAuthorResponse
import com.example.data.network.models.responses.success.GetUserPlannedEventsByIdDataResponse
import com.example.data.network.models.responses.success.GetUserPlannedEventsByIdPlaceResponse
import com.example.data.network.models.responses.success.GetUserPlannedEventsByIdProfileResponse
import com.example.data.network.models.responses.success.GetUserPlannedEventsByIdResponse
import com.example.data.network.models.responses.success.GetUserPlannedEventsByIdResultResponse
import com.example.data.network.models.responses.success.GetUserProfileByIdResponse
import com.example.data.network.models.responses.success.GetUserReviewsByIdData
import com.example.data.network.models.responses.success.GetUserReviewsByIdResponse
import com.example.data.network.models.responses.success.GetUserReviewsByIdResponseAuthor
import com.example.data.network.models.responses.success.GetUserReviewsByIdResponseProfile
import com.example.data.network.models.responses.success.GetUserReviewsByIdResponseResult
import com.example.data.network.models.responses.success.GetUsersListResponse
import com.example.data.network.models.responses.success.GetUsersListResponseData
import com.example.data.network.models.responses.success.GetUsersListResponseProfile
import com.example.data.network.models.responses.success.GetUsersListResponseResult
import com.example.data.network.models.responses.success.JoinToEventAsFunResponse
import com.example.data.network.models.responses.success.JoinToEventAsFunResponseData
import com.example.data.network.models.responses.success.JoinToEventAsPlayerResponse
import com.example.data.network.models.responses.success.JoinToEventAsPlayerResponseData
import com.example.data.network.models.responses.success.LeaveTheEventAsFanResponse
import com.example.data.network.models.responses.success.LeaveTheEventAsFanResponseData
import com.example.data.network.models.responses.success.LeaveTheEventAsPlayerResponse
import com.example.data.network.models.responses.success.LeaveTheEventAsPlayerResponseData
import com.example.data.network.models.responses.success.LoginSuccess
import com.example.data.network.models.responses.success.PlayingPlace
import com.example.data.network.models.responses.success.PostEmailVerifyCodeResponse
import com.example.data.network.models.responses.success.PostEmailVerifyCodeResponseData
import com.example.data.network.models.responses.success.PublicProfileDataResponse
import com.example.data.network.models.responses.success.PublicProfileResponse
import com.example.data.network.models.responses.success.RegistrationData
import com.example.data.network.models.responses.success.RegistrationResponse
import com.example.data.network.models.responses.success.ResetCompleteResponse
import com.example.data.network.models.responses.success.SendCodeResponse
import com.example.data.network.models.responses.success.SendEmailPasswordResetSuccess
import com.example.data.network.models.responses.success.SendVerifyCodeToUserEmailResponse
import com.example.data.network.models.responses.success.SendVerifyCodeToUserEmailResponseData
import com.example.data.network.models.responses.success.Tokens
import com.example.data.network.models.responses.success.UpdateUserProfileResponse
import com.example.data.network.models.responses.success.UpdateUserProfileResponseConfiguration
import com.example.data.network.models.responses.success.UpdateUserProfileResponseData
import com.example.data.network.models.responses.success.UpdateUserProfileResponsePlace
import com.example.data.network.models.responses.success.UpdateUserProfileResponseProfile
import com.example.domain.entity.responses.errors.CreationAnEventErrorDetailEntity
import com.example.domain.entity.responses.errors.CreationAnEventErrorEntity
import com.example.domain.entity.responses.errors.CreationAnEventErrorEntityData
import com.example.domain.entity.responses.errors.EditEventByIdResponseErrorDataEntity
import com.example.domain.entity.responses.errors.EditEventByIdResponseErrorDetailEntity
import com.example.domain.entity.responses.errors.EditEventByIdResponseErrorEntity
import com.example.domain.entity.responses.errors.EditMyProfileErrorEntity
import com.example.domain.entity.responses.errors.EditMyProfileErrorEntityData
import com.example.domain.entity.responses.errors.EditMyProfileErrorEntityDetail
import com.example.domain.entity.responses.errors.GetAllEventEntityResponseError
import com.example.domain.entity.responses.errors.GetAllEventEntityResponseErrorData
import com.example.domain.entity.responses.errors.GetAllEventEntityResponseErrorDetail
import com.example.domain.entity.responses.errors.GetEventByIdResponseErrorDataEntity
import com.example.domain.entity.responses.errors.GetEventByIdResponseErrorDetailEntity
import com.example.domain.entity.responses.errors.GetEventByIdResponseErrorEntity
import com.example.domain.entity.responses.errors.GetIsTechnicalWorkStatusErrorDataEntity
import com.example.domain.entity.responses.errors.GetIsTechnicalWorkStatusErrorDetailEntity
import com.example.domain.entity.responses.errors.GetIsTechnicalWorkStatusErrorEntity
import com.example.domain.entity.responses.errors.GetMyEventsEntityResponseError
import com.example.domain.entity.responses.errors.GetMyEventsEntityResponseErrorData
import com.example.domain.entity.responses.errors.GetMyEventsEntityResponseErrorDetail
import com.example.domain.entity.responses.errors.GetMyProfileErrorDataEntity
import com.example.domain.entity.responses.errors.GetMyProfileErrorDetailEntity
import com.example.domain.entity.responses.errors.GetMyProfileErrorEntity
import com.example.domain.entity.responses.errors.GetPrivateRequestListResponseErrorDetailEntity
import com.example.domain.entity.responses.errors.GetPrivateRequestListResponseErrorEntity
import com.example.domain.entity.responses.errors.GetPrivateRequestListResponseErrorEntityData
import com.example.domain.entity.responses.errors.GetRelevantUserSearchListErrorEntity
import com.example.domain.entity.responses.errors.GetRelevantUserSearchListErrorEntityData
import com.example.domain.entity.responses.errors.GetRelevantUserSearchListErrorEntityDetail
import com.example.domain.entity.responses.errors.GetUkraineCitiesListEntityErrorData
import com.example.domain.entity.responses.errors.GetUkraineCitiesListEntityErrorDetail
import com.example.domain.entity.responses.errors.GetUkraineCitiesListErrorEntity
import com.example.domain.entity.responses.errors.GetUserPlannedEventsByIdDetailDataEntity
import com.example.domain.entity.responses.errors.GetUserPlannedEventsByIdErrorDataEntity
import com.example.domain.entity.responses.errors.GetUserPlannedEventsByIdErrorEntity
import com.example.domain.entity.responses.errors.GetUserProfileByIdDetailDataEntity
import com.example.domain.entity.responses.errors.GetUserProfileByIdErrorDataEntity
import com.example.domain.entity.responses.errors.GetUserProfileByIdErrorEntity
import com.example.domain.entity.responses.errors.GetUserReviewsByIdResponseDetailDataEntity
import com.example.domain.entity.responses.errors.GetUserReviewsByIdResponseErrorDataEntity
import com.example.domain.entity.responses.errors.GetUserReviewsByIdResponseErrorEntity
import com.example.domain.entity.responses.errors.GetUsersListResponseErrorDataEntity
import com.example.domain.entity.responses.errors.GetUsersListResponseErrorDetailDataEntity
import com.example.domain.entity.responses.errors.GetUsersListResponseErrorEntity
import com.example.domain.entity.responses.errors.JoinToEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.JoinToEventAsFunErrorDataEntity
import com.example.domain.entity.responses.errors.JoinToEventAsFunErrorEntity
import com.example.domain.entity.responses.errors.JoinToEventAsPlayerDetailDataEntity
import com.example.domain.entity.responses.errors.JoinToEventAsPlayerErrorDataEntity
import com.example.domain.entity.responses.errors.JoinToEventAsPlayerErrorEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsFunErrorDataEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsFunErrorEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsPlayerDetailDataEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsPlayerErrorDataEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsPlayerErrorEntity
import com.example.domain.entity.responses.errors.PostEmailVerifyCodeDataErrorEntity
import com.example.domain.entity.responses.errors.PostEmailVerifyCodeErrorEntity
import com.example.domain.entity.responses.errors.PostEmailVerifyCodeErrorEntityDetail
import com.example.domain.entity.responses.errors.RegistrationErrorDetailEntity
import com.example.domain.entity.responses.errors.RegistrationErrorEntity
import com.example.domain.entity.responses.errors.RegistrationErrorsDataEntity
import com.example.domain.entity.responses.errors.SendVerifyCodeToUserEmailErrorEntity
import com.example.domain.entity.responses.errors.SendVerifyCodeToUserEmailErrorEntityData
import com.example.domain.entity.responses.errors.SendVerifyCodeToUserEmailErrorEntityDataDetail
import com.example.domain.entity.responses.errors.UpdateUserProfileResponseEntityError
import com.example.domain.entity.responses.errors.UpdateUserProfileResponseEntityErrorData
import com.example.domain.entity.responses.errors.UpdateUserProfileResponseEntityErrorDetail
import com.example.domain.entity.responses.success.ConfigurationEntity
import com.example.domain.entity.responses.success.CreationAnEventResponseEntity
import com.example.domain.entity.responses.success.CreationAnEventResponseEntityData
import com.example.domain.entity.responses.success.CreationAnEventResponseEntityForms
import com.example.domain.entity.responses.success.CreationAnEventResponseEntityPlace
import com.example.domain.entity.responses.success.DataCompleteResponseEntity
import com.example.domain.entity.responses.success.DataEmailResetEntity
import com.example.domain.entity.responses.success.DataSendCodeDomain
import com.example.domain.entity.responses.success.EditEventByIdResponseEntity
import com.example.domain.entity.responses.success.EditEventByIdResponseEntityData
import com.example.domain.entity.responses.success.EditMyProfileResponseEntity
import com.example.domain.entity.responses.success.EditMyProfileResponseEntityConfiguration
import com.example.domain.entity.responses.success.EditMyProfileResponseEntityData
import com.example.domain.entity.responses.success.EditMyProfileResponseEntityProfile
import com.example.domain.entity.responses.success.EmailPassDataErrorEntity
import com.example.domain.entity.responses.success.EmailPassResetErrorEntity
import com.example.domain.entity.responses.success.EmailPassResetErrorsEntity
import com.example.domain.entity.responses.success.EmailResetResponseEntity
import com.example.domain.entity.responses.success.ErrorResponse
import com.example.domain.entity.responses.success.GetAllEventResponseEntity
import com.example.domain.entity.responses.success.GetAllEventResponseEntityAuthor
import com.example.domain.entity.responses.success.GetAllEventResponseEntityData
import com.example.domain.entity.responses.success.GetAllEventResponseEntityPlace
import com.example.domain.entity.responses.success.GetAllEventResponseEntityProfile
import com.example.domain.entity.responses.success.GetAllEventResponseEntityResult
import com.example.domain.entity.responses.success.GetEventByIdResponseAuthorEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseCoordinatesEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseCurrentFanEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseCurrentUserEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseDataEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseFanProfileXEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseFormsEntity
import com.example.domain.entity.responses.success.GetEventByIdResponsePlaceEntity
import com.example.domain.entity.responses.success.GetEventByIdResponsePlayerProfileXEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseProfileEntity
import com.example.domain.entity.responses.success.GetIsTechnicalWorkStatusResponseEntity
import com.example.domain.entity.responses.success.GetIsTechnicalWorkStatusResponseEntityData
import com.example.domain.entity.responses.success.GetMyEventsResponseEntity
import com.example.domain.entity.responses.success.GetMyEventsResponseEntityAuthor
import com.example.domain.entity.responses.success.GetMyEventsResponseEntityData
import com.example.domain.entity.responses.success.GetMyEventsResponseEntityPlace
import com.example.domain.entity.responses.success.GetMyEventsResponseEntityProfile
import com.example.domain.entity.responses.success.GetMyEventsResponseEntityResult
import com.example.domain.entity.responses.success.GetMyProfileResponseConfigurationEntity
import com.example.domain.entity.responses.success.GetMyProfileResponseDataEntity
import com.example.domain.entity.responses.success.GetMyProfileResponseEntity
import com.example.domain.entity.responses.success.GetMyProfileResponsePlaceEntity
import com.example.domain.entity.responses.success.GetMyProfileResponseProfileEntity
import com.example.domain.entity.responses.success.GetPrivateRequestListResponseEntity
import com.example.domain.entity.responses.success.GetPrivateRequestListResponseEntityData
import com.example.domain.entity.responses.success.GetPrivateRequestListResponseEntityEvent
import com.example.domain.entity.responses.success.GetPrivateRequestListResponseEntityProfile
import com.example.domain.entity.responses.success.GetPrivateRequestListResponseEntityResult
import com.example.domain.entity.responses.success.GetPrivateRequestListResponseEntitySender
import com.example.domain.entity.responses.success.GetRelevantUserSearchListResponseEntity
import com.example.domain.entity.responses.success.GetRelevantUserSearchListResponseEntityData
import com.example.domain.entity.responses.success.GetRelevantUserSearchListResponseEntityProfile
import com.example.domain.entity.responses.success.GetRelevantUserSearchListResponseEntityResult
import com.example.domain.entity.responses.success.GetUkraineCitiesListResponseEntity
import com.example.domain.entity.responses.success.GetUkraineCitiesListResponseEntityData
import com.example.domain.entity.responses.success.GetUkraineCitiesListResponseEntityItem
import com.example.domain.entity.responses.success.GetUserPlannedEventsByIdAuthorResponseEntity
import com.example.domain.entity.responses.success.GetUserPlannedEventsByIdDataResponseEntity
import com.example.domain.entity.responses.success.GetUserPlannedEventsByIdPlaceResponseEntity
import com.example.domain.entity.responses.success.GetUserPlannedEventsByIdProfileResponseEntity
import com.example.domain.entity.responses.success.GetUserPlannedEventsByIdResponseEntity
import com.example.domain.entity.responses.success.GetUserPlannedEventsByIdResultResponseEntity
import com.example.domain.entity.responses.success.GetUserProfileByIdResponseEntity
import com.example.domain.entity.responses.success.GetUserReviewsByIdDataEntity
import com.example.domain.entity.responses.success.GetUserReviewsByIdResponseAuthorEntity
import com.example.domain.entity.responses.success.GetUserReviewsByIdResponseEntity
import com.example.domain.entity.responses.success.GetUserReviewsByIdResponseProfileEntity
import com.example.domain.entity.responses.success.GetUserReviewsByIdResponseResultEntity
import com.example.domain.entity.responses.success.GetUsersListResponseDataEntity
import com.example.domain.entity.responses.success.GetUsersListResponseEntity
import com.example.domain.entity.responses.success.GetUsersListResponseProfileEntity
import com.example.domain.entity.responses.success.GetUsersListResponseResultEntity
import com.example.domain.entity.responses.success.JoinToEventAsFunResponseEntity
import com.example.domain.entity.responses.success.JoinToEventAsFunResponseEntityData
import com.example.domain.entity.responses.success.JoinToEventAsPlayerResponseEntity
import com.example.domain.entity.responses.success.JoinToEventAsPlayerResponseEntityData
import com.example.domain.entity.responses.success.LeaveTheEventAsFanResponseEntity
import com.example.domain.entity.responses.success.LeaveTheEventAsFanResponseEntityData
import com.example.domain.entity.responses.success.LeaveTheEventAsPlayerResponseEntity
import com.example.domain.entity.responses.success.LeaveTheEventAsPlayerResponseEntityData
import com.example.domain.entity.responses.success.LoginData
import com.example.domain.entity.responses.success.LoginDataError
import com.example.domain.entity.responses.success.LoginErrorsDomain
import com.example.domain.entity.responses.success.LoginResponse
import com.example.domain.entity.responses.success.LoginTokens
import com.example.domain.entity.responses.success.PlayingPlaceEntity
import com.example.domain.entity.responses.success.PostEmailVerifyCodeResponseEntity
import com.example.domain.entity.responses.success.PostEmailVerifyCodeResponseEntityData
import com.example.domain.entity.responses.success.PublicProfileDataResponseEntity
import com.example.domain.entity.responses.success.PublicProfileResponseEntity
import com.example.domain.entity.responses.success.RegistrationDataEntity
import com.example.domain.entity.responses.success.RegistrationResponseEntity
import com.example.domain.entity.responses.success.ResetCompleteDataEntity
import com.example.domain.entity.responses.success.ResetCompleteErrorEntity
import com.example.domain.entity.responses.success.ResetCompleteErrorsEntity
import com.example.domain.entity.responses.success.ResetCompleteResponseEntity
import com.example.domain.entity.responses.success.SendCodeDataErrorEntity
import com.example.domain.entity.responses.success.SendCodeErrorEntity
import com.example.domain.entity.responses.success.SendCodeErrorsEntity
import com.example.domain.entity.responses.success.SendCodeResponseEntity
import com.example.domain.entity.responses.success.SendVerifyCodeToUserEmailResponseEntity
import com.example.domain.entity.responses.success.SendVerifyCodeToUserEmailResponseEntityData
import com.example.domain.entity.responses.success.UpdateUserProfileResponseConfigurationEntity
import com.example.domain.entity.responses.success.UpdateUserProfileResponseDataEntity
import com.example.domain.entity.responses.success.UpdateUserProfileResponseEntity
import com.example.domain.entity.responses.success.UpdateUserProfileResponsePlaceEntity
import com.example.domain.entity.responses.success.UpdateUserProfileResponseProfileEntity

internal fun RegistrationError.toRegistrationErrorEntity(): RegistrationErrorEntity =
    RegistrationErrorEntity(
        this.code,
        this.data.toRegistrationErrorsDataEntity(),
        this.message,
        this.status,
    )

internal fun RegistrationErrorsData.toRegistrationErrorsDataEntity(): RegistrationErrorsDataEntity =
    RegistrationErrorsDataEntity(
        errors = this.errors.map { it.toRegistrationErrorDetailEntity() },
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
        this.code,
        this.data.toGetUserPlannedEventsByIdDataResponseEntity(),
        this.message,
        this.status
    )

internal fun GetUserPlannedEventsByIdDataResponse.toGetUserPlannedEventsByIdDataResponseEntity(): GetUserPlannedEventsByIdDataResponseEntity =
    GetUserPlannedEventsByIdDataResponseEntity(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results?.map { it.toGetUserPlannedEventsByIdResultResponseEntity() },
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

internal fun GetMyProfileResponse.toGetMyProfileResponseEntity(): GetMyProfileResponseEntity =
    GetMyProfileResponseEntity(
        this.code,
        this.data.toGetMyProfileResponseDataEntity(),
        this.message,
        this.status,
    )

internal fun GetMyProfileResponseData.toGetMyProfileResponseDataEntity(): GetMyProfileResponseDataEntity =
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

internal fun GetMyProfileResponseConfiguration.toGetMyProfileResponseConfigurationEntity(): GetMyProfileResponseConfigurationEntity =
    GetMyProfileResponseConfigurationEntity(
        this.email,
        this.phone,
        this.show_reviews
    )

internal fun GetMyProfileResponseProfile.toGetMyProfileResponseProfileEntity(): GetMyProfileResponseProfileEntity =
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

internal fun GetMyProfileResponsePlace.toGetMyProfileResponsePlaceEntity(): GetMyProfileResponsePlaceEntity =
    GetMyProfileResponsePlaceEntity(
        this.place_name
    )

internal fun GetMyProfileError.toGetMyProfileErrorEntity(): GetMyProfileErrorEntity =
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
        author = this.author.toGetEventByIdResponseAuthor(),
        coordinates = this.coordinates.toGetEventByIdResponseCoordinatesEntity(),
        current_users = this.current_users.map { it.toGetEventByIdResponseCurrentUserEntity() },
        current_fans = this.current_fans.map { it.toGetEventByIdResponseCurrentFanEntity() },
        date_and_time = this.date_and_time,
        description = this.description,
        duration = this.duration,
        gender = this.gender,
        id = this.id,
        name = this.name,
        need_ball = this.need_ball,
        need_form = this.need_form,
        place = this.place.toGetEventByIdResponsePlaceEntity(),
        privacy = this.privacy,
        type = this.type,
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
    )

internal fun GetEventByIdResponseCurrentFan.toGetEventByIdResponseCurrentFanEntity(): GetEventByIdResponseCurrentFanEntity =
    GetEventByIdResponseCurrentFanEntity(
        id = this.id,
        profile = this.profile.toGetEventByIdResponseFanProfileXEntity(),
        raiting = this.raiting,
    )

internal fun GetEventByIdResponseCurrentUser.toGetEventByIdResponseCurrentUserEntity(): GetEventByIdResponseCurrentUserEntity =
    GetEventByIdResponseCurrentUserEntity(
        id = this.id,
        profile = this.profile.toGetEventByIdResponsePlayerProfileXEntity(),
        raiting = this.raiting,
    )

internal fun GetEventByIdResponseFanProfileX.toGetEventByIdResponseFanProfileXEntity(): GetEventByIdResponseFanProfileXEntity =
    GetEventByIdResponseFanProfileXEntity(
        avatar_url = this.avatar_url,
        last_name = this.last_name,
        name = this.name,
        position = this.position,
        working_leg = this.working_leg
    )

internal fun GetEventByIdResponsePlayerProfileX.toGetEventByIdResponsePlayerProfileXEntity(): GetEventByIdResponsePlayerProfileXEntity =
    GetEventByIdResponsePlayerProfileXEntity(
        avatar_url = this.avatar_url,
        last_name = this.last_name,
        name = this.name,
        position = this.position,
        working_leg = this.working_leg
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

internal fun EditEventByIdResponse.toEditEventByIdResponseEntity(): EditEventByIdResponseEntity =
    EditEventByIdResponseEntity(
        this.code,
        this.data.toEditEventByIdResponseEntityData(),
        this.message,
        this.status
    )

internal fun EditEventByIdResponseData.toEditEventByIdResponseEntityData(): EditEventByIdResponseEntityData =
    EditEventByIdResponseEntityData(
        this.success
    )

internal fun EditEventByIdResponseError.toEditEventByIdResponseErrorEntity(): EditEventByIdResponseErrorEntity =
    EditEventByIdResponseErrorEntity(
        this.code,
        this.data.toEditEventByIdResponseErrorDataEntity(),
        this.message,
        this.status
    )

internal fun EditEventByIdResponseErrorData.toEditEventByIdResponseErrorDataEntity(): EditEventByIdResponseErrorDataEntity =
    EditEventByIdResponseErrorDataEntity(
        listOf(this.errors[0].toEditEventByIdResponseErrorDetailEntity()),
        this.type
    )

internal fun EditEventByIdResponseErrorDetail.toEditEventByIdResponseErrorDetailEntity(): EditEventByIdResponseErrorDetailEntity =
    EditEventByIdResponseErrorDetailEntity(
        this.detail
    )

internal fun SendVerifyCodeToUserEmailResponse.toSendVerifyCodeToUserEmailResponseEntity(): SendVerifyCodeToUserEmailResponseEntity =
    SendVerifyCodeToUserEmailResponseEntity(
        this.code,
        this.data.toSendVerifyCodeToUserEmailResponseEntityData(),
        this.message,
        this.status,
    )

internal fun SendVerifyCodeToUserEmailResponseData.toSendVerifyCodeToUserEmailResponseEntityData(): SendVerifyCodeToUserEmailResponseEntityData =
    SendVerifyCodeToUserEmailResponseEntityData(
        this.success
    )

internal fun SendVerifyCodeToUserEmailError.toSendVerifyCodeToUserEmailErrorEntity(): SendVerifyCodeToUserEmailErrorEntity =
    SendVerifyCodeToUserEmailErrorEntity(
        this.code,
        this.data.toSendVerifyCodeToUserEmailErrorEntityData(),
        this.status,
        this.message,
    )

internal fun SendVerifyCodeToUserEmailErrorData.toSendVerifyCodeToUserEmailErrorEntityData(): SendVerifyCodeToUserEmailErrorEntityData =
    SendVerifyCodeToUserEmailErrorEntityData(
        listOf(this.errors[0].toSendVerifyCodeToUserEmailErrorEntityDataDetail()),
        this.type
    )

internal fun SendVerifyCodeToUserEmailErrorDataDetail.toSendVerifyCodeToUserEmailErrorEntityDataDetail(): SendVerifyCodeToUserEmailErrorEntityDataDetail =
    SendVerifyCodeToUserEmailErrorEntityDataDetail(
        this.detail
    )

internal fun PostEmailVerifyCodeResponse.toPostEmailVerifyCodeResponseEntity(): PostEmailVerifyCodeResponseEntity =
    PostEmailVerifyCodeResponseEntity(
        this.code,
        this.data.toPostEmailVerifyCodeResponseEntityData(),
        this.message,
        this.status,
    )

internal fun PostEmailVerifyCodeResponseData.toPostEmailVerifyCodeResponseEntityData(): PostEmailVerifyCodeResponseEntityData =
    PostEmailVerifyCodeResponseEntityData(
        this.success,
    )

internal fun PostEmailVerifyCodeError.toPostEmailVerifyCodeErrorEntity(): PostEmailVerifyCodeErrorEntity =
    PostEmailVerifyCodeErrorEntity(
        this.code,
        this.data.toPostEmailVerifyCodeDataErrorEntity(),
        this.message,
        this.status
    )

internal fun PostEmailVerifyCodeDataError.toPostEmailVerifyCodeDataErrorEntity(): PostEmailVerifyCodeDataErrorEntity =
    PostEmailVerifyCodeDataErrorEntity(
        listOf(this.errors[0].toPostEmailVerifyCodeErrorEntityDetail()),
        this.type,
    )

internal fun PostEmailVerifyCodeErrorDetail.toPostEmailVerifyCodeErrorEntityDetail(): PostEmailVerifyCodeErrorEntityDetail =
    PostEmailVerifyCodeErrorEntityDetail(
        this.detail
    )

internal fun GetIsTechnicalWorkStatusResponse.toGetIsTechnicalWorkStatusResponseEntity(): GetIsTechnicalWorkStatusResponseEntity =
    GetIsTechnicalWorkStatusResponseEntity(
        this.code,
        this.data.toGetIsTechnicalWorkStatusResponseEntityData(),
        this.message,
        this.status,
    )

internal fun GetIsTechnicalWorkStatusResponseData.toGetIsTechnicalWorkStatusResponseEntityData(): GetIsTechnicalWorkStatusResponseEntityData =
    GetIsTechnicalWorkStatusResponseEntityData(
        this.isMaintenance
    )

internal fun GetIsTechnicalWorkStatusError.toGetIsTechnicalWorkStatusErrorEntity(): GetIsTechnicalWorkStatusErrorEntity =
    GetIsTechnicalWorkStatusErrorEntity(
        this.code,
        this.data.toGetIsTechnicalWorkStatusErrorDataEntity(),
        this.message,
        this.status
    )

internal fun GetIsTechnicalWorkStatusErrorData.toGetIsTechnicalWorkStatusErrorDataEntity(): GetIsTechnicalWorkStatusErrorDataEntity =
    GetIsTechnicalWorkStatusErrorDataEntity(
        listOf(this.errors[0].toGetIsTechnicalWorkStatusErrorDetailEntity()),
        this.type
    )

internal fun GetIsTechnicalWorkStatusErrorDetail.toGetIsTechnicalWorkStatusErrorDetailEntity(): GetIsTechnicalWorkStatusErrorDetailEntity =
    GetIsTechnicalWorkStatusErrorDetailEntity(
        this.detail
    )

internal fun GetRelevantUserSearchListResponse.toGetRelevantUserSearchListResponseEntity(): GetRelevantUserSearchListResponseEntity =
    GetRelevantUserSearchListResponseEntity(
        this.code,
        this.data.toGetRelevantUserSearchListResponseEntityData(),
        this.message,
        this.status,
    )

internal fun GetRelevantUserSearchListResponseData.toGetRelevantUserSearchListResponseEntityData(): GetRelevantUserSearchListResponseEntityData =
    GetRelevantUserSearchListResponseEntityData(
        this.current_page,
        this.next,
        this.page_size,
        this.previous,
        this.results.map { it.toGetRelevantUserSearchListResponseEntityResult() },
        this.success,
        this.total_count
    )

internal fun GetRelevantUserSearchListResponseResult.toGetRelevantUserSearchListResponseEntityResult(): GetRelevantUserSearchListResponseEntityResult =
    GetRelevantUserSearchListResponseEntityResult(
        this.id,
        this.is_online,
        this.profile.toGetRelevantUserSearchListResponseEntityProfile(),
        this.raiting,
        this.role,
    )

internal fun GetRelevantUserSearchListResponseProfile.toGetRelevantUserSearchListResponseEntityProfile(): GetRelevantUserSearchListResponseEntityProfile =
    GetRelevantUserSearchListResponseEntityProfile(
        this.age,
        this.avatar_url,
        this.gender,
        this.id,
        this.name,
        this.last_name,
        this.position,
    )

internal fun GetRelevantUserSearchListError.toGetRelevantUserSearchListErrorEntity(): GetRelevantUserSearchListErrorEntity =
    GetRelevantUserSearchListErrorEntity(
        this.code,
        this.data.toGetRelevantUserSearchListErrorDataEntity(),
        this.message,
        this.status
    )

internal fun GetRelevantUserSearchListErrorData.toGetRelevantUserSearchListErrorDataEntity(): GetRelevantUserSearchListErrorEntityData =
    GetRelevantUserSearchListErrorEntityData(
        listOf(this.errors[0].toGetRelevantUserSearchListErrorDetailEntity()),
        this.type
    )

internal fun GetRelevantUserSearchListErrorDetail.toGetRelevantUserSearchListErrorDetailEntity(): GetRelevantUserSearchListErrorEntityDetail =
    GetRelevantUserSearchListErrorEntityDetail(
        this.detail
    )

internal fun EditMyProfileResponse.toEditMyProfileResponseEntity(): EditMyProfileResponseEntity =
    EditMyProfileResponseEntity(
        this.code,
        this.data.toEditMyProfileResponseEntityData(),
        this.message,
        this.status
    )

internal fun EditMyProfileResponseData.toEditMyProfileResponseEntityData(): EditMyProfileResponseEntityData =
    EditMyProfileResponseEntityData(
        configuration = this.configuration?.toEditMyProfileResponseEntityConfiguration(),
        phone = this.phone,
        profile = this.profile?.toEditMyProfileResponseProfileEntity(),
    )

internal fun EditMyProfileResponseProfile.toEditMyProfileResponseProfileEntity(): EditMyProfileResponseEntityProfile =
    EditMyProfileResponseEntityProfile(
        about_me = this.about_me,
        birthday = this.birthday,
        gender = this.gender,
        height = this.height,
        position = this.position,
        weight = this.weight,
        working_leg = this.working_leg
    )

internal fun EditMyProfileResponseConfiguration.toEditMyProfileResponseEntityConfiguration(): EditMyProfileResponseEntityConfiguration =
    EditMyProfileResponseEntityConfiguration(
        email = this.email,
        show_reviews = this.show_reviews,
        phone = phone,
    )

internal fun EditMyProfileError.toEditMyProfileErrorEntity(): EditMyProfileErrorEntity =
    EditMyProfileErrorEntity(
        code = this.code,
        data = this.data.toEditMyProfileErrorEntityData(),
        status = this.status,
        message = this.message,
    )

internal fun EditMyProfileErrorData.toEditMyProfileErrorEntityData(): EditMyProfileErrorEntityData =
    EditMyProfileErrorEntityData(
        errors = listOf(this.errors[0].toEditMyProfileErrorEntityDetail()),
        type = this.type,
    )

internal fun EditMyProfileErrorDetail.toEditMyProfileErrorEntityDetail(): EditMyProfileErrorEntityDetail =
    EditMyProfileErrorEntityDetail(
        detail = this.detail
    )

internal fun GetUkraineCitiesListResponse.toGetUkraineCitiesListResponseEntity(): GetUkraineCitiesListResponseEntity =
    GetUkraineCitiesListResponseEntity(
        code = this.code,
        data = this.data.map { it.toGetUkraineCitiesListResponseDataEntity() },
        message = this.message,
        status = this.status,
    )

internal fun GetUkraineCitiesListResponseData.toGetUkraineCitiesListResponseDataEntity(): GetUkraineCitiesListResponseEntityData =
    GetUkraineCitiesListResponseEntityData(
        cities = this.cities.map { it.toGetUkraineCitiesListResponseEntityItem() },
        name = this.name,
    )

internal fun GetUkraineCitiesListResponseItem.toGetUkraineCitiesListResponseEntityItem(): GetUkraineCitiesListResponseEntityItem =
    GetUkraineCitiesListResponseEntityItem(
        lat = this.lat,
        lng = this.lng,
        name = this.name,
    )

internal fun GetUkraineCitiesListError.toGetUkraineCitiesListErrorEntity(): GetUkraineCitiesListErrorEntity =
    GetUkraineCitiesListErrorEntity(
        code = this.code,
        data = this.data.toGetUkraineCitiesListEntityErrorData(),
        status = this.status,
    )

internal fun GetUkraineCitiesListDataError.toGetUkraineCitiesListEntityErrorData(): GetUkraineCitiesListEntityErrorData =
    GetUkraineCitiesListEntityErrorData(
        errors = this.errors.map { it.toGetUkraineCitiesListEntityErrorDetail() },
        type = this.type
    )

internal fun GetUkraineCitiesListErrorDetail.toGetUkraineCitiesListEntityErrorDetail(): GetUkraineCitiesListEntityErrorDetail =
    GetUkraineCitiesListEntityErrorDetail(
        detail = this.detail
    )

internal fun JoinToEventAsFunResponse.toJoinToEventAsFunResponseEntity(): JoinToEventAsFunResponseEntity =
    JoinToEventAsFunResponseEntity(
        code = this.code,
        data = this.data.toJoinToEventAsFunResponseDataEntity(),
        status = this.status,
    )

internal fun JoinToEventAsFunResponseData.toJoinToEventAsFunResponseDataEntity(): JoinToEventAsFunResponseEntityData =
    JoinToEventAsFunResponseEntityData(
        success = this.success,
    )

internal fun JoinToEventAsPlayerResponse.toJoinToEventAsPlayerResponseEntity(): JoinToEventAsPlayerResponseEntity =
    JoinToEventAsPlayerResponseEntity(
        code = this.code,
        data = this.data.toJoinToEventAsPlayerResponseDataEntity(),
        status = this.status,
    )

internal fun JoinToEventAsPlayerResponseData.toJoinToEventAsPlayerResponseDataEntity(): JoinToEventAsPlayerResponseEntityData =
    JoinToEventAsPlayerResponseEntityData(
        success = this.success,
    )

internal fun JoinToEventAsFunError.toJoinToEventAsFunErrorEntity(): JoinToEventAsFunErrorEntity =
    JoinToEventAsFunErrorEntity(
        code = this.code,
        data = this.data.toJoinToEventAsFunErrorDataEntity(),
        status = this.status,
    )

internal fun JoinToEventAsFunErrorData.toJoinToEventAsFunErrorDataEntity(): JoinToEventAsFunErrorDataEntity =
    JoinToEventAsFunErrorDataEntity(
        errors = this.errors.map { it.toJoinToEventAsFunDetailDataEntity() },
        type = this.type,
    )

internal fun JoinToEventAsFunDetailData.toJoinToEventAsFunDetailDataEntity(): JoinToEventAsFunDetailDataEntity =
    JoinToEventAsFunDetailDataEntity(
        detail = this.detail,
    )

internal fun JoinToEventAsPlayerError.toJoinToEventAsPlayerErrorEntity(): JoinToEventAsPlayerErrorEntity =
    JoinToEventAsPlayerErrorEntity(
        code = this.code,
        data = this.data.toJoinToEventAsPlayerErrorDataEntity(),
        status = this.status,
    )

internal fun JoinToEventAsPlayerErrorData.toJoinToEventAsPlayerErrorDataEntity(): JoinToEventAsPlayerErrorDataEntity =
    JoinToEventAsPlayerErrorDataEntity(
        errors = this.errors.map { it.toJoinToEventAsPlayerDetailDataEntity() },
        type = this.type,
    )

internal fun JoinToEventAsPlayerDetailData.toJoinToEventAsPlayerDetailDataEntity(): JoinToEventAsPlayerDetailDataEntity =
    JoinToEventAsPlayerDetailDataEntity(
        detail = this.detail,
    )

internal fun LeaveTheEventAsFanResponse.toLeaveTheEventAsFanResponseEntity() =
    LeaveTheEventAsFanResponseEntity(
        code = this.code,
        data = this.data.toLeaveTheEventAsFanResponseEntityData(),
        status = this.status,
    )

internal fun LeaveTheEventAsFanResponseData.toLeaveTheEventAsFanResponseEntityData(): LeaveTheEventAsFanResponseEntityData =
    LeaveTheEventAsFanResponseEntityData(
        success = this.success,
    )

internal fun LeaveTheEventAsPlayerResponse.toLeaveTheEventAsPlayerResponseEntity(): LeaveTheEventAsPlayerResponseEntity =
    LeaveTheEventAsPlayerResponseEntity(
        code = this.code,
        data = this.data.toLeaveTheEventAsPlayerResponseDataEntity(),
        status = this.status,
    )

internal fun LeaveTheEventAsPlayerResponseData.toLeaveTheEventAsPlayerResponseDataEntity(): LeaveTheEventAsPlayerResponseEntityData =
    LeaveTheEventAsPlayerResponseEntityData(
        success = this.success,
    )

internal fun LeaveTheEventAsFunError.toLeaveTheEventAsFunErrorEntity(): LeaveTheEventAsFunErrorEntity =
    LeaveTheEventAsFunErrorEntity(
        code = this.code,
        data = this.data.toLeaveTheEventAsFunErrorDataEntity(),
        status = this.status,
    )

internal fun LeaveTheEventAsFunErrorData.toLeaveTheEventAsFunErrorDataEntity(): LeaveTheEventAsFunErrorDataEntity =
    LeaveTheEventAsFunErrorDataEntity(
        errors = this.errors.map { it.toLeaveTheEventAsFunDetailDataEntity() },
        type = this.type,
    )

internal fun LeaveTheEventAsFunDetailData.toLeaveTheEventAsFunDetailDataEntity(): LeaveTheEventAsFunDetailDataEntity =
    LeaveTheEventAsFunDetailDataEntity(
        detail = this.detail,
    )

internal fun LeaveTheEventAsPlayerError.toLeaveTheEventAsPlayerErrorEntity(): LeaveTheEventAsPlayerErrorEntity =
    LeaveTheEventAsPlayerErrorEntity(
        code = this.code,
        data = this.data.toLeaveTheEventAsPlayerErrorDataEntity(),
        status = this.status,
    )

internal fun LeaveTheEventAsPlayerErrorData.toLeaveTheEventAsPlayerErrorDataEntity(): LeaveTheEventAsPlayerErrorDataEntity =
    LeaveTheEventAsPlayerErrorDataEntity(
        errors = this.errors.map { it.toLeaveTheEventAsPlayerDetailDataEntity() },
        type = this.type,
    )

internal fun LeaveTheEventAsPlayerDetailData.toLeaveTheEventAsPlayerDetailDataEntity(): LeaveTheEventAsPlayerDetailDataEntity =
    LeaveTheEventAsPlayerDetailDataEntity(
        detail = this.detail,
    )

internal fun GetPrivateRequestListResponse.toGetPrivateRequestListResponseEntity() =
    GetPrivateRequestListResponseEntity(
        code = this.code,
        data = this.data.toGetPrivateRequestListResponseEntityData(),
        status = this.status,
        message = this.message,
    )

internal fun GetPrivateRequestListResponseData.toGetPrivateRequestListResponseEntityData() =
    GetPrivateRequestListResponseEntityData(
        current_page = this.current_page,
        next = this.next,
        page_size = this.page_size,
        previous = this.previous,
        results = this.results.map { it.toGetPrivateRequestListResponseEntityResult() },
        success = this.success,
        total_count = this.total_count,
    )

internal fun GetPrivateRequestListResponseResult.toGetPrivateRequestListResponseEntityResult() =
    GetPrivateRequestListResponseEntityResult(
        event = this.event.toGetPrivateRequestListResponseEntityEvent(),
        id = this.id,
        recipient = this.recipient,
        sender = this.sender.toGetPrivateRequestListResponseEntitySender(),
        status = this.status,
        time_created = this.time_created,
    )

internal fun GetPrivateRequestListResponseEvent.toGetPrivateRequestListResponseEntityEvent() = GetPrivateRequestListResponseEntityEvent(
    date_and_time = this.date_and_time,
    id = this.id,
    name = this.name
)

internal fun GetPrivateRequestListResponseSender.toGetPrivateRequestListResponseEntitySender() = GetPrivateRequestListResponseEntitySender(
    id = this.id,
    profile = this.profile.toGetPrivateRequestListResponseEntityProfile(),
    raiting = this.raiting,
)

internal fun GetPrivateRequestListResponseProfile.toGetPrivateRequestListResponseEntityProfile() = GetPrivateRequestListResponseEntityProfile(
    avatar_url = this.avatar_url,
    last_name = this.last_name,
    name = this.name,
    position = this.position,
    working_leg = this.working_leg
)

internal fun GetPrivateRequestListResponseError.toGetPrivateRequestListResponseErrorEntity() =
    GetPrivateRequestListResponseErrorEntity(
        code = this.code,
        data = this.data.toGetPrivateRequestListResponseErrorEntityData(),
        status = this.status,
    )

internal fun GetPrivateRequestListResponseErrorData.toGetPrivateRequestListResponseErrorEntityData() =
    GetPrivateRequestListResponseErrorEntityData(
        errors = this.errors.map { it.toGetPrivateRequestListResponseErrorDetailEntity() },
        type = this.type
    )

internal fun GetPrivateRequestListResponseErrorDetail.toGetPrivateRequestListResponseErrorDetailEntity() =
    GetPrivateRequestListResponseErrorDetailEntity(
        detail = this.detail
    )